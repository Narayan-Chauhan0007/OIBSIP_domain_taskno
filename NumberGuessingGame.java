// Number Guessing Game in Java
import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have " + totalRounds + " rounds to play. Try to guess the number between 1 and 100!");

        for (int round = 1; round <= totalRounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 7;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nRound " + round + " begins!");

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    int score = (maxAttempts - attempts + 1) * 10;
                    totalScore += score;
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    System.out.println("You scored " + score + " points this round.");
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry! You've used all attempts. The correct number was: " + numberToGuess);
            }
        }

        System.out.println("\nGame Over! Your total score is: " + totalScore + " out of " + (totalRounds * 70));
        scanner.close();
    }
}
