package com.cryptoAnalyzer;

public class Analyzer {
    static String stringAlphaBet = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя.,”:-!? ";
    public static char[] alphaBet = stringAlphaBet.toCharArray();

    //************************ Подсчёт количества символов "symbol" в переданном тексте *******************************
    public static int letterCount(String text, char symbol){
        int symbCount = 0;                          // переменная-счётчик (для подсчёта количества символов)
        char[] ch = text.toCharArray();             //переводим текст в массив символов
        for (int i = 0; i < text.length(); i++) {   //цикл по массиву символов текста
            if (ch[i] == symbol) {                  //когда встречается нужный символ
                symbCount++;                        //увеличиваем счётчик на 1
            }
        }
        return symbCount;
    }

    //************************ Шифрование текста сдвигом по алфавиту на значение key***********************************
    public static String encrypt(StringBuilder stringMessage, int cryptoKey){
        char[] text = stringMessage.toString().toCharArray(); //создаём массив символов на основе переданного текста
        char[] result = new char[text.length];                //массив для хранения результата обработки текста
        for (int i = 0; i < text.length; i++) {               //цикл по массиву символов тескта
            for (int j = 0; j < alphaBet.length; j++) {        //цикл по алфавиту
                if (text[i] == alphaBet[j]){                    //находим нужный символ в алфавите
                    result[i] = alphaBet[(alphaBet.length + j + cryptoKey) % alphaBet.length];
                } // берём символ, находящийся на cryptoKey расстоянии от него и записывает в массив result
            }
        }
        return String.valueOf(result);   //возвращаем строковое представление массива result

    }

    //************************ Расшифровка текста сдвигом по алфавиту на значение key **********************************
    //работа метода аналогична методу decrypt, только cryptoKey со знаком "-" (отсчёт в обратную сторону по алфавиту)
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
