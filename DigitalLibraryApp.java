import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public void display() {
        System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author + " | Issued: " + (isIssued ? "Yes" : "No"));
    }
}

class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private String adminPassword = "admin123";

    public void showMainMenu() {
        int choice;
        do {
            System.out.println("\n=== Digital Library Management System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. User Panel");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userPanel();
                    break;
                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);
    }

    private void adminLogin() {
        System.out.print("Enter admin password: ");
        String pass = scanner.nextLine();
        if (pass.equals(adminPassword)) {
            adminMenu();
        } else {
            System.out.println("Incorrect password.");
        }
    }

    private void adminMenu() {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. View All Books");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    viewBooks();
                    break;
                case 4:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 4);
    }

    private void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();
        books.put(id, new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = scanner.nextInt();
        if (books.containsKey(id)) {
            books.remove(id);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void userPanel() {
        int choice;
        do {
            System.out.println("\n--- User Panel ---");
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Send Query via Email");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    System.out.print("Enter your query: ");
                    String query = scanner.nextLine();
                    System.out.println("Email sent to admin: " + query);
                    break;
                case 6:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 6);
    }

    private void viewBooks() {
        System.out.println("\n--- Available Books ---");
        if (books.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            for (Book book : books.values()) {
                book.display();
            }
        }
    }

    private void searchBook() {
        System.out.print("Enter title keyword: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Book book : books.values()) {
            if (book.title.toLowerCase().contains(keyword)) {
                book.display();
                found = true;
            }
        }
        if (!found) System.out.println("No matching books found.");
    }

    private void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = scanner.nextInt();
        Book book = books.get(id);
        if (book != null && !book.isIssued) {
            book.isIssued = true;
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not available or already issued.");
        }
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();
        Book book = books.get(id);
        if (book != null && book.isIssued) {
            book.isIssued = false;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found or not issued.");
        }
    }
}

public class DigitalLibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        library.showMainMenu();
    }
}
