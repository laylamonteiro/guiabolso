package com.gb.guiabolso.utils;

import java.sql.Time;
import java.util.Random;

public class TimestampBuilder {

    public static String randomTimestamp(Integer ano, Integer mes) {
        final Random random = new Random();
        final int millisInDay = 24 * 60 * 60 * 1000;
        Time hora = new Time(random.nextInt(millisInDay));

        String horaToString = hora.toString();
        String anoToString = ano.toString();
        String mesToString = mes.toString();
        String data = anoToString.concat("-").concat(mesToString).concat("-01 ").concat(horaToString);

        return data;
    }
}

