
import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    public static final String fileName = "students.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Student Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. View Students");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    deleteStudent(sc);
                    break;
                case 3:
                    viewStudents();
                    break;
                case 4:
                    updateStudent(sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(name + "," + studentId);
            writer.newLine();
            System.out.println("Student added successfully");
        } catch (IOException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter student name to delete: ");
        String nameToDelete = sc.nextLine();
        File inputFile = new File(fileName);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] student = currentLine.split(",");
                if (!student[0].equalsIgnoreCase(nameToDelete)) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    found = true;
                }
            }
            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("Student deleted successfully");
            } else {
                tempFile.delete();
                System.out.println("Student not found");
            }
        } catch (IOException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    private static void viewStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }

    private static void updateStudent(Scanner sc) {
        System.out.print("Enter student name to update: ");
        String nameToUpdate = sc.nextLine();
        File inputFile = new File(fileName);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] student = currentLine.split(",");
                if (student[0].equalsIgnoreCase(nameToUpdate)) {
                    System.out.print("Enter new student ID: ");
                    String newStudentId = sc.nextLine();
                    writer.write(student[0] + "," + newStudentId);
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
                System.out.println("Student updated successfully");
            } else {
                tempFile.delete();
                System.out.println("Student not found");
            }
        } catch (IOException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }
}
