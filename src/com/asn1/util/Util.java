package com.asn1.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase utilitaria
 * @author Efrain.Blanco
 * @version 1.0
 */
public class Util {
    public static String getStrDateTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return format.format( new Date() );
    }
    
    public static String getStackTrace(Exception exception){
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        return writer.toString();
    }
}
