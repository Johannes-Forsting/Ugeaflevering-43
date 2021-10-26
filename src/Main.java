import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Character> wordToGuessArray = new ArrayList<>();
    static ArrayList<Character> charsInWordGuessed = new ArrayList<>();
    static ArrayList<Character> charsNotGuessed = new ArrayList<>();

    public static void main(String[] args) {
        Words.createListOfWords();
        String wordToGuess = Words.getWord();
        createCharArrays(wordToGuess);
        System.out.println(charsInWordGuessed);
        while (Hangman.lives > 0 && Hangman.hasWon == false) {
            char guessedChar = getCharInput();
            boolean isInWord = isInWord(guessedChar);
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
                boolean hasBeenUsed = hasBeenUsed(charToReturn);
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

    static boolean hasBeenUsed(char charGuess){
        boolean returnBool = false;
        for (int i = 0; i < charsNotGuessed.size(); i++) {
            if (charsNotGuessed.get(i) == charGuess){
                charsNotGuessed.remove(i);
                returnBool = true;
            }
        }
        return returnBool;
    }

    static boolean isInWord(char guess){
        boolean isInWord = false;
        for (int i = 0; i < wordToGuessArray.size(); i++) {
            if(guess == wordToGuessArray.get(i)){
                charsInWordGuessed.set(i, guess);
                isInWord = true;
            }
        }
        return isInWord;
    }
}
