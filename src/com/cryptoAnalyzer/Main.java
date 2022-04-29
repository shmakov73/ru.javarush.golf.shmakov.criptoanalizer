package com.cryptoAnalyzer;

import java.io.*;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        int key = 34;
//        Scanner console = new Scanner(System.in);
//        String line1 = console.nextLine();
        String inputFile = "c:\\test\\doubleText.txt";
        String outputFile = "c:\\test\\encryptedFile.txt";
        String decryptedFile = "c:\\test\\decryptedFile.txt";

        encryptText(inputFile, outputFile, key);
        decryptText(outputFile, decryptedFile, key);
        bruteForce(outputFile);
    }


    public static void encryptText(String inputFile, String outputFile, int key){
        try (FileReader in = new FileReader(inputFile);
             BufferedReader reader = new BufferedReader(in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            while (reader.ready()){
                StringBuilder line = new StringBuilder(reader.readLine());
                String encryptedLine = Analyzer.encrypt(line, key);
                writer.write(encryptedLine + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decryptText(String inputFile, String outputFile, int key){
        try (FileReader in = new FileReader(inputFile);
             BufferedReader reader = new BufferedReader(in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            while (reader.ready()){
                StringBuilder line = new StringBuilder(reader.readLine());
                String decryptedLine = Analyzer.decrypt(line, key);
                writer.write(decryptedLine + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bruteForce(String inputFile){
        StringBuilder string = new StringBuilder();
        try (FileReader in = new FileReader(inputFile);
             BufferedReader reader = new BufferedReader(in)){
            while (reader.ready()){
                string.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int aLength = Analyzer.alphaBet.length;
        for (int i = 0; i < aLength; i++) {
            String s = Analyzer.decrypt(string, i);
            if ((Analyzer.letterCount(s, 'е') > Analyzer.letterCount(s, 'и') &
                    Analyzer.letterCount(s, 'и') > Analyzer.letterCount(s, 'с') &
                    Analyzer.letterCount(s, 'с') > Analyzer.letterCount(s, 'у') &
                    Analyzer.letterCount(s, 'у') > Analyzer.letterCount(s, 'ф'))){
                System.out.println(i);
                break;
            }
        }
    }
}

