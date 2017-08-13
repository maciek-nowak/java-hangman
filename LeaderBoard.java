import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;

class LeaderBoard {
    private static ArrayList<HighScore> highScores;

    public static void readHighScore() {
        highScores= new ArrayList<HighScore>();
        String[] highScoreData;
        Scanner fileScan;
        int time;

        try {
            fileScan = new Scanner(new File("highscore.txt"));

            while(fileScan.hasNextLine()) {
                highScoreData = fileScan.nextLine().split(" \\| ");
                time = Integer.parseInt(highScoreData[2]);
                highScores.add(new HighScore(highScoreData[0], highScoreData[1], time, highScoreData[3]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public static void saveHighScore() {
        try {
            Formatter writer = new Formatter("highscore.txt");
            for(HighScore line: highScores) {
                writer.format("%s%n", line.makeString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public static void printHighScore() {
        int number = 1;

        System.out.println("HIGHSCORE:");
        for(HighScore line: highScores) {
            System.out.printf("%d. %s%n", number, line.makeString());
            number++;
        }
    }

    public static void addHighScore(HighScore highscore) {
        for(int i = 0; i < 10; i++) {
            if (highscore.getTime() < highScores.get(i).getTime()) {
                highScores.add(i, highscore);
                highScores.remove(10);
                break;
            }
        }

    }

    public static void handleHighScore() {
        readHighScore();
        printHighScore();
        saveHighScore();
    }
}
