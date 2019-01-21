package com.asn1.parser;

/**
 * Interfaz que contiene parametros de configuración necesarios para la decodificación
 * @author Efrain.Blanco
 */
public interface ASN1Configuration {
    public static final String[] PRIMITIVES = {"BerInteger","BerBoolean","Integer64","Date","BerUTF8String"};
    public static final int ERROR_CHOICE = 7;
    public static final int REFILL_CHOICE = 6;
    public static final int OFFLINE_CHOICE = 5;
    public static final int ADJUSTMENT_CHOICE = 4;
    public static final String SHEET_NAME = "CDR_AIR";
    public static final String CSV_SEPARATOR = "~";
    public static final String ESCAPE_SEPARATOR = "\\~";
    public static final String DECODE_METHOD = "decode";
    public static final String IN_PROCESS_FOLDER = "prc";
    public static final String LOG_FILENAME = "ASN";
    public static final String EXTENSION_LOG_FILE = ".log";
    public static final String LOGGER_NAME = "LogASN";
    public static final String LOGS_FOLDERNAME = "logs";
    public static final String OUT_FOLDERNAME = "out";
    public static final String ERROR_FOLDERNAME = "err";
    public static final String CDR_OUTPUT_FOLDERNAME = "cdr-out";
    public static final String EMPTY_ATTRIBUTE = "-";
}
