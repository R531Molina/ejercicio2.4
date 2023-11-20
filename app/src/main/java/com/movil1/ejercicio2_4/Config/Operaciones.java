package com.movil1.ejercicio2_4.Config;

public class Operaciones {

    public static final String NameDatabase="DBSignature";
    public static final int versionDatabase=1;
    public static final String TablaSignature="Signature";

    public static final String Descripcion="Descripcion";
    public static final String ImgFirma="ImgFirma";


    public static final String CreateTableSignature ="CREATE TABLE Signature (Descripcion TEXT,"+"ImgFirma BLOB);";


    public static final String DROPTableSignature ="DROP TABLE IF EXISTS Signature;";
}
