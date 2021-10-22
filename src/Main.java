import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ArrayList<String> words = new ArrayList<>();
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static int lives = 6;
    static ArrayList<Character> wordToGuessArray = new ArrayList<>();
    static ArrayList<Character> charsInWordGuessed = new ArrayList<>();
    static ArrayList<Character> charsNotGuessed = new ArrayList<>();

    public static void main(String[] args) {
        createListOfWords();
        String wordToGuess = getWord();
        createCharArrays(wordToGuess);
        System.out.println(wordToGuess);
        System.out.println(charsInWordGuessed);
        while (Hangman.lives > 0 && Hangman.hasWon == false) {
            char guessedChar = getCharInput();
            boolean isInWord = isInWord(guessedChar);
            if (isInWord == false){
                Hangman.lives--;
            }
            System.out.println(charsInWordGuessed);
            System.out.println("You have " + lives + " lives left.");
            Hangman.drawHangman();
            Hangman.checkForVictory(charsInWordGuessed);
        }

        String stringToEndGame = Hangman.hasWon ? "Congratulations. You have won!" : "You died. Better luck next time.";
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


    static void createListOfWords(){
        File f = new File("resources/words.csv");
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                words.add(line);
            }
        }
        catch (FileNotFoundException e){
        }
    }


    static String getWord(){
        String word = "";
        System.out.println("Press 1 for easy and 2 for hard.");
        int choice = validateInput(1, 2);

        while (true){
            int randomInt = random.nextInt(words.size());
            word = words.get(randomInt);
            if((choice == 1) && (word.length() < 6)) {
                break;
            }
            else if ((choice == 2) && (word.length() > 10)){
                break;
            }
        }
        return word;
    }

    static int validateInput(int min, int max){
        int choice = 0;
        while (true){
            try {
                choice = scanner.nextInt();
                if (choice > min - 1 && choice < max + 1){
                    break;
                }
                else{
                    throw new InputMismatchException();
                }
            }
            catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("You need to input a number betweeen " + min + " and " + max);
            }
        }
        scanner.nextLine();
        return choice;
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
                removeFromNotGuessedAraay(guess);
                isInWord = true;
            }
        }
        return isInWord;
    }
    static void removeFromNotGuessedAraay(char x){
        for (int i = 0; i < charsNotGuessed.size(); i++) {
            if (x == charsNotGuessed.get(i)){
                charsNotGuessed.remove(i);
                break;
            }
        }
    }



    static void looseHealth(){

    }




}
