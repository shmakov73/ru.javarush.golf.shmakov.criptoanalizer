package com.cryptoAnalyzer;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static final String INPUT = "Введите адрес файла:";
    private static final String OUTPUT = "Зашифрованный текст сохранён в файл: ";
    private static final String ENTER_KEY = "Введите ключ шифрования:";

    public static void main(String[] args) {

        print("Выберите действие:");
        print("1. Шифрование текста методом Цезаря");
        print("2. Расшифровка текста методом Цезаря");
        print("3. Поиск ключа к зашифрованному тексту");
        print("4. Выход");

        Scanner console = new Scanner(System.in);
        while (console.hasNextInt()){
            int answer = console.nextInt();
            if (answer == 1){
                Scanner scanner = new Scanner(System.in);
                print(INPUT);
                String input = scanner.nextLine();
                String output = outputFile(input, "\\encryptedFile.txt");
                print(ENTER_KEY);
                int key = scanner.nextInt();
                encryptText(input, output, key);
                print(OUTPUT + output);
            }
            if (answer == 2){
                Scanner scanner = new Scanner(System.in);
                print(INPUT);
                String input = scanner.nextLine();
                String output = outputFile(input, "\\decryptedFile.txt");
                print(ENTER_KEY);
                int key = scanner.nextInt();
                decryptText(input, output, key);
                print("Расшифрованный текст сохранён в файл: " + output);
            }
            if (answer == 3){
                Scanner scanner = new Scanner(System.in);
                print(INPUT);
                String input = scanner.nextLine();
                print("Ключ к зашифрованному тексту: " + bruteForce(input));
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
    public static void print(String text){ //метод для вывода в консоль
        System.out.println(text);
    }


    public static String outputFile(String input, String output){ // создание адреса файла для сохранения
        File file = new File(input);
        File out = new File(file.getAbsoluteFile().getParentFile() + output);
        return String.valueOf(out);
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
            if ((Analyzer.letterCount(s, 'е') > Analyzer.letterCount(s, 'и') &&
                    Analyzer.letterCount(s, 'и') > Analyzer.letterCount(s, 'с') &&
                    Analyzer.letterCount(s, 'с') > Analyzer.letterCount(s, 'у') &&
                    Analyzer.letterCount(s, 'у') > Analyzer.letterCount(s, 'ф'))){
                break;
            }
        }return key;
    }
}

