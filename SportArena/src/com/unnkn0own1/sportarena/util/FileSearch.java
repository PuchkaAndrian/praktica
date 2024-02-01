package com.unnkn0own1.sportarena.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Утилiтарний клас для пошуку ключового слова у файлах.
 */
public class FileSearch {

    /**
     * Головний метод для введення ключового слова та виведення результатiв пошуку у файлi "Tops.json".
     *
     * @param args Аргументи командного рядка (не використовуються в цьому випадку).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введiть ключове слово для пошуку:");
        String keyword = scanner.nextLine();

        String dataPath = "Data/";

        List<String> topsLines = readFile(dataPath + "Tops.json");

        System.out.println("Результати пошуку в Tops:");
        searchAndPrintLines(topsLines, keyword);

        scanner.close();
    }

    /**
     * Зчитує вмiст файлу та повертає його у виглядi списку рядкiв.
     *
     * @param filename iм'я файлу для зчитування.
     * @return Список рядкiв з вмiстом файлу.
     */
    private static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Виконує пошук та виведення рядкiв, якi мiстять ключове слово у заданому списку рядкiв.
     *
     * @param lines   Список рядкiв для пошуку.
     * @param keyword Ключове слово для пошуку.
     */
    private static void searchAndPrintLines(List<String> lines, String keyword) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int index = line.indexOf(keyword);
            while (index != -1) {
                int startIndex = line.lastIndexOf("{", index);
                int endIndex = line.indexOf("}", index) + 1;
                if (startIndex != -1 && endIndex != 0) {
                    String substring = line.substring(startIndex, endIndex);
                    System.out.println("Рядок " + (i + 1) + ": " + substring);
                }
                index = line.indexOf(keyword, index + 1);
            }
        }
    }

    /**
     * Метод для використання у iнших класах для виводу результатiв пошуку в файлi "Tops.json".
     *
     * @param dataPath Шлях до каталогу даних.
     * @param keyword  Ключове слово для пошуку.
     */
    public static void searchAndPrintResults(String dataPath, String keyword) {
        List<String> topsLines = readFile(dataPath + "Tops.json");

        System.out.println("Результати пошуку в Tops:");
        searchAndPrintLines(topsLines, keyword);
    }
}
