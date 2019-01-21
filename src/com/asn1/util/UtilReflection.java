package com.asn1.util;

import static com.asn1.parser.ASN1Configuration.PRIMITIVES;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase Utilitaria para el manejo de Instrospection y Reflection.
 * @author Efrain.Blanco
 * @author Jhon Fernandez
 * @version 1.0
 */
public class UtilReflection {
    
    /**
     * Devuelve las propiedades de un objeto haciendo un filtro por 
     * tipo de modificador y omitiendo un conjunto de atributos especificos.
     * @param record    objeto
     * @see #omitProps(java.beans.PropertyDescriptor) 
     * @return  lista de propiedades filtrada
     * @throws IntrospectionException   si ocurre un error al 
     *                                  obtener las propiedades del objeto
     */
    public static List<PropertyDescriptor> filterProperties(Object record) throws IntrospectionException {
        PropertyDescriptor[] properties = Introspector.getBeanInfo(record.getClass()).getPropertyDescriptors();
        List<PropertyDescriptor> filterProperties = Arrays.stream( properties ).
                filter( prop ->
                        !Modifier.isFinal( prop.getClass().getModifiers() )
                                && omitProps(prop) ).collect(Collectors.toList());
        return filterProperties;
    }
    
    /**
     * Devuelve los atributos de un objeto haciendo un filtro por 
     * tipo de modificador, Tipo de dato y omitiendo un conjunto 
     * de atributos especificos.
     * @see #isPrimitive(java.lang.Class) 
     * @see #omitFields(java.lang.reflect.Field) 
     * @param record    objeto
     * @return  lista filtrada
     */
    public static List<Field> filterFields(Object record){
        if( isPrimitive( record.getClass() ) ){
            return new ArrayList();
        }
        Field[] properties = record.getClass().getDeclaredFields();
        List<Field> filterProperties = Arrays.stream( properties ).
                filter( field ->
                        !Modifier.isFinal( field.getModifiers() )
                                && omitFields(field) ).collect(Collectors.toList());
        return filterProperties;
    }
    
    /**
     * Devuelve los atributos de una Clase haciendo un filtro por 
     * tipo de modificador y omitiendo un conjunto de atributos especificos.
     * @param clazz clase
     * @see #omitFields(java.lang.reflect.Field) 
     * @return  lista filtrada
     */
    public static List<Field> filterFields(Class clazz){
        Field[] properties = clazz.getDeclaredFields();
        List<Field> filterProperties = Arrays.stream( properties ).
                filter( field ->
                        !Modifier.isFinal( field.getModifiers() )
                                && omitFields(field) ).collect(Collectors.toList());
        return filterProperties;
    }
    
    /**
     * Devuelve los atributos de un objeto <code>Field</code> omitiendo los tipo Final.
     * @param field objeto <code>Field</code>
     * @return  Lista de atributos
     */
    public static List<Field> getDeclaredFields(Field field){
        return Arrays.asList( field.getType().getDeclaredFields( )).
                stream().filter( att -> !Modifier.isFinal(att.getModifiers()) ).collect(Collectors.toList());
    }
    
    /**
     * Indica si el objeto <code>field</code> es primitivo.
     * @param field objeto <code>field</code>
     * @return <code>true</code> si <code>field</code> es primitivo
     */
    public static boolean isPrimitive(Field field){
        Optional<String> op = Arrays.stream(PRIMITIVES).filter( prim -> field.getType().getName().endsWith( prim ) ).findFirst();
        return op.isPresent() || field.getType().isPrimitive();
    }
    
    /**
     * Indica si el objeto <code>clazz</code> es primitivo.
     * @param clazz objeto <code>clazz</code>
     * @return <code>true</code> si <code>clazz</code> es primitivo
     */
    public static boolean isPrimitive(Class clazz){
        Optional<String> op = Arrays.stream(PRIMITIVES).filter( prim -> clazz.getName().endsWith( prim ) ).findFirst();
        return op.isPresent() || clazz.isPrimitive();
    }
    
    /**
     * Devuelve el numero total de atributos del objeto 
     * <code>field</code> omitiendo los Final.
     * @param field objeto <code>field</code>
     * @return  numero total de atributos
     */
    public static long getTotalAttributes(Field field){
        return Arrays.stream( field.getType().getDeclaredFields() ).
                filter( att -> !Modifier.isFinal(att.getModifiers()) ).count();
    }
    
    /**
     * Devuelve el tipo de objeto que permite una lista especifica.
     * @param field objeto lista
     * @return  tipo de objeto que permite la lista
     */
    public static Type getTypeList(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) field.getGenericType();
            for (Type type : pt.getActualTypeArguments()) {
                return type;
            }
        }
        return null;
    }
    
    /**
     * Evalua si el objeto <code>field</code> es un atributo valido.
     * @param field objeto <code>field</code>
     * @return  <code>true</code> si el objeto 
     *          <code>field</code> es un atributo valido. 
     */
    public static boolean omitFields(Field field) {
        return !field.getName().equals("serialVersionUID") && 
                !field.getName().equals("tag") &&
                !field.getName().equals("code") &&
                !field.getName().equals("class");
    }

    /**
     * Evalua si el objeto <code>prop</code> es una propiedad valida.
     * @param prop objeto <code>prop</code>
     * @return  <code>true</code> si el objeto 
     *          <code>prop</code> es una propiedad valida. 
     */
    public static boolean omitProps(PropertyDescriptor prop) {
        return !prop.getName().equals("serialVersionUID") && 
                !prop.getName().equals("tag") &&
                !prop.getName().equals("code") &&
                !prop.getName().equals("class");
    }
}
