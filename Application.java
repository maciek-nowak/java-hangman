import java.util.Scanner;


class Application {
    private static AsciiArt asciiArt;
    private static boolean again;

    public static boolean askUserToPlayAgain() {
        String choice = "";
        Scanner in;

        while (!choice.equals("YES") && !choice.equals("NO")) {
            in = new Scanner(System.in);
            System.out.println("Do you want to play again [yes / no]: ");
            choice = in.nextLine().toUpperCase();
        }

        if (choice.equals("YES")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        again = true;
        asciiArt = AsciiArt.getAsciiArt();
        asciiArt.showIntro();

        while (again) {
            Game.singleGame(asciiArt);
            again = askUserToPlayAgain();
        }
    }
}
