package com.asn1.parser;

import com.asn1.exception.DecoderException;
import static com.asn1.parser.ASN1Configuration.LOGGER_NAME;
import com.asn1.util.Util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase que gestiona las N decodificaciones que se realizen
 * @author Efrain.Blanco
 * @author Jhon Fernandez
 */
public class ASN1Builder implements ASN1Configuration{
    
   private static final Logger LOGGER = Logger.getLogger(LOGGER_NAME); 
   private ASN1Parser parser;
   private String cdrOutputFolder;
    
   /**
    * Lee directorio <code>IN_PROCESS_FOLDER</code> y envía los 
    * CDRs encontrados a decodificación
    * @see #initLog() 
    * @see #writeLog(java.lang.String, java.util.logging.Level) 
    * @see #createCdrOutputFolder(java.lang.String) 
    * @see #moveFile(java.nio.file.Path, boolean) 
    * @see ASN1Configuration#IN_PROCESS_FOLDER
    * @throws IOException   si la ruta <code>inProcessPath</code> está errada
    */
   public void run() throws IOException {
        initLog();
        Path inProcessPath = Paths.get(parser.getAsnDir() + File.separator + IN_PROCESS_FOLDER);
        
        Files.list(inProcessPath).forEach(cdrPath -> {
            try {
                writeLog("Decodificando archivo " + cdrPath.toString(), Level.INFO);
                createCdrOutputFolder(cdrPath.toString());
                parser.setCdrOutputFolder(cdrOutputFolder);
                parser.run(cdrPath.toString());
                
                if( !parser.getExceptionList().isEmpty() ){
//                    throw new DecoderException("Ocurrió un error al decodificar el archivo");
                }                
//                moveFile(cdrPath, false);
                writeLog("Archivo " + cdrPath.toString() + " decodificado con éxito", Level.INFO);
            } catch (DecoderException ex) {
//                moveFile(cdrPath, true);
                writeLog("Error: " + ex.getMessage(), Level.SEVERE);
                parser.getExceptionList().
                        forEach(e -> writeLog("Error: " + e.getMessage() 
                                + " Causa: \n" + Util.getStackTrace(ex), Level.SEVERE));
            } catch (Exception ex) {
//                moveFile(cdrPath, true);
                writeLog("Error: " + ex.getMessage() + " Causa: \n" + Util.getStackTrace(ex), Level.SEVERE);
            }
        });
    }
    
    /**
     * Inicializa log 
     * @see Util#getStrDateTime() 
     * @see ASN1Configuration#LOGS_FOLDERNAME
     * @see ASN1Configuration#LOG_FILENAME
     * @see ASN1Configuration#EXTENSION_LOG_FILE
     * @throws IOException  si el directorio de logs está errado
     */
    public void initLog() throws IOException{
        FileHandler fileHandler = new FileHandler( parser.getAsnDir() + File.separator + 
                LOGS_FOLDERNAME + File.separator + LOG_FILENAME + 
                Util.getStrDateTime() + EXTENSION_LOG_FILE);
        SimpleFormatter formatter = new SimpleFormatter();  
        fileHandler.setFormatter(formatter);
        LOGGER.addHandler(fileHandler);
        LOGGER.info("Inicio Log");  
    }
    
    /**
     * Escribe en el archivo Log
     * @param message   texto a escribir
     * @param level tipo de mensaje <code>Level.INFO, Level.SEVERE ...</code>
     */
    public void writeLog(String message, Level level){
        LOGGER.log(level, message);
    }
    
    /**
     * Mueve el CDR procesado a carpeta de salida o de error dependiendo del caso
     * @see ASN1Configuration#OUT_FOLDERNAME
     * @see ASN1Configuration#ERROR_FOLDERNAME
     * @param file  CDR Procesado
     * @param isError   indica si hubo un error en la decodificación
     */
    public void moveFile(Path file, boolean isError ){
        Path destPath;
        String fileName = file.toString().substring(file.toString().lastIndexOf("\\"));
        if(isError){
            destPath = Paths.get(parser.getAsnDir() + File.separator + ERROR_FOLDERNAME + fileName);
        }else{
            destPath = Paths.get(parser.getAsnDir() + File.separator + OUT_FOLDERNAME + fileName);
        }
        try {
            Files.move(file, destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    /**
     * Crea folder de salida de CDR(Decodificado) donde se 
     * guardarán N archivos de texto, donde N = # choices.
     * <ul>
     * <li>Ejemplo:</li>
     * <li>Esquema: air.asn</li>
     * <li>OutputRecord: DetailOutputRecord</li>
     * <li>Choices: RefillRecordV2, AdjustmentRecordV2</li>
     * <li>CDR a decodificar: Archivo_cdr.air</li>
     * <li>Nombre de folder de salida:  Archivo_cdr</li>
     * <li>Contenido de folder de salida:  RefillRecordV2.txt, AdjustmentRecordV2.txt</li>
     * </ul>
     * @param dirFile   Ubicación del CDR en procesamiento
     * @throws IOException  si ocurre algun error en la creación del 
     *                      directorio de salida del CDR en procesamiento 
     */
    private void createCdrOutputFolder(String dirFile) throws IOException{
        String fileName = dirFile.substring(dirFile.lastIndexOf("\\")).split("\\.")[0];
        Path cdrOutputPath = Paths.get(parser.getAsnDir() + 
                File.separator + CDR_OUTPUT_FOLDERNAME + File.separator + fileName);
        
        if (!Files.exists(cdrOutputPath)){
            Files.createDirectories(cdrOutputPath);
        }
        cdrOutputFolder = cdrOutputPath.toString();
    }
    
    public ASN1Parser getParser() {
        return parser;
    }

    public void setParser(ASN1Parser parser) {
        this.parser = parser;
    }
}
