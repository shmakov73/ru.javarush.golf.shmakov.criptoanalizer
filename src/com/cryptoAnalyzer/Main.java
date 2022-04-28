package com.cryptoAnalyzer;

import java.io.*;

public class Main {
    static String stringAlphaBet = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя.,”:-!? ";
    public static char[] alphaBet = stringAlphaBet.toCharArray();

    public static void main(String[] args) throws IOException {
        int key = 0;
//        Scanner console = new Scanner(System.in);
//        String line1 = console.nextLine();
        String inputFile = "c:\\test\\tolstoy.txt";
        String outputFile = "c:\\test\\encryptedFile.txt";
        String decryptedFile = "c:\\test\\decryptedFile.txt";
        StringBuilder text = new StringBuilder();

        try (FileReader in = new FileReader(inputFile);
             BufferedReader reader = new BufferedReader(in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            while (reader.ready()){
                String line = reader.readLine();
                //System.out.println(line);
                String encryptLine = encrypt(line, key);
                writer.write(encryptLine + "\n");
                text.append(encryptLine);
                writer.flush();
            }
            int c = 0;
            char[] ch = text.toString().toCharArray();
            for (int i = 0; i < text.length(); i++) {
                if (ch[i] == 'ъ'){
                    c++;
                }
            }
            System.out.println(c);

        }
        System.out.println(text.length());

//        try (FileReader in = new FileReader(outputFile);
//             BufferedReader reader = new BufferedReader(in);
//             BufferedWriter writer = new BufferedWriter(new FileWriter(decryptedFile))){
//            while (reader.ready()){
//                String line = reader.readLine();
//                System.out.println(line);
//                String decryptLine = decrypt(line, key);
//                writer.write(decryptLine + "\n");
//                writer.flush();
//            }
//        }
// *************************** зашифровка текста сдвигом по алфавиту на значение key ***************************
    }
    public static String encrypt(String stringMessage, int cryptoKey){
        char[] message = stringMessage.toCharArray();
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

    //********************************* расшифровка текста сдвигом по алфавиту на значение key *************************

    public static String decrypt(String encryptedText, int cryptoKey){
        char[] text = encryptedText.toCharArray();
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
