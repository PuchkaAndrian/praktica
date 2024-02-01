package com.unnkn0own1.sportarena.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Клас для виведення iнформацiї про турнiри.
 */
public class Tournament {

    /**
     * Виводить iнформацiю про всi подiї з файлу JSON.
     *
     * @param filePath Шлях до файлу JSON з iнформацiєю про турнiри.
     */
    public void printAllEventInfo(String filePath) {
        try {
            // Зчитування вмiсту JSON-файлу в рядок
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Розбиває JSON-рядок на окремi турнiри
            String[] tournaments = content.split("\\},\\s*\\{");
            for (String tournament : tournaments) {
                printEventInfo(tournament);
                System.out.println(); // Додати порожнiй рядок мiж подiями для кращого виведення
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Виводить iнформацiю про конкретний турнiр.
     *
     * @param tournament Рядок JSON, що представляє iнформацiю про турнiр.
     */
    public void printEventInfo(String tournament) {
        // Видобуває та виводить iнформацiю з JSON-об'єкта
        String match = parseFieldValue(tournament, "match");
        String time = parseFieldValue(tournament, "time");
        String tournamentName = parseFieldValue(tournament, "tournament");

        System.out.println("Iнформацiя про подiю:");
        System.out.println("   Матч: " + match);
        System.out.println("   Час: " + time);
        System.out.println("   Турнiр: " + tournamentName);
    }

    /**
     * Видобуває значення поля з JSON-рядка за iм'ям поля.
     *
     * @param jsonString Рядок JSON.
     * @param fieldName  iм'я поля.
     * @return Значення поля або null, якщо поле не iснує.
     */
    private String parseFieldValue(String jsonString, String fieldName) {
        // Знаходить значення поля за iм'ям
        String[] tokens = jsonString.split("\"" + fieldName + "\":");
        if (tokens.length > 1) {
            String fieldValue = tokens[1].split(",")[0].replaceAll("[\"{}]", "").trim();
            return "null".equals(fieldValue) ? null : fieldValue;
        }
        return null;
    }
}
