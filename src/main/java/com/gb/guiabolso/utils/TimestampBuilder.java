package com.gb.guiabolso.utils;

import java.sql.Time;
import java.util.Random;

public class TimestampBuilder {

    public static String randomTimestamp(Integer ano, Integer mes) {
        //Gera um horário aleatório
        final Random random = new Random();
        final int millisInDay = 24 * 60 * 60 * 1000;
        Time hora = new Time(random.nextInt(millisInDay));

        //Gera um dia aleatório
        Random rand = new Random();
        Integer dia = rand.nextInt(31);

        //Transforma os dados em string
        String horaToString = hora.toString();
        String anoToString = ano.toString();
        String mesToString = mes.toString();
        String diaToString = dia.toString();

        //Concatena os dados para formaro timestamp
        String data = anoToString.concat("-")
                .concat(mesToString)
                .concat("-")
                .concat(diaToString)
                .concat(" ")
                .concat(horaToString);

        //Retorna o timestamp em string
        return data;
    }
}

