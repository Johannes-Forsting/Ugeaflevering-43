import java.util.ArrayList;

public class Hangman {
    static int lives = 6;
    static boolean hasWon = false;

    //Metode som tjekker om der stadig mangler at blive gættet nogle bogstaver i ordet
    public static void checkForVictory(ArrayList<Character> charsInWordGuessed){
        hasWon = true;
        for (int i = 0; i < charsInWordGuessed.size(); i++) {
            char currentChar = charsInWordGuessed.get(i);
            if (currentChar == '_'){
                hasWon = false;
                break;
            }
        }
    }

    //Metode med switch som tegner hangman alt afhængig af antal af liv tilbage.
    public static void drawHangman() {
        switch (lives) {
            case 6:
                System.out.println("\t\t  _____ \n" +
                        " \t\t /     |\n" +
                        "\t\t|      |\n" +
                        "\t\t|       \n" +
                        "\t\t|\n" +
                        "\t\t|\n" +
                        "\t\t|\n" +
                        "    ____|____\n" +
                        "   |         |\n" +
                        "   |         |");
                break;
            case 5:
                System.out.println("\t\t  _____ \n" +
                        " \t\t /     |\n" +
                        "\t\t|      |\n" +
                        "\t\t|      O\n" +
                        "\t\t|\n" +
                        "\t\t|\n" +
                        "\t\t|\n" +
                        "    ____|____\n" +
                        "   |         |\n" +
                        "   |         |");
                break;
            case 4:
                System.out.println("\t\t  _____ \n" +
                        " \t\t /     |\n" +
                        "\t\t|      |\n" +
                        "\t\t|      O\n" +
                        "\t\t|      |\n" +
                        "\t\t|\n" +
                        "\t\t|\n" +
                        "    ____|____\n" +
                        "   |         |\n" +
                        "   |         |");
                break;
            case 3:
                System.out.println("\t\t  _____ \n" +
                        " \t\t /     |\n" +
                        "\t\t|      |\n" +
                        "\t\t|      O\n" +
                        "\t\t|     /|\n" +
                        "\t\t|\n" +
                        "\t\t|\n" +
                        "    ____|____\n" +
                        "   |         |\n" +
                        "   |         |");
                break;
            case 2:
                System.out.println("\t\t  _____ \n" +
                        " \t\t /     |\n" +
                        "\t\t|      |\n" +
                        "\t\t|      O\n" +
                        "\t\t|     /|\\\n" +
                        "\t\t|\n" +
                        "\t\t|\n" +
                        "    ____|____\n" +
                        "   |         |\n" +
                        "   |         |");
                break;
            case 1:
                System.out.println("\t\t  _____ \n" +
                        " \t\t /     |\n" +
                        "\t\t|      |\n" +
                        "\t\t|      O\n" +
                        "\t\t|     /|\\\n" +
                        "\t\t|    _/ \n" +
                        "\t\t|\n" +
                        "    ____|____\n" +
                        "   |         |\n" +
                        "   |         |");
                break;
            case 0:
                System.out.println("\t\t  _____ \n" +
                        " \t\t /     |\n" +
                        "\t\t|      |\n" +
                        "\t\t|      O\n" +
                        "\t\t|     /|\\\n" +
                        "\t\t|    _/ \\_\n" +
                        "\t\t|\n" +
                        "    ____|____\n" +
                        "   |         |\n" +
                        "   |         |");
                break;
        }
    }
}
