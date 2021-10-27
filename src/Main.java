import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //Opretter Random, scanner, og 3 ArrayLiser som jeg skal bruge.
    //wordToGuessArray er ordet som skal gættes lavet om til et array af chars.
    //charsInWordGuessed er et array af '_' karaktere som bliver lavet om til ordet jo flere bogstaver man gætter.
    //charsNotGuessed er et array af chars af hele alfabetet, hvor hvert bogstav fjernes når det gættes på.
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Character> wordToGuessArray = new ArrayList<>();
    static ArrayList<Character> charsInWordGuessed = new ArrayList<>();
    static ArrayList<Character> charsNotGuessed = new ArrayList<>();

    //Main hvor gør følgende:
    //Kalder metoden createListOfWords() som scanner alle ord ind fra en fil med omkring 800 forskellige ord.
    //Kalder metoden getWord som tager et tilfældigt ord fra listen, og længden af ordet kommer an på om man vælger "easy", "medium" eller "hard".
    //Kalder metoden createCharArrays som opretter de forskellige arrays som jo er forskellige afhængige af ordet som vælges.
    //Kører while while loop indtil man enten har gættet ordet.
    public static void main(String[] args) {
        Words.createListOfWords();
        String wordToGuess = Words.getWord();
        createCharArrays(wordToGuess);
        System.out.println(charsInWordGuessed);
        while (Hangman.lives > 0 && Hangman.hasWon == false) {
            char guessedChar = getCharInput();
            boolean isInWord = Words.isInWord(guessedChar);
            if (isInWord == false){
                Hangman.lives--;
            }
            System.out.println(charsInWordGuessed);
            System.out.println("You have " + Hangman.lives + " lives left.");
            Hangman.drawHangman();
            Hangman.checkForVictory(charsInWordGuessed);
        }
        String stringToEndGame = Hangman.hasWon ? "Congratulations. You have won!" : "You died. Better luck next time. \nThe word you were trying to guess was: " + wordToGuess;
        System.out.println(stringToEndGame);
    }

    //Metode som fylder de forskellige arrayLister ud
    static void createCharArrays(String word){
        for (int i = 0; i < word.length(); i++) {
            wordToGuessArray.add(word.charAt(i));
        }
        for (int i = 0; i < wordToGuessArray.size(); i++) {
            charsInWordGuessed.add('_');
        }
        for (int i = 0; i < 26; i++) {
            char currentChar = (char) (97 + i);
            charsNotGuessed.add(currentChar);
        }
    }

    //Metode som får et bogstav input fra brugeren.
    //Er sikret på en sådan måde at den kun kan godtage ét bogstav af gangen og man kan ikke skrive tal eller specialtegn.
    static char getCharInput(){
        String choice = "";
        char charToReturn;
        while (true){
            boolean hasNotBeenGuessed = false;
            boolean isOnly1Letter = false;
            System.out.println("What would you like to guess for? The following letters have not been used yet:");
            System.out.println(charsNotGuessed);
            choice = scanner.nextLine();
            choice = choice.toLowerCase();
            isOnly1Letter = (choice.length() == 1) && (Character.isLetter(choice.charAt(0)));
            if(isOnly1Letter){
                charToReturn = choice.charAt(0);
                boolean hasBeenUsed = Words.hasBeenUsed(charToReturn);
                if (hasBeenUsed){
                    break;
                }
                else{
                    System.out.println("That character has allready been guessed. Choose another.");
                }
            }
            else {
                System.out.println("Only type 1 letter please, and one that has not been guessed yet.");
            }
        }
        return charToReturn;
    }
}
