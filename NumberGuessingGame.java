/*__________________________________________________________________________________________________________________________________________________________________
                                                      WELCOME TO
                                                NUMBER GUESSING GAME
___________________________________________________________________________________________________________________________________________________________________*/
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        int target = rand.nextInt(100) + 1; // Generate a random number from 1 to 100
        int attempts = 0;
        int maxAttempts = 10; // Maximum number of attempts
        int score = 100; // Starting score

        System.out.println("Welcome to the Number Guessing Game! You have " + maxAttempts + " attempts to guess the number.");

        while (attempts < maxAttempts) {
            System.out.println("Enter a number between 1 and 100: ");
            int guess = input.nextInt();

            attempts++;

            if (guess == target) {
                System.out.println("Congratulations! You guessed the number correctly.");
                break;
            } else if (guess < target) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }

            score -= 10; // Deduct 10 points for each incorrect guess
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you did not guess the number. The number was " + target);
        }

        System.out.println("Your score is: " + score);
    }
}

// ______________________________________________________________________________________________________________________________________________________________________
