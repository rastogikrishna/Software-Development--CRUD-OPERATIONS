
import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    public static final String fileName = "books.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Library Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. View Books");
            System.out.println("4. Update Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    deleteBook(sc);
                    break;
                case 3:
                    viewBooks();
                    break;
                case 4:
                    updateBook(sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private static void addBook(Scanner sc) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(title + "," + author);
            writer.newLine();
            System.out.println("Book added successfully");
        } catch (IOException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private static void deleteBook(Scanner sc) {
        System.out.print("Enter book title to delete: ");
        String titleToDelete = sc.nextLine();
        File inputFile = new File(fileName);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] book = currentLine.split(",");
                if (!book[0].equalsIgnoreCase(titleToDelete)) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    found = true;
                }
            }
            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("Book deleted successfully");
            } else {
                tempFile.delete();
                System.out.println("Book not found");
            }
        } catch (IOException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    private static void viewBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            System.out.println("Error viewing books: " + e.getMessage());
        }
    }

    private static void updateBook(Scanner sc) {
        System.out.print("Enter book title to update: ");
        String titleToUpdate = sc.nextLine();
        File inputFile = new File(fileName);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] book = currentLine.split(",");
                if (book[0].equalsIgnoreCase(titleToUpdate)) {
                    System.out.print("Enter new author: ");
                    String newAuthor = sc.nextLine();
                    writer.write(book[0] + "," + newAuthor);
                    writer.newLine();
                    found = true;
                } else {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("Book updated successfully");
            } else {
                tempFile.delete();
                System.out.println("Book not found");
            }
        } catch (IOException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }
}

