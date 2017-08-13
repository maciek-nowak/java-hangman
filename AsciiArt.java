import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;


class AsciiArt {
    private String[] titleFrames;
    private String[] hangingPhases;
    private static String GREEN = "\033[32m";
    private static String RED = "\033[31m";

    public AsciiArt(String[] titleFrames, String[] hangingPhases){
      this.titleFrames = titleFrames;
      this.hangingPhases = hangingPhases;
    }

    public void showIntro() {

        for (String frame: titleFrames) {
            System.out.print("\033[2J\033[1;1H");
            System.out.println(GREEN + frame);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e1) {
                // catching Exception caused by sleep method
            }
        }
    }

    public void showHangman(Player player) {
        System.out.println(RED + hangingPhases[player.getLife()]);
    }

    public static AsciiArt getAsciiArt() {
        String content = "";
        String[] framesList;
        String[] titleFrames;
        String[] hangingPhases;
        Scanner fileScan;
        AsciiArt asciiArts = null;

        try {
            fileScan = new Scanner(new File("ascii_art.txt"));

            while(fileScan.hasNextLine()) {
                content += (fileScan.nextLine() + "\n");
            }
            framesList = content.split("break\n");
            titleFrames = Arrays.copyOfRange(framesList, 0, 7);
            hangingPhases = Arrays.copyOfRange(framesList, 7, 13);
            asciiArts = new AsciiArt(titleFrames, hangingPhases);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return asciiArts;
    }

    public static void main(String[] args) {
        AsciiArt asciiArts = getAsciiArt();
        asciiArts.showIntro();
    }
}
