
    import java.io.*;
import java.util.Scanner;

public class Task4 {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete All Notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // clear buffer

            switch (choice) {
                case 1 -> addNote(sc);
                case 2 -> viewNotes();
                case 3 -> deleteNotes();
                case 4 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(note);
            writer.newLine();
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found yet!");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void deleteNotes() {
        try {
            new PrintWriter(FILE_NAME).close(); // clears file
            System.out.println("All notes deleted!");
        } catch (IOException e) {
            System.out.println("Error deleting notes: " + e.getMessage());
        }
    }
}

