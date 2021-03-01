package com.chntu_team.eapi_lab_1;

public class DoubleTranspositionEncryptor {
    public String encryption(String word, String colKeys, String rowKeys) {

        char[][] firstTable = new char[5][5];
        char[][] secondTable = new char[5][5];

        if (word.length() < 25) {
            for (int i = word.length() + 1; i <= 25; i++) {
                word += " ";
            }
        }

        for (int i = 0; i < firstTable.length; i++) {
            for (int j = 0; j < firstTable.length; j++) {
                firstTable[i][j] = word.charAt(firstTable.length * i + j);
            }
        }

        for (int i = 0; i < firstTable.length; i++) {
            int a = colKeys.charAt(i) - '0' - 1;
            for (int j = 0; j < firstTable.length; j++) {
                secondTable[j][a] = firstTable[j][i];
            }
        }

        for (int i = 0; i < firstTable.length; i++) {
            int a = rowKeys.charAt(i) - '0' - 1;
            for (int j = 0; j < firstTable.length; j++) {
                firstTable[a][j] = secondTable[i][j];
            }
        }

        String result = "";

        for (int i = 0; i < firstTable.length; i++) {
            for (int j = 0; j < firstTable.length; j++) {
                result += firstTable[i][j];
            }
        }
        System.out.println(result);
        return result;
    }

    public String decryption(String word, String colKeys, String rowKeys) {

        char[][] firstTable = new char[5][5];
        char[][] secondTable = new char[5][5];

        for (int i = 0; i < firstTable.length; i++) {
            for (int j = 0; j < firstTable.length; j++) {
                firstTable[i][j] = word.charAt(firstTable.length * i + j);
            }
        }

        for (int i = 0; i < firstTable.length; i++) {
            int a = rowKeys.charAt(i) - '0' - 1;
            for (int j = 0; j < firstTable.length; j++) {
                secondTable[i][j] = firstTable[a][j];
            }
        }

        for (int i = 0; i < firstTable.length; i++) {
            int a = colKeys.charAt(i) - '0' - 1;
            for (int j = 0; j < firstTable.length; j++) {
                firstTable[j][i] = secondTable[j][a];
            }
        }

        String result = "";

        for (int i = 0; i < firstTable.length; i++) {
            for (int j = 0; j < firstTable.length; j++) {
                result += firstTable[i][j];
            }
        }
        System.out.println(result);
        return result;
    }
}
