package com.unnkn0own1.sportarena.util;

import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * Клас для виведення iнформацiї про топових гравцiв.
 */
public class Tops {

    /**
     * Виводить iнформацiю про топових гравцiв з файлу JSON.
     *
     * @param filePath Шлях до файлу JSON з iнформацiєю про топових гравцiв.
     */
    public void printTopPlayers(String filePath) {
        try {
            // Зчитування JSON з файлу
            JsonObject jsonObject = new Gson().fromJson(new FileReader(filePath), JsonObject.class);

            if (jsonObject != null) {
                // Припускаючи, що топовi гравцi зберiгаються в властивостi з iм'ям "Tops"
                JsonArray jsonArray = jsonObject.getAsJsonArray("Tops");

                if (jsonArray != null) {
                    for (JsonElement jsonElement : jsonArray) {
                        JsonObject playerObject = jsonElement.getAsJsonObject();
                        printPlayerInfo(playerObject);
                    }
                } else {
                    System.out.println("Помилка: поле 'Tops' не знайдено у файлi або воно не є масивом.");
                }
            } else {
                System.out.println("Помилка читання файлу або файл порожнiй.");
            }

        } catch (JsonParseException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Виводить iнформацiю про конкретного гравця.
     *
     * @param playerObject JSON-об'єкт, що представляє iнформацiю про гравця.
     */
    private void printPlayerInfo(JsonObject playerObject) {
        System.out.println("Iнформацiя про гравця:");
        System.out.println("Iм'я: " + getStringFieldValue(playerObject, "playerName"));
        System.out.println("Краiна: " + getStringFieldValue(playerObject, "country"));

        if (playerObject.has("winStreak")) {
            System.out.println("Win Streak: " + parseIntFieldValue(playerObject, "winStreak"));
        }

        if (playerObject.has("lossStreak")) {
            System.out.println("Loss Streak: " + parseIntFieldValue(playerObject, "lossStreak"));
        }

        System.out.println();
    }

    /**
     * Отримує значення рядкового поля з JSON-об'єкта за iменем поля.
     *
     * @param jsonObject JSON-об'єкт.
     * @param fieldName  iм'я поля.
     * @return Значення рядкового поля або порожнiй рядок, якщо поле не iснує або не є примiтивом JSON.
     */
    private String getStringFieldValue(JsonObject jsonObject, String fieldName) {
        JsonElement jsonElement = jsonObject.get(fieldName);
        if (jsonElement != null && jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsString();
        }
        return ""; // Або iнше значення за замовчуванням, якщо поле вiдсутнє або не є примiтивом JSON
    }

    /**
     * Отримує значення цiлочислового поля з JSON-об'єкта за iменем поля.
     *
     * @param jsonObject JSON-об'єкт.
     * @param fieldName  iм'я поля.
     * @return Значення цiлочислового поля або 0, якщо поле не iснує або не є примiтивом JSON.
     */
    private int parseIntFieldValue(JsonObject jsonObject, String fieldName) {
        JsonElement jsonElement = jsonObject.get(fieldName);
        if (jsonElement != null && jsonElement.isJsonPrimitive()) {
            String stringValue = jsonElement.getAsString();
            try {
                return Integer.parseInt(stringValue.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0; // Або iнше значення за замовчуванням, якщо поле вiдсутнє або не є примiтивом JSON
    }
}
