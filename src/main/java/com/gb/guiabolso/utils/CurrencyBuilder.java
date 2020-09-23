package com.gb.guiabolso.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyBuilder {

    public static String formatNumero(Integer valor){
        Locale brazilian = new Locale("pt", "BR");
        NumberFormat brFormat = NumberFormat.getCurrencyInstance(brazilian);
        return brFormat.format(valor);
    }
}
