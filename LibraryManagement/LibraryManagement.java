import java.io.*;
import java.util.*;

public class LibraryManagement {
    static final String FILE_NAME = "library.txt";

    public static void addBook(String book) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, true);
        fw.write(book + "\n");
        fw.close();
        System.out.println("Book added.");
    }

    public static void viewBooks() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Name: ");
                    String book = sc.nextLine();
                    addBook(book);
                    break;
                case 2:
                    viewBooks();
                    break;
            }
        } while (choice != 3);

        sc.close();
    }
}