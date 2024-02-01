# Puchka Andrian - Спортивна Арена Система Управління Змаганнями (SportArena)

Це консольний додаток для управління турнірами та змаганнями з гри Dota 2. Додаток дозволяє легко створювати, редагувати та видаляти турніри, а також відслідковувати та оновлювати результати змагань.

## Встановлення

1. Клонуйте репозиторій:

   ```bash
   git clone https://github.com/PuchkaAndrian/SportArena.git
   ```

2. Перейдіть до папки проекту:

   ```bash
   cd SportArena
   ```

3. Запустіть додаток:

   ```bash
   python main.py
   ```

## Використання

- Додайте новий турнір:

  ```bash
  python main.py create-tournament
  ```

- Редагуйте інформацію про турнір:

  ```bash
  python main.py edit-tournament <tournament_id>
  ```

- Перегляньте результати турніру:

  ```bash
  python main.py view-results <tournament_id>
  ```

- Оновіть результати змагань:

  ```bash
  python main.py update-results <tournament_id>
  ```

## Внесок

Я відкритий до змін та вдосконалень. Якщо у вас є ідеї чи побажання, будь ласка, створіть pull request або відкрийте новий issue.

## Ліцензія

Цей проект має ліцензію [MIT](LICENSE).

© 2024 Puchka Andrian. Всі права захищені.
