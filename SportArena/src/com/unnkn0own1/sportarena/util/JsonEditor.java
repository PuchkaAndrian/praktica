package com.unnkn0own1.sportarena.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Утилiтарний клас для редагування JSON-даних.
 */
public class JsonEditor {

    private final String filename;
    private final JSONArray topsData;

    /**
     * Конструктор, який приймає iм'я файлу JSON для редагування.
     *
     * @param filename iм'я файлу JSON.
     */
    public JsonEditor(String filename) {
        this.filename = filename;
        this.topsData = loadTopsData();
    }

    /**
     * Завантажує данi з файлу JSON i повертає масив об'єктiв.
     *
     * @return Масив JSON-об'єктiв.
     */
    private JSONArray loadTopsData() {
        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(reader);
            return (JSONArray) jsonData.get("Tops");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Зберiгає данi у файл JSON.
     */
    private void saveData() {
        try (FileWriter writer = new FileWriter(filename)) {
            JSONObject jsonData = new JSONObject();
            jsonData.put("Tops", topsData);
            writer.write(jsonData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Оновлює значення "winStreak" для гравця за iм'ям у списку JSON-даних.
     *
     * @param playerName  iм'я гравця.
     * @param newWinStreak Нове значення "winStreak".
     */
    public void updatePlayerWinStreakByName(String playerName, int newWinStreak) {
        for (Object player : topsData) {
            if (player instanceof JSONObject) {
                JSONObject playerObject = (JSONObject) player;
                if (playerObject.containsKey("playerName") && playerObject.get("playerName").equals(playerName)) {
                    playerObject.put("winStreak", newWinStreak);
                    break;
                }
            }
        }
        saveData();
    }

    /**
     * Повертає данi зi списку JSON-даних.
     *
     * @return Список JSON-об'єктiв.
     */
    public List<Object> getTopsData() {
        return topsData;
    }

    /**
     * Метод для прикладу використання.
     *
     * @param args Аргументи командного рядка (не використовуються в цьому випадку).
     */
    public static void main(String[] args) {
        // Приклад використання:
        JsonEditor topsEditor = new JsonEditor("Data/Tops.json");

        // Оновлення значення "winStreak" для гравця "Miposhka" на 5
        topsEditor.updatePlayerWinStreakByName("Miposhka", 5);

        // Виведення оновлених даних
        System.out.println(topsEditor.getTopsData());
    }
}
