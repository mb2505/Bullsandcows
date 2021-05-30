import java.util.*;

public class Bullsandcows {

    public static ArrayList<String> randomNumber;
    public static int count = 1;
    public static int length;
    public static int bulls;
    public static int cows;
    public static String secretCode;
    public static String guessCode;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        length = s.nextInt();
        if (length > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d " +
                    "because there aren't enough unique digits.", length);
        } else {
            secretCode = generateCode(length);
            System.out.println("Okay, let's start a game!");
            countBullsAndCows();
        }
    }

    public static String generateCode(int length) {
        generateRandomNumber();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(randomNumber.get(i));
        }
        return code.toString();
    }

    public static void generateRandomNumber() {
        randomNumber = new ArrayList<>(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Collections.shuffle(randomNumber);

        if (randomNumber.get(0).equals("0")) {
            generateRandomNumber();
        }
    }

    public static void countBullsAndCows() {
        Scanner s = new Scanner(System.in);
        bulls = 0;
        cows = 0;
        guessCode = s.next();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (guessCode.charAt(j) == (secretCode.charAt(i)) && i != j) {
                    cows++;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            if (guessCode.charAt(i) == (secretCode.charAt(i))) {
                bulls++;
            }
        }

        printBullsAndCows();
    }

    public static void printBullsAndCows() {
        System.out.printf("Turn %d:\n", count);
        count++;

        if (bulls > 0 && cows > 0) {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + "cow(s).");
            countBullsAndCows();
        } else if (bulls > 0 && cows == 0 && bulls != length) {
            System.out.println("Grade: " + bulls + " bull(s).");
            countBullsAndCows();
        } else if (bulls == 0 && cows > 0) {
            System.out.println("Grade: " + cows + " cow(s).");
            countBullsAndCows();
        } else if (bulls == length) {
            System.out.println("Grade: " + bulls + " bull(s).");
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            System.out.println("Grade: None.");
            countBullsAndCows();
        }
    }
}
