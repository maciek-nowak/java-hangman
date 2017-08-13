import java.util.Scanner;
import java.util.HashMap;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Game {
    private Player player;
    private Capital capital;
    private AsciiArt asciiArt;
    private long startTime;
    private int attempt;
    private int gameTime;
    private static String GREEN = "\033[32m";
    private static String RED = "\033[31m";
    private static String YELLOW = "\033[33m";
    private static String WHITE = "\033[0m";

    public Game(AsciiArt asciiArt, String name) {
        this.player = new Player(name);
        this.capital = Capital.chooseRandomCapital();
        this.asciiArt = asciiArt;
        this.startTime = 0;
        this.attempt = 0;
        this.gameTime = 0;
    }

    public static String askPlayerName() {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = in.nextLine();
        return name;
    }

    public static void clearScreen() {
        System.out.println("\033[2J\033[1;1H");
    }

    public String getUserLetter() {
        String letter;

        Scanner in = new Scanner(System.in);
        System.out.println(RED + "Guess a letter: ");
        letter = Character.toString(in.next().toUpperCase().charAt(0));
        return letter;
    }


    public String getUserWord() {
        String word;

        Scanner in = new Scanner(System.in);
        System.out.println(RED + "Guess a word: ");
        word = in.nextLine().toUpperCase();
        return word;
    }

    public void printGameStatus() {
        clearScreen();
        System.out.println(RED + capital.getCapital() + "\n");
        asciiArt.showHangman(player);
        System.out.printf("%sYou have: %s%d lives.%n%n", RED, YELLOW, player.getLife());
        System.out.println(RED + "Hidden capital name is: " + YELLOW + capital.getHiddenCapitalView() + "\n");
        if (player.getLife() == 1) {
            System.out.println(RED + "Hint: the capital of " + YELLOW + capital.getCountry());
        }
        System.out.println(RED + "Previous wrong letters: " + YELLOW + capital.getWrongLettersView() + "\n");
        System.out.println(RED + "Previous wrong words: " + YELLOW + capital.getWrongWordsView() + "\n");
    }

    public void handleUserGuess() {
        String letter;
        String word;
        char choice = ' ';
        boolean isAnswearOk;
        Scanner in;

        while (choice != 'L' && choice != 'W') {
            in = new Scanner(System.in);
            System.out.println(RED + "What do you want to type? Letter (l) or word (w): ");
            choice = in.next().toUpperCase().charAt(0);
        }

        switch (choice) {
            case 'L': letter = getUserLetter();
                isAnswearOk = capital.checkLetter(letter);
                if (!isAnswearOk) {
                    player.decreaseLife(1);
                }
                break;
            case 'W': word = getUserWord();
                isAnswearOk = capital.checkWord(word);
                if (!isAnswearOk) {
                    player.decreaseLife(2);
                }
        }
        attempt++;
    }

    public void printStartMessage() {
        String temp;

        clearScreen();
        startTime = System.currentTimeMillis();
        System.out.println(RED + "Try to guess what capital I've got on my mind.");
        Scanner in = new Scanner(System.in);
        System.out.println(RED + "Hit ENTER to continue!");
        temp = in.nextLine();
    }

    public void printFinalInfo() {

        if (player.isAlive()) {
            System.out.println("You won!");
            System.out.printf("You guessed after %d attempts.%n", attempt);
        } else {
            System.out.println("You lost!");
            asciiArt.showHangman(player);
        }

        gameTime = Math.round((System.currentTimeMillis() - startTime)/1000);
        System.out.printf("It took you %d s.%n", gameTime);
        System.out.printf("Hidden capital name was: %s of %s%n", capital.getCapital(), capital.getCountry());
    }

    public void handleHighScore() {
        HighScore score;
        
        LeaderBoard.readHighScore();

        if (player.isAlive()) {
            String name = player.getName();
            DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date date = new Date();
            String currentDate = sdf.format(date);
            String capital = this.capital.getCapital();

            score = new HighScore(name, currentDate, gameTime, capital);
            LeaderBoard.addHighScore(score);
        }
        LeaderBoard.printHighScore();
        LeaderBoard.saveHighScore();
    }

    public static void singleGame(AsciiArt asciiArt) {
        String name = askPlayerName();
        Game game = new Game(asciiArt, name);
        Character letter;

        game.printStartMessage();

        while(game.player.isAlive() && !game.capital.isGuessed()) {
            game.printGameStatus();
            game.handleUserGuess();
        }
        game.printFinalInfo();
        game.handleHighScore();
    }

    public static void main(String[] args) {
        AsciiArt asciiArt = AsciiArt.getAsciiArt();
        singleGame(asciiArt);
    }
}
