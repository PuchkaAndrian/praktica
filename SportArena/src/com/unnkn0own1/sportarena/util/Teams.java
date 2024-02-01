package com.unnkn0own1.sportarena.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

/**
 * Клас для роботи з iнформацiєю про команди та гравцiв.
 */
public class Teams {

    /**
     * Виводить iнформацiю про всi команди та їх гравцiв з файла JSON.
     *
     * @param filePath Шлях до файлу JSON з iнформацiєю про команди та гравцiв.
     */
    public void printAllTeamsInfo(String filePath) {
        try {
            // Зчитування JSON з файлу
            JsonObject jsonObject = new Gson().fromJson(new FileReader(filePath), JsonObject.class);

            if (jsonObject != null && jsonObject.has("Teams")) {
                JsonArray teamsArray = jsonObject.getAsJsonArray("Teams");

                for (JsonElement teamElement : teamsArray) {
                    printTeamInfo(teamElement.getAsJsonObject());
                }
            } else {
                System.out.println("Невiрний або пустий файл JSON.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Виводить iнформацiю про конкретну команду та її гравцiв.
     *
     * @param teamObject JSON-об'єкт, що представляє iнформацiю про команду.
     */
    private void printTeamInfo(JsonObject teamObject) {
        // Видобування та виведення iнформацiї з JSON-об'єкта
        String teamName = getStringFieldValue(teamObject, "TeamName");
        System.out.println("iнформацiя про команду " + teamName + ":");

        if (teamObject.has("Players")) {
            JsonArray playersArray = teamObject.getAsJsonArray("Players");

            for (JsonElement playerElement : playersArray) {
                printPlayerInfo(playerElement.getAsJsonObject());
            }
        }
        System.out.println();
    }

    /**
     * Виводить iнформацiю про конкретного гравця.
     *
     * @param playerObject JSON-об'єкт, що представляє iнформацiю про гравця.
     */
    private void printPlayerInfo(JsonObject playerObject) {
        // Видобування та виведення iнформацiї з JSON-об'єкта
        String playerName = getStringFieldValue(playerObject, "PlayerName");
        String role = getStringFieldValue(playerObject, "Role");
        int age = getIntFieldValue(playerObject, "Age");
        String country = getStringFieldValue(playerObject, "Country");
        String nextMatch = getStringFieldValue(playerObject, "NextMatch");

        System.out.println("   iнформацiя про гравця " + playerName + ":");
        System.out.println("      Роль: " + role);
        System.out.println("      Вiк: " + age);
        System.out.println("      Країна: " + country);
        if (nextMatch != null) {
            System.out.println("      Наступний матч: " + nextMatch);
        }
    }

    /**
     * Отримує значення рядкового поля з JSON-об'єкта за iменем поля.
     *
     * @param jsonObject JSON-об'єкт.
     * @param fieldName  iм'я поля.
     * @return Значення рядкового поля або пустий рядок, якщо поле не iснує.
     */
    private String getStringFieldValue(JsonObject jsonObject, String fieldName) {
        return jsonObject.has(fieldName) ? jsonObject.get(fieldName).getAsString() : "";
    }

    /**
     * Отримує значення цiлочислового поля з JSON-об'єкта за iменем поля.
     *
     * @param jsonObject JSON-об'єкт.
     * @param fieldName  iм'я поля.
     * @return Значення цiлочислового поля або 0, якщо поле не iснує.
     */
    private int getIntFieldValue(JsonObject jsonObject, String fieldName) {
        return jsonObject.has(fieldName) ? jsonObject.get(fieldName).getAsInt() : 0;
    }
}
