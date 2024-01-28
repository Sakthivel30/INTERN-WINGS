import java.util.Random;
import java.util.Scanner;

public class Number_Game{
    public static void main(String[] args) {
        playNumberGame();
    }

    static void playNumberGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int mysteryNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        int maxAttempts = 10;
        int attemptsLeft = maxAttempts;
        int roundsWon = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between " + lowerBound + " and " + upperBound);

        while (attemptsLeft > 0) {
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Enter your guess: ");

            int userGuess;

            try {
                userGuess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            totalAttempts++;

            if (userGuess == mysteryNumber) {
                System.out.println("Congratulations! You guessed the correct number.");
                roundsWon++;
                break;
            } else if (userGuess < mysteryNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            attemptsLeft--;
        }

        if (attemptsLeft == 0) {
            System.out.println("Sorry, you ran out of attempts. The correct number was: " + mysteryNumber);
        }

        System.out.println("Rounds won: " + roundsWon);
        System.out.println("Total attempts: " + totalAttempts);

        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();

        if (playAgain.equals("yes")) {
            playNumberGame(); // Recursive call to play again
        } else {
            System.out.println("Thank you for playing. Goodbye!");
        }

        scanner.close();
    }
}