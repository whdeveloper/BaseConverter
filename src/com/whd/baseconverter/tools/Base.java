package com.whd.baseconverter.tools;

public class Base {
    
    public static String baseToBase(String value, int basein, int baseout) {
        int dec = BaseToDecimal(value, basein);
        return DecimalToBase(dec, baseout);
    }
    
    public static int BaseToDecimal(String other, int base) {
        return Integer.parseInt(other, base);
    }
    
    public static String DecimalToBase(int decimal, int base) {
        return Integer.toString(decimal, base);
    }
    
}