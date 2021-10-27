import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Words {

    private static ArrayList<String> words = new ArrayList<String>();
    private static String wordToUse;

    //Metode som indlæser ord fra en fil og indsætter dem i en arrayList
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

    //Metode som vælger et tilfældigt ord ud efter hvilken sværhedsgrad man har valgt
    public static String getWord(){
        System.out.println("Press 1 for easy, 2 for medium and 3 for hard.");
        int choice = validateInput(1, 3);

        while (true){
            int randomInt = Main.random.nextInt(words.size());
            wordToUse = words.get(randomInt);
            if((choice == 1) && (wordToUse.length() < 6)) {
                break;
            }
            else if(choice == 2 && (wordToUse.length() > 5) && (wordToUse.length() < 11)){
                break;
            }
            else if ((choice == 3) && (wordToUse.length() > 10)){
                break;
            }
        }
        return wordToUse;
    }

    //Metode til at kun godtage integer input som er mellem min og max. Sikret så programmet ikke crasher hvis man indtaster bogstaver eller lign.
    static int validateInput(int min, int max){
        int choice = 0;
        while (true){
            try {
                choice = Main.scanner.nextInt();
                if (choice > min - 1 && choice < max + 1){
                    break;
                }
                else{
                    throw new InputMismatchException();
                }
            }
            catch (InputMismatchException e){
                Main.scanner.nextLine();
                System.out.println("You need to input a number betweeen " + min + " and " + max);
            }
        }
        Main.scanner.nextLine();
        return choice;
    }
}
