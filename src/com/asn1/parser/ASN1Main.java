package com.asn1.parser;

/**
 * Clase principal que inicia la ejecución
 * @see ASN1Parser
 * @see ASN1Builder
 * @author Efrain.Blanco
 * @author Jhon Fernandez
 */
public class ASN1Main {
    public static void main(String[] args) {
        if( args.length == 0 ){
            System.err.println("No se han especificado los argumentos");
            return;
        }
        String outputRecord = args[0];
        String asnDir = args[1];
        String packageName = args[2];

        try {
            ASN1Parser parser = new ASN1Parser();
            parser.setOutputRecord(outputRecord);
            parser.setAsnDir(asnDir);
            parser.setPackageName(packageName);
            ASN1Builder builder = new ASN1Builder();
            builder.setParser(parser);
            builder.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
