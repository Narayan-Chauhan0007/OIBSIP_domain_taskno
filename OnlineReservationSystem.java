import java.util.*;

// User class for login authentication
class User {
    String userId;
    String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public boolean authenticate(String inputId, String inputPassword) {
        return this.userId.equals(inputId) && this.password.equals(inputPassword);
    }
}

// Reservation class to hold booking info
class Reservation {
    static int pnrCounter = 1000;
    int pnr;
    String name, from, to, date, trainNumber, trainName, classType;

    public Reservation(String name, String from, String to, String date, String trainNumber, String classType) {
        this.pnr = pnrCounter++;
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.trainNumber = trainNumber;
        this.trainName = getTrainName(trainNumber);
        this.classType = classType;
    }

    private String getTrainName(String number) {
        Map<String, String> trains = new HashMap<>();
        trains.put("12345", "Rajdhani Express");
        trains.put("23456", "Shatabdi Express");
        trains.put("34567", "Duronto Express");
        trains.put("45678", "Garib Rath");
        trains.put("56789", "Intercity Express");
        return trains.getOrDefault(number, "Unknown Train");
    }

    public void display() {
        System.out.println("PNR: " + pnr);
        System.out.println("Passenger Name: " + name);
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + trainName);
        System.out.println("Class Type: " + classType);
        System.out.println("From: " + from + "  To: " + to);
        System.out.println("Journey Date: " + date);
    }
}

// Main application class
public class OnlineReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static Map<Integer, Reservation> reservations = new HashMap<>();
    static User user = new User("user1", "pass123");

    public static void main(String[] args) {
        System.out.println("=== Welcome to the Online Reservation System ===");
        if (login()) {
            int choice;
            do {
                System.out.println("\n1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        makeReservation();
                        break;
                    case 2:
                        cancelReservation();
                        break;
                    case 3:
                        System.out.println("Thank you for using the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 3);
        }
    }

    // User login method
    static boolean login() {
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        if (user.authenticate(id, pass)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials. Exiting.");
            return false;
        }
    }

    // Reservation method
    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("From: ");
        String from = scanner.nextLine();
        System.out.print("To: ");
        String to = scanner.nextLine();
        System.out.print("Journey Date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        System.out.print("Train Number (e.g., 12345): ");
        String trainNumber = scanner.nextLine();
        System.out.print("Class Type (Sleeper/AC): ");
        String classType = scanner.nextLine();

        Reservation res = new Reservation(name, from, to, date, trainNumber, classType);
        reservations.put(res.pnr, res);

        System.out.println("\n✅ Reservation Successful! Your PNR is: " + res.pnr);
        res.display();
    }

    // Cancellation method
    static void cancelReservation() {
        System.out.print("Enter your PNR number to cancel: ");
        int pnr = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (reservations.containsKey(pnr)) {
            Reservation res = reservations.get(pnr);
            System.out.println("\nReservation Details:");
            res.display();

            System.out.print("\nAre you sure you want to cancel this ticket? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("✅ Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("❌ No reservation found with PNR: " + pnr);
        }
    }
}
