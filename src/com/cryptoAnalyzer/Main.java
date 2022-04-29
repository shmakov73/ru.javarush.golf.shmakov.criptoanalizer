package com.cryptoAnalyzer;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int key = 5;
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
                String encryptLine =Analyzer.encrypt(line, key);
                writer.write(encryptLine + "\n");
                text.append(encryptLine);
                writer.flush();
            }

            int s = Analyzer.letterCount(text, 'а');
            int s1 = Analyzer.letterCount(text, 'с');

        }
        try (FileReader in = new FileReader(outputFile);
             BufferedReader reader = new BufferedReader(in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(decryptedFile))){
            while (reader.ready()){
                String line = reader.readLine();
                System.out.println(line);
                String decryptLine = Analyzer.decrypt(line, key);
                writer.write(decryptLine + "\n");
                writer.flush();
            }
        }
        int aLength = Analyzer.alphaBet.length;
        for (int i = 0; i < aLength; i++) {
            String s = Analyzer.decrypt(outputFile, i);
            if (Analyzer.letterCount(text, 'о') > Analyzer.letterCount(text, 'е') &&
                    Analyzer.letterCount(text, 'е') > Analyzer.letterCount(text, 'и') &&
                    Analyzer.letterCount(text, 'и') > Analyzer.letterCount(text, 'с') &&
                    Analyzer.letterCount(text, 'с') > Analyzer.letterCount(text, 'у') &&
                    Analyzer.letterCount(text, 'у') > Analyzer.letterCount(text, 'ф')){
                System.out.println(i);
                break;
            }
        }
    }
}
