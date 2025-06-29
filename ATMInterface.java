import java.util.*;

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 1000.0; // Initial balance
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String id, String pin) {
        return this.userId.equals(id) && this.userPin.equals(pin);
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: Rs. " + amount);
        System.out.println("Rs. " + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: Rs. " + amount);
            System.out.println("Rs. " + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            transactionHistory.add("Transferred: Rs. " + amount + " to User ID: " + recipient.userId);
            System.out.println("Rs. " + amount + " transferred to " + recipient.userId);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String txn : transactionHistory) {
                System.out.println(txn);
            }
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        // Predefined user accounts
        accounts.put("user123", new Account("user123", "1234"));
        accounts.put("user456", new Account("user456", "4567"));

        System.out.println("=== Welcome to the ATM Machine ===");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account currentAccount = accounts.get(userId);
        if (currentAccount != null && currentAccount.authenticate(userId, pin)) {
            System.out.println("Login successful!");
            showMenu(currentAccount);
        } else {
            System.out.println("Invalid User ID or PIN.");
        }
    }

    private static void showMenu(Account account) {
        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    account.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: Rs. ");
                    double withdrawAmt = scanner.nextDouble();
                    account.withdraw(withdrawAmt);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: Rs. ");
                    double depositAmt = scanner.nextDouble();
                    account.deposit(depositAmt);
                    break;
                case 4:
                    System.out.print("Enter recipient User ID: ");
                    String recipientId = scanner.next();
                    Account recipient = accounts.get(recipientId);
                    if (recipient != null) {
                        System.out.print("Enter amount to transfer: Rs. ");
                        double transferAmt = scanner.nextDouble();
                        account.transfer(recipient, transferAmt);
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
    }
}
