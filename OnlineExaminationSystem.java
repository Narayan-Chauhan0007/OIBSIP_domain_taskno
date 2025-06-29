import java.util.*;

class User {
    String username;
    String password;
    String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void updateProfile(String newName) {
        this.name = newName;
        System.out.println("Profile updated successfully.");
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated successfully.");
    }
}

class Exam {
    private Map<String, String> questions;
    private Map<String, String> userAnswers;
    private int score;

    public Exam() {
        questions = new LinkedHashMap<>();
        userAnswers = new HashMap<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.put("1) What is the capital of India?\nA) Mumbai\nB) Delhi\nC) Kolkata\nD) Chennai", "B");
        questions.put("2) Which language runs in a web browser?\nA) Java\nB) C\nC) Python\nD) JavaScript", "D");
        questions.put("3) Who is the CEO of Tesla?\nA) Jeff Bezos\nB) Elon Musk\nC) Bill Gates\nD) Tony Stark", "B");
        questions.put("4) What is 10 + 20?\nA) 10\nB) 20\nC) 30\nD) 40", "C");
    }

    public void startExam(Scanner scanner) {
        System.out.println("\n⏱️ Exam started. You have 60 seconds to complete it.");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\n⏰ Time's up! Auto-submitting the exam...");
                submitExam();
                System.exit(0);
            }
        }, 60000); // 60 seconds

        for (String question : questions.keySet()) {
            System.out.println("\n" + question);
            System.out.print("Enter your answer: ");
            String answer = scanner.nextLine().trim().toUpperCase();
            userAnswers.put(question, answer);
        }

        timer.cancel(); // Cancel timer if user submits early
        submitExam();
    }

    public void submitExam() {
        score = 0;
        for (Map.Entry<String, String> entry : questions.entrySet()) {
            String correct = entry.getValue();
            String userAnswer = userAnswers.getOrDefault(entry.getKey(), "");
            if (correct.equalsIgnoreCase(userAnswer)) {
                score++;
            }
        }
        System.out.println("\n✅ Exam submitted.");
        System.out.println("Your Score: " + score + " out of " + questions.size());
    }
}

public class OnlineExaminationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static User user = new User("student1", "pass123", "John Doe");

    public static void main(String[] args) {
        System.out.println("=== 📝 Welcome to the Online Examination System ===");
        login();
    }

    private static void login() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (user.login(username, password)) {
            System.out.println("\n🔓 Login successful. Welcome, " + user.name + "!");
            showMenu();
        } else {
            System.out.println("\n❌ Invalid credentials. Try again.");
        }
    }

    private static void showMenu() {
        int choice;
        do {
            System.out.println("\n--- 📋 Menu ---");
            System.out.println("1. Update Profile");
            System.out.println("2. Update Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    user.updateProfile(newName);
                    break;
                case 2:
                    System.out.print("Enter new password: ");
                    String newPass = scanner.nextLine();
                    user.updatePassword(newPass);
                    break;
                case 3:
                    Exam exam = new Exam();
                    exam.startExam(scanner);
                    break;
                case 4:
                    System.out.println("🔒 Logging out. Goodbye!");
                    break;
                default:
                    System.out.println("❗ Invalid choice. Try again.");
            }
        } while (choice != 4);
    }
}
