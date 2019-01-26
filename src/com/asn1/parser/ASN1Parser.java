package com.asn1.parser;

import com.asn1.util.UtilReflection;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase encargada de decodificar CDRs de tipo ASN1.
 * @see ASN1Configuration
 * @author Efrain Blanco
 * @author Jhon Fernandez
 * @version 1.0
 */
public class ASN1Parser implements ASN1Configuration{

//    private Map<String, List<String>> mapColumns;     
    private Set<String> setColumns;  
    private String recordName = null;    
    private Path filePath;
    private String header;    
    private List<String> innerAttributes;
    private List<String> subLines;
    private String strSeparators;
    private List<Object> genericList;
    private String cdrOutputFolder;
    private String outputRecord;
    private String asnDir;
    private String packageName;
    private Class outputRecordClass;
    private List<Exception> exceptionList;
    private static final int DEEP_LEVEL = 1;
    private int deepLevelCount;
    private String lastParentKey;
    
    
    /**
     * Decodifica CDR y los convierte en archivos de texto independientes
     * @see #decodeFile(java.lang.String) 
     * @see #buildChoices() 
     * @param dirFile   Ruta de CDR
     * @throws Exception    Si el proceso no es exitoso
     */
    public void run(String dirFile) throws Exception{
        decodeFile(dirFile);
        try {
            buildColumnsMap();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
//        buildChoices();
    }
    
    /**
     * Decodifica archivo CDR haciendo uso de la libreria 
     * <a href="https://www.openmuc.org/asn1/">jASN1</a>
     * @param dirFile   Ruta del CDR
     * @throws IOException  Si ocurre algun error en la lectura del CDR
     * @throws ClassNotFoundException   si la clase <code>packageName 
     *                                  + "." + outputRecord</code> no existe
     * @throws InstantiationException   si ocurre algun error en la instanciación 
     *                                  de la clase <code>outputRecordClass</code>
     * @throws IllegalAccessException   si ocurre algun error en la instanciación 
     *                                  de la clase <code>outputRecordClass</code>
     */
    public void decodeFile(String dirFile) throws IOException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        File file = new File(dirFile);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        ByteArrayInputStream bais = new ByteArrayInputStream(fileContent);
        genericList = new ArrayList();
        Object outputRecordInstance;
        List<PropertyDescriptor> filterProperties;
        exceptionList = new LinkedList();
        Object choice;

        outputRecordClass = Class.forName(packageName + "." + outputRecord);
        while (bais.available() > 0) {
            outputRecordInstance = outputRecordClass.newInstance();
            try {
                Method decodeMethod = outputRecordInstance.getClass().getMethod(DECODE_METHOD, InputStream.class);
                decodeMethod.invoke(outputRecordInstance, bais);
                filterProperties = UtilReflection.filterProperties(outputRecordInstance);
                choice = getChoice(filterProperties, outputRecordInstance);
                genericList.add(choice);
            } catch (Exception ex) {
//                ex.printStackTrace();
                exceptionList.add(ex);
            }
        }
    }
    
    /**
     * Construye los registros de cada choice y los escribe en archivos independientes
     * @see #buildRecords(java.lang.String) 
     * @throws Exception si ocure algun error en la construcción de los registros
     */
    private void buildChoices() throws Exception {
        TreeSet<Object> treeChoices = genericList.stream()
                .collect(Collectors.toCollection(
                        () -> new TreeSet<Object>((p1, p2) -> p1.getClass().getSimpleName().compareTo(p2.getClass().getSimpleName()))
                ));
        
        for( Object choice: treeChoices ){
            buildRecords( choice.getClass().getSimpleName() );
        }
    }
    
