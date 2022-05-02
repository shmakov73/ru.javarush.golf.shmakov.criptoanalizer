package com.cryptoAnalyzer;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Выберите действие:");
        System.out.println("1. Шифрование текста методом Цезаря");
        System.out.println("2. Расшифровка текста методом Цезаря");
        System.out.println("3. Поиск ключа к зашифрованному тексту");
        System.out.println("4. Выход");

        Scanner console = new Scanner(System.in);
        while (console.hasNextInt()){
            int answer = console.nextInt();
            if (answer == 1){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите адрес файла:");
                String input = scanner.nextLine();
                System.out.println("Введите адрес для сохранения зашифрованного текста:");
                String output = scanner.nextLine();
                System.out.println("Введите ключ шифрования:");
                int key = scanner.nextInt();
                encryptText(input, output, key);
                System.out.println("Зашифрованный текст сохранён в файл: " + output);
                break;
            }
            if (answer == 2){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите адрес файла:");
                String input = scanner.nextLine();
                System.out.println("Введите адрес для сохранения расшифрованного текста:");
                String output = scanner.nextLine();
                System.out.println("Введите ключ шифрования:");
                int key = scanner.nextInt();
                decryptText(input, output, key);
                System.out.println("Расшифрованный текст сохранён в файл: " + output);
                break;
            }
            if (answer == 3){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите адрес файла:");
                String input = scanner.nextLine();
                System.out.println("Ключ к зашифрованному тексту: " + bruteForce(input));
                break;
            }
            if (answer == 4) break;
        }



//********************************** Это для проверки работоспособности кода ******************************************
//        String inputFile = "c:\\test\\doubleText.txt";
//        String outputFile = "c:\\test\\encryptedFile.txt";
//        String decryptedFile = "c:\\test\\decryptedFile.txt";
//
//        int key = 34;
//        encryptText(inputFile, outputFile, key);
//        decryptText(outputFile, decryptedFile, key);
//        bruteForce(outputFile);
//*********************************************************************************************************************

    }

    //*************************** Зашифровка текста ********************************
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
    //************************** Расшифровка текста ********************************
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

    //***************************** Подбор ключа шифрования ***************************
    public static int bruteForce(String inputFile){
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
        int key;
        // в методе используется частотный анализ, информация о частоте использования букв в русском алфавите
        // взята из Википедии.
        for (key = 0; key < aLength; key++) {
            String s = Analyzer.decrypt(string, key);
            if ((Analyzer.letterCount(s, 'е') > Analyzer.letterCount(s, 'и') &
                    Analyzer.letterCount(s, 'и') > Analyzer.letterCount(s, 'с') &
                    Analyzer.letterCount(s, 'с') > Analyzer.letterCount(s, 'у') &
                    Analyzer.letterCount(s, 'у') > Analyzer.letterCount(s, 'ф'))){
                break;
            }
        }return key;
    }
}

