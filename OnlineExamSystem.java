/*
__________________________________________________________________________________________________________________________________________________________
                                                WELCOME
                                                  TO
                                          ONLINE EXAMINATION SYSTEM
__________________________________________________________________________________________________________________________________________________________ */
import java.util.Scanner;

public class OnlineExamSystem {
    private static User currentUser;
    private static Quiz[] quizzes;
    private static int totalTime = 1800; // 30 minutes in seconds

    public static void main(String[] args) {
        // Load quizzes and questions from a file or a database
        // quizzes = loadQuizzesFromDatabase();

        // For demonstration purposes, let's create a sample quiz
        quizzes = new Quiz[]{
                new Quiz("What is the capital of France?", new String[]{"London", "Paris", "Berlin", "Rome"}, 1),
                new Quiz("Which planet is known as the Red Planet?", new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 1)
        };

        showLoginScreen();
    }

    private static void showLoginScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Examination System");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // You can validate the user credentials against a database or hardcoded values here
        currentUser = new User(username, password);

        // If login successful, proceed to the profile and password update screen
        showProfileUpdateScreen();
    }

    private static void showProfileUpdateScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
        System.out.println("1. Update Profile");
        System.out.println("2. Update Password");
        System.out.println("3. Start Examination");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Implement the profile update logic
                break;
            case 2:
                // Implement the password update logic
                break;
            case 3:
                startExamination();
                break;
            case 4:
                System.out.println("Logout successful. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                showProfileUpdateScreen();
                break;
        }
    }

    private static void startExamination() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer(totalTime);
        timer.startTimer();

        int score = 0;
        int totalQuestions = quizzes.length;

        for (int i = 0; i < totalQuestions; i++) {
            Quiz quiz = quizzes[i];
            System.out.println("\nQuestion " + (i + 1) + ": " + quiz.getQuestion());
            String[] options = quiz.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            System.out.print("Enter your choice (1-" + options.length + "): ");
            int userChoice = scanner.nextInt();

            if (userChoice == quiz.getCorrectAnswer()) {
                score++;
            }
        }

        System.out.println("\n--- Examination Complete ---");
        System.out.println("Your Score: " + score + " out of " + totalQuestions);
        System.out.println("Time Remaining: " + timer.getTimeRemaining() + " seconds");

        // Implement auto-submit functionality here (submit the answers to the server/database)
        // ...

        showProfileUpdateScreen();
    }
}

class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class Quiz {
    private String question;
    private String[] options;
    private int correctAnswer;

    // Constructor
    public Quiz(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Timer {
    private int timeRemaining;

    // Constructor
    public Timer(int totalTime) {
        this.timeRemaining = totalTime;
    }

    // Methods
    public void startTimer() {
        // Implement the timer logic using threads or other mechanisms
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }
}
/*
===========================================================================================================================================================
============================================================================================================================================================
 */
