import java.util.*;
public class KNBplus {
    static int totalRocks = 0;
    static int totalScissors = 0;
    static int totalPaper = 0;
    public static void sortArray(String[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            String temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    public static void printMatch(String[] players) {
        System.out.println("1/8 финала:");
        for (int i = 0; i < players.length; i += 2) {
            System.out.println(players[i] + " VS " + players[i + 1]);
        }
    }
    public static void startTournament(String[] players) {
        String[] lastSixteenWinners = new String[8];
        String[] quarterFinalWinners = new String[4];
        String[] semiFinalWinners = new String[2];
        String[] semiFinalLosers = new String[2];
        System.out.println("1/8 финала:");
        for (int i = 0; i < players.length; i += 2) {
            lastSixteenWinners[i / 2] = playMatch(players[i], players[i + 1], 3);
        }
        System.out.println("Четвертьфинал:");
        for (int i = 0; i < lastSixteenWinners.length; i += 2) {
            quarterFinalWinners[i / 2] = playMatch(lastSixteenWinners[i], lastSixteenWinners[i + 1], 3);
        }
        System.out.println("\nПолуфинал:");
        for (int i = 0; i < quarterFinalWinners.length; i += 2) {
            String winner = playMatch(quarterFinalWinners[i], quarterFinalWinners[i + 1], 3);
            semiFinalWinners[i / 2] = winner;
            semiFinalLosers[i / 2] = winner.equals(quarterFinalWinners[i]) ? quarterFinalWinners[i + 1] : quarterFinalWinners[i];
        }
        System.out.println("\nМатч за третье место:");
        String thirdPlace = playMatch(semiFinalLosers[0], semiFinalLosers[1], 5);
        System.out.println("\nФинал:");
        String champion = playMatch(semiFinalWinners[0], semiFinalWinners[1], 5);
        System.out.println("\nПобедитель турнира: " + champion);
        System.out.println("Третье место занял: " + thirdPlace);
    }
    public static String playMatch(String player1, String player2, int maxWins) {
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int matchNumber = 1;
        while (winsPlayer1 < maxWins && winsPlayer2 < maxWins) {
            System.out.println("\n===== Матч #" + matchNumber + " =====");
            String result = playGame(player1, player2);
            System.out.println(result);
            if (result.contains(player1 + " выиграл")) {
                winsPlayer1++;
            } else if (result.contains(player2 + " выиграл")) {
                winsPlayer2++;
            }
            matchNumber++;
        }
        String matchResult = "Результат: " + player1 + " " + winsPlayer1 + ":" + winsPlayer2 + " " + player2;        System.out.println(matchResult);
        if (winsPlayer1 > winsPlayer2) {
            System.out.println("////// Победа игрока " + player1 + " //////");
            return player1;
        }
        else {
            System.out.println("////// Победа игрока " + player2 + " //////");
            return player2;
        }
    }

    public static String playGame(String player1, String player2) {
        String[] moves = {"камень", "ножницы", "бумага"};
        Random rand = new Random();
        String move1 = moves[rand.nextInt(moves.length)];
        String move2 = moves[rand.nextInt(moves.length)];
        updateStatistics(move1);
        updateStatistics(move2);
        System.out.println(player1 + ": " + move1 + " | " + player2 + ": " + move2);
        if (move1.equals(move2)) {
            return "Ничья!";
        }
        else if ((move1.equals("камень") && move2.equals("ножницы")) || (move1.equals("ножницы") && move2.equals("бумага")) ||                   (move1.equals("бумага") && move2.equals("камень"))) {
            return player1 + " выиграл!";
        }
        else {
            return player2 + " выиграл!";
        }
    }
    public static void updateStatistics(String move) {
        switch (move) {
            case "камень":totalRocks++;
                break;
            case "ножницы":totalScissors++;
                break;
            case "бумага":totalPaper++;
                break;
        }
    }
    public static void printStatistics() {
        System.out.println("Статистика игры:");
        System.out.println("Камень: " + totalRocks);
        System.out.println("Ножницы: " + totalScissors);
        System.out.println("Бумага: " + totalPaper);
    }
    public static void main(String[] args) {
        String[] players = {"Абдуллах", "Барканов", "Владимирова", "Жуков", "Касибин", "Ледовский", "Лучин", "Мотина","Мухин", "Марфенкова", "Малофеев", "Слепченко", "Телешева", "Тырин", "Хомутецкий", "Ягупов"};
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Провести жеребьёвку");
            System.out.println("2. Начать турнир");
            System.out.println("3. Показать статистику");
            System.out.println("4. Выход");
            int choice = sc.nextInt();
            sc.nextLine(); // очистка буфера
            switch (choice) {
                case 1:
                    sortArray(players);
                    printMatch(players);
                    break;
                case 2:
                    startTournament(players);
                    break;
                case 3:
                    printStatistics();
                    break;
                case 4:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}