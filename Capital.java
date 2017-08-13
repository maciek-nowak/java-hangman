import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.ArrayList;

public class Capital {
    private String capital;
    private String country;
    private String hiddenCapital;
    private HashSet<String> wrongLetters;
    private HashSet<String> wrongWords;

    public String getCapital(){
        return capital;
    }

    public String getCountry(){
        return country;
    }

    public String getHiddenCapital(){
        return hiddenCapital;
    }

    public String getHiddenCapitalView(){
        return hiddenCapital.replace("", " ").trim();
    }

    public String getWrongLettersView(){
        return String.join(" ", wrongLetters);
    }

    public String getWrongWordsView(){
        return String.join(" ", wrongWords);
    }

    public HashSet<String> getWrongLetters(){
        return wrongLetters;
    }

    public HashSet<String> getWrongWords(){
        return wrongWords;
    }

    public boolean isGuessed(){
        return capital.equals(hiddenCapital);
    }

    public boolean checkLetter( String letter ) {
        if (capital.indexOf(letter) != -1) {
            for (int i = 0; i < capital.length(); i++) {
                if ( letter.equals(Character.toString(capital.charAt(i))) ) {
                    hiddenCapital = hiddenCapital.substring(0, i) + letter +
                                    hiddenCapital.substring(i+1, hiddenCapital.length());
                }
            }
            return true;
        } else {
            wrongLetters.add(letter);
            return false;
        }
    }

    public boolean checkWord( String word ) {
        if (capital.equals(word)) {
            hiddenCapital = capital;
            return true;
        } else {
            wrongWords.add(word);
            return false;
        }
    }

    public Capital(String capital, String country){
        this.capital = capital.toUpperCase();
        this.country = country.toUpperCase();
        this.hiddenCapital = capital.replaceAll("\\w", "_");
        this.wrongLetters = new HashSet<>();
        this.wrongWords = new HashSet<>();
    }

    public static ArrayList<Capital> readCapitalsData() {
        ArrayList<Capital> capitals= new ArrayList<Capital>();
        String[] capitalData;
        Scanner fileScan;

        try {
            fileScan = new Scanner(new File("countries_and_capitals.txt"));

            while(fileScan.hasNextLine()) {
                capitalData = fileScan.nextLine().split(" \\| ");
                capitals.add(new Capital(capitalData[1], capitalData[0]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return capitals;
    }

    public static Capital chooseRandomCapital() {
        ArrayList<Capital> capitals = readCapitalsData();
        Random randomGenerator = new Random();
        int capitalNumber = randomGenerator.nextInt(capitals.size());
        return capitals.get(capitalNumber);
    }

    public static void main(String[] args) {
        Capital capital = chooseRandomCapital();
        System.out.printf("%s is capital of %s%n", capital.capital, capital.country);
        System.out.println(capital.capital + " is capital of " + capital.country);
        System.out.println(capital.checkWord("WARSAW"));
        System.out.println(capital.checkWord("BERLIN"));
        System.out.println(capital.checkLetter("A"));
        System.out.println(capital.checkLetter("E"));
        System.out.println(capital.checkLetter("O"));
        System.out.println(capital.checkLetter("I"));
        System.out.println(capital.getHiddenCapitalView());
        System.out.println(capital.getWrongLettersView());
        System.out.println(capital.getWrongWordsView());
    }
}