    /**
     * Extrae objeto choice no nulo dentro del OutputRecord. 
     * Ejemplo: si el objeto no nulo es de tipo <code>AdjustmentRecordV2</code> 
     * entonces retornará dicho objeto(choice).
     * @see UtilReflection#filterProperties(java.lang.Object) 
     * @see #invokeGetter(java.beans.PropertyDescriptor, java.lang.Object) 
     * @param filterProperties  atributos del OutputRecord
     * @param outputRecordInstance instancia del OutputRecord
     * @return objeto choice no nulo
     * @throws IntrospectionException   si ocurre un error al 
     *                                  filtrar las propiedades.
     */
    private Object getChoice(List<PropertyDescriptor> filterProperties, 
            Object outputRecordInstance) throws IntrospectionException {
        
        filterProperties = UtilReflection.filterProperties(outputRecordInstance);
        Optional<Object> optionalChoice = filterProperties.stream().
                filter(prop -> invokeGetter(prop, outputRecordInstance) != null ).
                map(prop -> invokeGetter(prop, outputRecordInstance)).findFirst();
        if( optionalChoice.isPresent() ){
            return optionalChoice.get();
        }
        return null;
    }

    /**
     * Hace un llamado al metodo get de un atributo sobre un objeto especifico
     * @param prop  atributo o propiedad
     * @param outputRecordInstance  instancia del objeto
     * @return  resultado del metodo get
     */
    private Object invokeGetter(PropertyDescriptor prop, Object outputRecordInstance) {
        try {
            return prop.getReadMethod().invoke(outputRecordInstance);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Construye primera linea del archivo con los nombres de las columnas 
     * de un objeto choice especifico. 
     * Ejemplo: Si <code>choice="AdjustmentRecordV2"</code> entonces se extraeran 
     * todas las propiedades de la Clase <code>AdjustmentRecordV2</code> 
     * y se escribiran como cabecera del archivo.
     * @see #appendColumn(java.lang.String) 
     * @param choice    nombre del choice.
     * @throws IOException  si ocurre un error en la escritura de la cabecera del archivo.
     */
    private void buildHeader(String choice) throws IOException {
//        mapColumns.forEach((k, v) -> {
//            if( k.startsWith( choice.toLowerCase() ) ){            
//                appendColumn(k);
//            }
//        });
        setColumns.forEach(column -> {
            if (column.startsWith(choice.toLowerCase())) {
                appendColumn(column);
            }
        });
        header = header.substring(1) + "\n";
        filePath = Paths.get(cdrOutputFolder + File.separator + choice + ".txt");
        Files.write(filePath, header.getBytes());
    }
    
    
    private void buildHeader(Object record){
    
    
    }
    
    
    
    /**
     * Concatena columna al header junto con su separador
     * @param columnName   nombre de columna
     * @see ASN1Configuration#CSV_SEPARATOR
     */
    private void appendColumn(String columnName) {
        header += CSV_SEPARATOR + columnName;
    }
    
    /**
     * Genera un archivo de texto con los registros decodificados 
     * de un objeto choice especifico.
     * @see #buildHeader(java.lang.String) 
     * @see #initLines() 
     * @see #buildRecord(java.lang.Object, boolean) +
     * @see #writeRecord() 
     * @param choice    nombre del choice.
     * @throws Exception    si la escritura de los registros no es exitosa.
     */
    private void buildRecords(String choice) throws Exception {
        header = "";
        lastParentKey = "";
        deepLevelCount = 0;
        buildHeader(choice);
        List<StringBuilder> cdr;
        for (Object record : genericList) {
//            if (record.getClass().getSimpleName().toLowerCase().equals(choice.toLowerCase())) {       
              if (record.getClass().getSimpleName().toLowerCase().equalsIgnoreCase("OnlineCreditControlRecord")) {      
                recordName = choice.toLowerCase(); 
                //recordType = record.getClass().getSimpleName().toLowerCase();
                
                //cdr = buildRecord(record, false, recordType);
                cdr = buildRecordChoice(record);
                writeRecord(cdr);
            }
        }
    }
    
    /**
     * Escribe informacion de un objeto o record en el archivo, el numero de 
     * lineas que se escribiran en el archivo dependerá del numero 
     * de elementos de la lista <code>lines</code>.
     * @see #getStrEmptyChilds(int) 
     * @see ASN1Configuration#ESCAPE_SEPARATOR
     * @throws IOException  si ocurre un error en la escritura del archivo
     */
    private void writeRecord(List<StringBuilder> cdr) throws IOException{
        int totalSeparators = header.split(ESCAPE_SEPARATOR).length;
        int lineSeparators;
        String value;
        String separators;
        String line;
        
        for( int i = 0; i < cdr.size(); i++  ){
            line = cdr.get(i).substring(1);
            lineSeparators = line.split(ESCAPE_SEPARATOR).length;
            separators = getStrEmptyChilds( totalSeparators - lineSeparators );
            value = line + separators;
            cdr.set(i, new StringBuilder(value));
        }
        
        FileWriter output = new FileWriter(filePath.toString(), true);
        cdr.forEach(cdrLine -> {
            try {
                output.write(cdrLine.toString());
            } catch (IOException ex) {
                Logger.getLogger(ASN1Parser.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        output.close();
    }
    
    private void updateRecordType(String key, String type){
        recordName += "." + key.toLowerCase();
        //recordType += "." + type.toLowerCase();
//        System.out.println( recordName );
        //System.out.println( recordType );
    }

    private List<StringBuilder> buildRecordChoice(Object record) throws Exception{
        Object object;
        List<Field> filterProperties = UtilReflection.filterFields(record);
        X x = new X();
                
        for (Field fieldChoice : filterProperties){
            
            fieldChoice.setAccessible(true);
            
            object = isNullObject(fieldChoice, record) ? new NullObject(fieldChoice.getType()) : fieldChoice.get(record);
            
            x.add(getInformationField(object, fieldChoice.getType().getSimpleName()));            
        }
        
        
        return x.listResultProperties;
    }
    
    public class X {         
        List<StringBuilder> listResultProperties = new ArrayList();
        
 
        
        public X add(StringBuilder result) {
            if(this.listResultProperties.isEmpty()){
                this.listResultProperties.add(new StringBuilder());
            }
            this.listResultProperties.get(0).append(CSV_SEPARATOR+result);
            return this;
        }
        
        private void appendTwoList(List<StringBuilder> result) {
            
            if(this.listResultProperties.size()< result.size()){
                int diffferenceList = result.size()-this.listResultProperties.size();
                for (int i = 0;i<diffferenceList;i++){
                    this.listResultProperties.add(new StringBuilder());
                }
            }
            
            for (int i = 1; i < this.listResultProperties.size(); i++) {
                if (rowHasData(i, result)) {
                    this.listResultProperties.get(i).append(result.get(i));
                }
            }
            
        }
        
        private X matchList(List<StringBuilder> result) {
            
            if(!this.listResultProperties.isEmpty()){
                
                this.listResultProperties.get(0).append(result.get(0));
                appendTwoList(result);
                
            }else{
                this.listResultProperties = result;
            }
            
            
            return this;
        }
        
         public X add(List<X> result) {            
            for (X x : result) 
                add(x);            
                        
            return this;
        }
        
        public X add(X x) {                        
            matchList(x.listResultProperties);            
            return this;
        }
    }
    
    public String buildChoiceHeader(Object record, String columnName, String path) throws Exception{
        String resultado ="";
        
        Class clazz = record instanceof NullObject ? ((NullObject)record).getClazz(): record.getClass();
        List<Field> filterAttributes = UtilReflection.filterFields(clazz);
                
        if( path.toLowerCase().endsWith("currencytype") ){
            System.out.println("");
        }
        
        System.out.println(path);
        if (filterAttributes.isEmpty() || isMaximumDeepLevel(clazz.getSimpleName(), path)){
            if (path.endsWith(".Group")){
                int az = 1;
            }
            resultado = CSV_SEPARATOR + columnName;
            return resultado;
        }
        
        for(Field att: filterAttributes){
            att.setAccessible(true);
            
            if( att.getName().equals("value") ){
                resultado = CSV_SEPARATOR + columnName;
                return resultado;
            }else if( att.getType().getName().endsWith(".List") ){                
                
                Type listType = UtilReflection.getTypeList(att);
                String className = listType.getTypeName();
                Class c = Class.forName(className);
                Object instance = c.newInstance();
                
                resultado += buildChoiceHeader(instance, columnName, path /*+ "." +instance.getClass().getSimpleName()*/);
            
            }else{
                Object instance;
                
                if( UtilReflection.isPrimitive(att) ){
                    instance = isNullObject(att, record) ? new NullObject(att.getType()): att.get(record);
                }else{
                    instance = att.getType().newInstance();
                }
                
                String auxName = att.getName().equals("seqOf") ? att.getDeclaringClass().getSimpleName() : att.getName();
                
                resultado += buildChoiceHeader(instance, columnName+"."+att.getName(), path + "." + auxName);
            }
        }
        
        return resultado;
    }
    
    private X getInformationField(Object record, String path) throws Exception{
        X x = new X();        
        Object innerObj;
        Object valueAtt;        
        
        System.out.println("Path: "+ path);
                
        if (record instanceof NullObject){
           
            StringBuilder resultado = new StringBuilder(buildStringSeparators(record, path));
            return x.add(resultado);
        }
        
        if (isMaximumDeepLevel(record.getClass().getSimpleName(),path)){
            return new X();
        }
        
        
        List<Field> filterAttributes = UtilReflection.filterFields(record.getClass());
        
        if (filterAttributes.isEmpty()){
            
            return x.add(new StringBuilder(record.toString()));
        }
        
        for (Field att: filterAttributes){
            
            att.setAccessible(true);
            valueAtt = isNullObject(att, record) ? new NullObject(att.getType()) : att.get(record);
            if (valueAtt instanceof List){
                
                List listAtt = (List) valueAtt;
                
                X list = new X();
                for (Object objListAtt : listAtt) {
                    
                    List<Field> filterValueProperties = UtilReflection.filterFields(objListAtt);                    
                    if (filterValueProperties.isEmpty()) {
                        
                         return x.add(new StringBuilder(objListAtt.toString()));
                        //x.add(getInformationField(valueAtt, path + "." + valueAtt.getClass().getSimpleName()));
                    } else {      
                        X recordList = new X();
                        for (Field attList : filterValueProperties) {
                            attList.setAccessible(true);
                            innerObj = isNullObject(attList, objListAtt) ? new NullObject(attList.getType()) : attList.get(objListAtt);
                            recordList.add(getInformationField(innerObj, path + "." + attList.getType().getSimpleName()));

                        } 
                        list.listResultProperties.addAll(recordList.listResultProperties);
                    }                    
                    
                }
                x.add(list);
                
            }else{
                
                if(att.getName().equalsIgnoreCase("value")){
                    
                    return x.add(new StringBuilder(record.toString()));
                }
                
                x.add(getInformationField(valueAtt, path + "." + att.getType().getSimpleName()));
            }
        
        
        }                                                        
       
        return x;
    }
    
    private List<StringBuilder> buildRecord(Object record, boolean isListElement, String path, List<StringBuilder> cdrLines, StringBuilder line)throws Exception {
        
        Object object;
        String propName;
        Object innerObj;
        
        String type = record instanceof NullObject ? 
                ((NullObject)record).getClazz().getSimpleName() : record.getClass().getSimpleName();
        
   
                
        if (isMaximumDeepLevel(type, path)) {
           // return cdrLine;
        }
        
        if (record instanceof NullObject) {
            if (isListElement) {
                addAttribute(record);                
            } else {
                putLine(record);                
            }
            removeLastParent();
            //return;
        }
        List<Field> filterProperties = UtilReflection.filterFields(record);

        if (filterProperties.isEmpty()) {
            if (isListElement) {
                addAttribute(record);
                removeLastParent();
            } else {
                putLine(record);
                removeLastParent();
            }
        } else {
            for (Field field : filterProperties) {
                propName = field.getName();
                field.setAccessible(true);
                object = isNullObject(field, record) ? new NullObject(field.getType()) : field.get(record);

//                System.out.println( propName );
                if( propName.equalsIgnoreCase("group") ){
                    System.out.println("");
                }
                
                if (object instanceof List) {
                    subLines = new ArrayList<>();
                    List list = (List) object;

                    if( list.isEmpty() ){
                        System.out.println("");
                    }
                    
                    for (Object obj : list) {
                        innerAttributes = new ArrayList<>();
                        List<Field> attributes = filtrar(Arrays.asList(obj.getClass().getDeclaredFields()));
                        
                        if (attributes.isEmpty()) {
                            addAttribute(obj);
                            removeLastParent();
                        } else {
                            for (Field innerField : attributes) {
                                innerField.setAccessible(true);
                                innerObj = isNullObject(innerField, obj) ? new NullObject(innerField.getType()) : innerField.get(obj);
                                //buildRecord(innerObj, true, null, innerField.getName());
                            }
                        }
                        
                        StringBuilder strAttributes = new StringBuilder();
                        innerAttributes.forEach(strAttributes::append);
                        subLines.add(strAttributes.toString());
                    }
//                    removeLastParentKey();
                    putListLines();
                } else {
                    //buildRecord(object, false, propName, null);
                }
            }
            removeLastParent();
        }
        return null;
    }

    private boolean isNullObject(Field field, Object record) throws IllegalArgumentException, IllegalAccessException{
        Object object = field.get(record);
        
        if (object == null) {
            return true;
        } else {
            if (object instanceof List) {
                List list = (List) object;
                return list.isEmpty();
            } else {
                return false;
            }
        }
    }
    
    /**
     * Adiciona elementos a la lista <code>lines</code> 
     * dependiendo de la lista <code>sublines</code>, esto aplica para 
     * propiedades del record de tipo SEQUENCE.
     * @see #getSeparators(java.lang.String) 
     * @see #rowHasData(int) 
     * @see #getStrEmptyChilds(int) 
     * @see ASN1Configuration#ESCAPE_SEPARATOR
     */
    private void putListLines(){
        /*//String currentLine = lines.get(0);
        String stringSeparators = "";
        String subline = subLines.get(0);
        int numSepCurentLine;
        int numSepNextLine;
        
        numSepCurentLine = getSeparators(currentLine).split(ESCAPE_SEPARATOR).length - 1;    
        String value = lines.get(0).concat(subline);
        //lines.set(0, value);
        
        for(int i = 1; i < subLines.size(); i++) {
            stringSeparators = "";
            if (rowHasData(i)) {
                numSepNextLine = getSeparators(lines.get(i)).split(ESCAPE_SEPARATOR).length - 1;
                stringSeparators = getStrEmptyChilds(numSepCurentLine - numSepNextLine);
                value = lines.get(i).concat( stringSeparators + subLines.get(i));
                lines.set( i, value);
            } else {
                subline = subLines.get(i);
                stringSeparators = getSeparators(currentLine);
                lines.add(stringSeparators.toString() + subline);
            }
        }*/
    }

    /**
     * Construye un String de <code>length</code> propiedades vacias.
     * @param length longitud.
     * @see ASN1Configuration#CSV_SEPARATOR
     * @return cadena de <code>length</code> propiedades vacias
     */
    private String getStrEmptyChilds(int length) {
        String stringSeparators = "";
        for( int j=0; j< length; j++ ){
            stringSeparators += CSV_SEPARATOR + EMPTY_ATTRIBUTE;
        }
        return stringSeparators;
    }
    
    /**
     * Cuenta cuantos separadores (<code>CSV_SEPARATOR</code>) hay en la cadena 
     * <code>line</code> y construye un String con ese numero de propiedades vacias.
     * @see ASN1Configuration#CSV_SEPARATOR
     * @see ASN1Configuration#EMPTY_ATTRIBUTE
     * @param line  Cadena a evaluar
     * @return cadena con propiedades vacias
     */
    private String getSeparators(String line){
        List<String> separators = Arrays.stream( line.split("") ).
                filter(caracter -> caracter.equals(CSV_SEPARATOR) ).
                map(caracter -> caracter + EMPTY_ATTRIBUTE).collect(Collectors.toList());
        StringBuilder stringSeparators = new StringBuilder();
        separators.forEach( stringSeparators::append ); 
        return stringSeparators.toString();
    }
    
    /**
     * Evalua si la lista <code>lines</code> tiene 
     * un elemento en la posición <code>index</code>.
     * @param index posición o indice de la lista.
     * @return  <code>true</code> si la lista tiene un elemento 
     *          en la posición <code>index</code>.
     */
    private boolean rowHasData(int index) {
        try {
          //  lines.get(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    
    private boolean rowHasData(int index, List<StringBuilder> list) {
        try {
            list.get(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    
   
    
    private void addAttribute(Object record)throws Exception {
        if( record instanceof NullObject ){
            //String emptyFieldValues = buildStringSeparators(record, recordType);
           // innerAttributes.add( emptyFieldValues );
        }else{
            innerAttributes.add(CSV_SEPARATOR + record.toString() );            
        }
    }    

    /**
     * Concatena una propiedad del registro actual 
     * al elemento actual de la lista<code>lines</code>.
     * @param record    Registro
     * @see #buildStringSeparators(java.lang.Object) 
     * @see ASN1Configuration#CSV_SEPARATOR
     * @throws Exception    si ocurre un error al agregar la propiedad    
     */
    public void putLine2(Object record) throws Exception {
       /* String value = "";
        if (record instanceof NullObject) {
            strSeparators = "";
            buildStringSeparators(record, null, null);
            value = strSeparators;
            removeLastParent();
        } else {
            value = CSV_SEPARATOR + record.toString();
            removeLastParent();
        }
        value = lines.get(0) + value;
        lines.set(0, value);*/
    }
    
     public void putLine(Object record) throws Exception {
       /* String value = "";
        if (record instanceof NullObject) {            
            value = buildStringSeparators(record, recordType);
                        
        } else {
            value = CSV_SEPARATOR + record.toString();
            //removeLastParent();
        }
        value = lines.get(0) + value;
        lines.set(0, value);*/
    }
    
    /**
     * Construye cadena de atributos nulos dependiendo del tipo 
     * de clase(clazz) del objeto <code>record</code>
     * @see NullObject
     * @see UtilReflection#filterFields(java.lang.Class) 
     * @see UtilReflection#isPrimitive(java.lang.Class) 
     * @see UtilReflection#getTypeList(java.lang.reflect.Field) 
     * @see ASN1Configuration#CSV_SEPARATOR
     * @param record    instancia de NullObject
     * @throws ClassNotFoundException   si no encuentra una clase X por reflection.  
     * @throws IntrospectionException   si ocurre algun error al obtener los 
     *                                  atributos de <code>nullObject.getClazz()</code>.
     */
   
    private final String EMPTY_FIELD = CSV_SEPARATOR + "NULL";
    private String buildStringSeparators(Object record, String path) 
            throws IntrospectionException, ClassNotFoundException {
          String result = "";
        NullObject nullObject = (NullObject) record;
        List<Field> attributes = UtilReflection.filterFields( nullObject.getClazz() );
        Type listType;
        String className;
        Class clazz;
        String type = nullObject.getClazz().getSimpleName();               
               
        if (isMaximumDeepLevel(type, path)  || attributes.isEmpty() || UtilReflection.isPrimitive( nullObject.getClazz() )) {            
            return EMPTY_FIELD;
        }
                      
        for( Field field: attributes ){           

            if( field.getType().getSimpleName().endsWith("List") ){
                listType = UtilReflection.getTypeList(field);
                className = listType.getTypeName();
                clazz = Class.forName(className);
                result += buildStringSeparators( new NullObject(clazz), path);
            }else{
                result += buildStringSeparators( new NullObject( field.getType() ), path + "." + field.getType().getSimpleName() );
            }
        }       
        return result;
    }
      
    private boolean isMaximumDeepLevel(String type, String path){
        String[] arrayTypes = path.split("\\.");
        long cont = 0;
        if( arrayTypes.length > 0 ){
            cont = Arrays.asList(arrayTypes).stream().filter( innerType -> innerType.equalsIgnoreCase(type) ).count();
        }
        if (cont > DEEP_LEVEL ) {
            System.out.println("ROMPEEE " + type + " PATH " + path);
        }
        return cont > DEEP_LEVEL ;
    }
     
    private boolean isMaximumDeepLevel2(String type){
        return parentTypeExists(type);
//        if (parentTypeExists(type)) {
//            if (lastParentKey == null) {
//                lastParentKey = type;
//                deepLevelCount++;
//            } else if (!lastParentKey.equalsIgnoreCase(type)) {
//                deepLevelCount++;
//            }
//        }
//
//        if (deepLevelCount > DEEP_LEVEL) {
//            lastParentKey = null;
//            deepLevelCount = 0;
//            return true;
//        }
//        return false;
    }
    
    /**
     * Construye objeto <code>Map</code> con los nombres de las columnas del OutputRecord.
     * @see UtilReflection#omitFields(java.lang.reflect.Field) 
     * @see #buildColumnsMapAttributtes(java.lang.Object, java.lang.String, java.lang.String, java.lang.String) 
     * @throws SecurityException    si ocurre un error al extraer las 
     *                              propiedades o atributos del OutputRecord.
     */
    private void buildColumnsMap() throws SecurityException {
        setColumns = new LinkedHashSet<>();
        Arrays.stream( outputRecordClass.getDeclaredFields() ).forEach(choice -> {
            if ( !Modifier.isFinal(choice.getModifiers()) && UtilReflection.omitFields( choice ) ) {
                
                if( choice.getType().getSimpleName().equals("OnlineCreditControlRecord") ){
                    try {
                        Class recordClass = Class.forName( choice.getType().getName() );
                        Object instance = recordClass.newInstance();
                        recordName = instance.getClass().getSimpleName();
                        //recordType = "";
                        header += buildChoiceHeader(instance, recordName, recordName);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        System.out.println(header);
    }
    

   
    private boolean parentNameExists(String key){
        String[] arrayKeys = recordName.split("\\.");
        long cont = 0;
        if( arrayKeys.length > 0 ){
            cont = Arrays.asList(arrayKeys).stream().filter( innerKey -> innerKey.equalsIgnoreCase(key) ).count();
        }
        return cont >= 2 ;
    }
    
    private boolean parentTypeExists(String type){
        return false;
        /*String[] arrayTypes = recordType.split("\\.");
        long cont = 0;
        if( arrayTypes.length > 0 ){
            cont = Arrays.asList(arrayTypes).stream().filter( innerType -> innerType.equalsIgnoreCase(type) ).count();
        }
        return cont > DEEP_LEVEL ;*/
    }
    
    /**
     * Obtiene cada nombre attributo del objeto choice y los agrega al 
     * mapa de columnas junto con su recordType.
     * @param record    objeto choice
     * @param rType identificador completo de propiedad o atributo. Tendrá 
     * la forma: <code>choice.parent-1...parent-N.atributo</code>
     * @param fieldName nombre del atributo padre inmediatamente anterior.
     * @param childName nombre de propiedad o atributo final.
     * @throws Exception    si ocurre algun error en la 
     *                      construcción de los nombres de las columnas.
     */
    private void buildColumnsMapAttributtes(Object record, 
            String rType, String fieldName, String childName) throws Exception {
        List<Field> attributes = filtrar( Arrays.asList( record.getClass().getDeclaredFields() ) );
        
//        recordType = rType != null ? rType + "." + fieldName : recordType;  
        
        if( rType != null ){
            recordName = rType + "." + fieldName;
            if( parentNameExists(fieldName) ){
                if( lastParentKey == null){
                    lastParentKey = fieldName;
                    deepLevelCount++;
                }else if( !lastParentKey.equalsIgnoreCase(fieldName) ){
                    deepLevelCount++;
                }
            }
            
            if( deepLevelCount > DEEP_LEVEL ){
                lastParentKey = null;
                deepLevelCount = 0;
                return;
            }
            
            
        }
        
        if( attributes.isEmpty() ){
//            mapColumns.put( (recordType + "." + childName).toLowerCase(), new ArrayList<>() );
//            setColumns.add((recordType + "." + childName).toLowerCase());
            if(childName == null){
                childName = record.getClass().getSimpleName().toLowerCase();
            }

            addColumn(childName.toLowerCase());
        }
        attributes.stream().forEach(field -> {
            try {
                addElementToMap(field);                                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
    /**
     * Construye nombre de columna y lo agrega al mapa
     * @param field atributo
     * @see #putMapField(java.lang.reflect.Field) 
     * @see UtilReflection#getDeclaredFields(java.lang.reflect.Field) 
     * @see #buildTypeListMap(java.lang.reflect.Field, java.lang.String) 
     * @see #buildColumnsMapAttributtes(java.lang.Object, java.lang.String, java.lang.String, java.lang.String) 
     * @throws Exception    si ocurre un error en la construcción
     */
    private void addElementToMap(Field field) throws Exception {
        long attLength = UtilReflection.getTotalAttributes(field);
        String fieldName = field.getName();

        if (("code").equals(fieldName)) {
            return;
        }

        if (attLength > 0) {
            if (attLength == 1) {
                addColumn(field.getName());
            } else {
                List<Field> innerAtt = filtrar(UtilReflection.getDeclaredFields(field));

                for (Field att : innerAtt) {
                    if (("value").equals(att.getName())) {
                        addColumn(field.getName());
                    } else {
                        if (att.getType().getName().endsWith(".List")) {
                            
                            if(field.getName().equalsIgnoreCase("communitydatainfo")){
                                System.out.println("");
                            }
                            
                            buildTypeListMap(att, field.getName());
                        } else {
                            if (UtilReflection.isPrimitive(att)) {
//                                String key = recordType + "." + field.getName() + "." + att.getName();
//                                mapColumns.put(key.toLowerCase(), new ArrayList<>())
                                String key = field.getName() + "." + att.getName();
                                addColumn(key); 
                            } else {
                                Class c = Class.forName(att.getType().getName());
                                Object instance = c.newInstance();
                                buildColumnsMapAttributtes(instance, recordName, field.getName(), att.getName());
                                removeLastParent();
//                                int lastIndexParent = recordType.lastIndexOf(".");
//                                recordType = recordType.substring(0, lastIndexParent);
                            }
                        }
                    }
                }
            }
        } else {
            if (field.getType().getName().endsWith(".List")) {
                String auxName = field.getName().equals("seqOf") ? field.getDeclaringClass().getSimpleName() : field.getName();
                buildTypeListMap(field, auxName);
            } else {
                addColumn(field.getName());
            }
        }
    }
    
    private void removeLastParent(){
        /*int lastIndexParentName = recordName.lastIndexOf(".");
        int lastIndexParentType = recordType.lastIndexOf(".");
        if( lastIndexParentName >= 0 ){
            recordName = recordName.substring(0, lastIndexParentName);
        }
        if( lastIndexParentType >= 0 ){
             recordType = recordType.substring(0, lastIndexParentType);
        }*/
    }
    
    /**
     * Agrega columna a mapa <code>mapColumns</code>
     * @param field nombre de columna
     */
    private void addColumn(String childKey){
//        String key = recordType + "." + field.getName();
//        mapColumns.put( key.toLowerCase(), new ArrayList<>() );
        String key = recordName + "." + childKey;
        setColumns.add(key.toLowerCase());
//        System.out.println(key.toLowerCase());
    }

    /**
     * Obtiene el tipo de elementos que guarda una lista, crea una instancia 
     * y continua la contruccion del nombre de la columna
     * @param att   atributo Lista
     * @param name  nombre de atributo
     * @throws Exception    si ocurre un error en la construcción
     */
    private void buildTypeListMap(Field att, String name) throws Exception {
        Type listType = UtilReflection.getTypeList(att);
        String className = listType.getTypeName();
        Class c = Class.forName(className);
        Object instance = c.newInstance();
        
        buildColumnsMapAttributtes(instance, recordName, name, null);
        removeLastParent();
//        int lastIndexParent = recordType.lastIndexOf(".");
//        recordType = recordType.substring(0,lastIndexParent);
    }

    /**
     * Filtra el listado <code>declaredFields</code>
     * @param declaredFields    lista de atributos
     * @see UtilReflection#omitFields(java.lang.reflect.Field) 
     * @return lista filtrada
     */
    public static List<Field> filtrar(List<Field> declaredFields) {
        return  declaredFields.stream().filter( 
                field -> UtilReflection.omitFields(field) ).collect(Collectors.toList()); 
    }
    
    public String getOutputRecord() {
        return outputRecord;
    }

    public void setOutputRecord(String outputRecord) {
        this.outputRecord = outputRecord;
    }

    public String getAsnDir() {
        return asnDir;
    }

    public void setAsnDir(String asnDir) {
        this.asnDir = asnDir;
    }

    public String getCdrOutputFolder() {
        return cdrOutputFolder;
    }

    public void setCdrOutputFolder(String cdrOutputFolder) {
        this.cdrOutputFolder = cdrOutputFolder;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Exception> getExceptionList() {
        return exceptionList;
    }

    public void setExceptionList(List<Exception> exceptionList) {
        this.exceptionList = exceptionList;
    }
}
