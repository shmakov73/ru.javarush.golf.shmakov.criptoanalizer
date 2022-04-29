package com.cryptoAnalyzer;

public class Analyzer {
    static String stringAlphaBet = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя.,”:-!? ";
    public static char[] alphaBet = stringAlphaBet.toCharArray();

    public static int letterCount(String text, char symbol){
        int c = 0;
        char[] ch = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            if (ch[i] == symbol) {
                c++;
            }
        }
        return c;
    }

    public static String encrypt(StringBuilder stringMessage, int cryptoKey){
        char[] message = stringMessage.toString().toCharArray();
        char[] result = new char[message.length];
        for (int i = 0; i < message.length; i++) {
            for (int j = 0; j < alphaBet.length; j++) {
                if (message[i] == alphaBet[j]){
                    result[i] = alphaBet[(alphaBet.length + j + cryptoKey) % alphaBet.length];
                }
            }
        }
        return String.valueOf(result);

    }

    public static String decrypt(StringBuilder encryptedText, int cryptoKey){
        char[] text = encryptedText.toString().toCharArray();
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < alphaBet.length; j++) {
                if (text[i] == alphaBet[j]){
                    result[i] = alphaBet[(alphaBet.length + j - cryptoKey) % alphaBet.length];
                }
            }
        }
        return String.valueOf(result);
    }
}
