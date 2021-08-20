package com.trade.project.store.application;

import java.util.Random;

public class StoreNameGenerator {
    private static final char[] CHARACTER_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    private static final int LENGTH = 6;

    public static String createName() {
        Random random = new Random();

        StringBuffer generateName = new StringBuffer();

        for(int i = 0; i < LENGTH; i++) {
            generateName.append(CHARACTER_TABLE[random.nextInt(CHARACTER_TABLE.length)]);
        }

        return generateName.toString();
    }
}
