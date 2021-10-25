import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Words {

    private static ArrayList<String> words = new ArrayList<String>();
    private static String wordToUse;


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


    public static String getWord(){
        System.out.println("Press 1 for easy and 2 for hard.");
        int choice = validateInput(1, 2);

        while (true){
            int randomInt = Main.random.nextInt(words.size());
            wordToUse = words.get(randomInt);
            if((choice == 1) && (wordToUse.length() < 6)) {
                break;
            }
            else if ((choice == 2) && (wordToUse.length() > 10)){
                break;
            }
        }
        return wordToUse;
    }

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
