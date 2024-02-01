package com.unnkn0own1.sportarena;

import com.unnkn0own1.sportarena.util.Authorisation;
import com.unnkn0own1.sportarena.util.Registration;
import com.unnkn0own1.sportarena.util.Tops;
import com.unnkn0own1.sportarena.util.Tournament;
import com.unnkn0own1.sportarena.util.Teams;
import com.unnkn0own1.sportarena.util.JsonEditor;
import com.unnkn0own1.sportarena.util.FileSearch;

import java.util.Scanner;

/**
 * Головний клас для виконання додатка Sport Arena.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dataPath = "Data/";

        boolean isAuthenticated = false;
        int userId = -1;

        JsonEditor topsEditor = new JsonEditor(dataPath + "Tops.json");
        JsonEditor tournamentEditor = new JsonEditor(dataPath + "Tournament.json");
        JsonEditor teamsEditor = new JsonEditor(dataPath + "Teams.json");

        while (true) {
            if (!isAuthenticated) {
                // Меню авторизацiї та реєстрацiї
                System.out.println("\n\033[2;1mМеню авторизацiї та реєстрацiї\033[3m:");
                System.out.println("1. Авторизацiя");
                System.out.println("2. Реєстрацiя");
                System.out.println("0. Вихiд");

                String userChoice = scanner.nextLine();

                switch (userChoice) {
                    case "0":
                        System.out.println("Дякуємо за використання додатка. До побачення!");
                        System.exit(0);
                        break;
                    case "1":
                        Authorisation.AuthResult authResult = Authorisation.authenticate(dataPath + "users.json");
                        if (authResult.isAuthenticated()) {
                            isAuthenticated = true;
                            userId = authResult.getUserId();
                            System.out.println("Авторизацiя успiшна. Вiтаємо, користуваче " + userId + "!");
                        } else {
                            System.out.println("Авторизацiя не вдалася. Перевiрте вашi данi та спробуйте ще раз.");
                        }
                        break;
                    case "2":
                        Registration registration = new Registration();
                        registration.registration(dataPath + "users.json", scanner);
                        break;
                    default:
                        System.out.println("Невiрний вибiр. Будь ласка, введiть коректний номер опцii.");
                }
            } else {
                // Код для авторизованого користувача
                System.out.println("\n\033[2;1mГоловне меню\033[3m:");
                System.out.println("1. Перегляд iнформацii з Tops");
                System.out.println("2. Перегляд iнформацii з Tournament");
                System.out.println("3. Перегляд iнформацii з Teams");
                System.out.println("4. Редагування iнформацii в Tops");
                System.out.println("5. Пошук");
                System.out.println("0. Вихiд");

                String userChoice = scanner.nextLine();

                switch (userChoice) {
                    case "0":
                        System.out.println("Дякуємо за використання додатка. До побачення!");
                        System.exit(0);
                        break;
                    case "1":
                        Tops tops = new Tops();
                        System.out.println("Вiдображення iнформацii з Tops для користувача " + userId);
                        tops.printTopPlayers(dataPath + "tops.json");
                        break;
                    case "2":
                        Tournament tournament = new Tournament();
                        System.out.println("Вiдображення iнформацii з Tournament для користувача " + userId);
                        tournament.printAllEventInfo(dataPath + "tournament.json");
                        break;
                    case "3":
                        Teams teams = new Teams();
                        System.out.println("Вiдображення iнформацii з Teams для користувача " + userId);
                        teams.printAllTeamsInfo(dataPath + "teams.json");
                        break;
                    case "4":
                        // Редагування iнформацiї у файлi Tops
                        System.out.println("Введiть iм'я гравця для оновлення winStreak:");
                        String playerNameForUpdate = scanner.nextLine();
                        System.out.println("Введiть нове значення для winStreak:");
                        int newWinStreak = Integer.parseInt(scanner.nextLine());
                        topsEditor.updatePlayerWinStreakByName(playerNameForUpdate, newWinStreak);
                        System.out.println("Значення успiшно оновлено.");
                        break;
                    case "5":
                        // Пошук
                        System.out.println("Введiть ключове слово для пошуку:");
                        String keyword = scanner.nextLine();
                        FileSearch.searchAndPrintResults(dataPath, keyword);
                        break;
                    default:
                        System.out.println("Невiрний вибiр. Будь ласка, введiть коректний номер опцii.");
                }
            }
        }
    }
}
