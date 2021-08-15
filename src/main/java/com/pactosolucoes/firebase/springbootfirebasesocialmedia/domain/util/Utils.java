package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.util;

import java.util.UUID;

/**
 * @author laizorrane
 * Data Criacao: 13/08/2021 - 10:25
 */
public class Utils {
    public static String getIdAleatorio(){
        return UUID.randomUUID().toString();
    }
}
