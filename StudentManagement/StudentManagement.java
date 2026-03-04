import java.util.*;

class Student {
    int rollNo;
    String name;
    double marks;

    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    void display() {
        System.out.println("Roll No: " + rollNo + ", Name: " + name + ", Marks: " + marks);
    }
}

public class StudentManagement {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice = 0;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Roll No: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid roll number!");
                        sc.nextLine();
                        break;
                    }
                    int roll = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Marks: ");
                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid marks!");
                        sc.nextLine();
                        break;
                    }
                    double marks = sc.nextDouble();

                    students.add(new Student(roll, name, marks));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (Student s : students) {
                            s.display();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll No to update: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid roll number!");
                        sc.nextLine();
                        break;
                    }
                    int updateRoll = sc.nextInt();
                    sc.nextLine();

                    boolean found = false;
                    for (Student s : students) {
                        if (s.rollNo == updateRoll) {
                            System.out.print("New Name: ");
                            s.name = sc.nextLine();

                            System.out.print("New Marks: ");
                            if (!sc.hasNextDouble()) {
                                System.out.println("Invalid marks!");
                                sc.nextLine();
                                break;
                            }
                            s.marks = sc.nextDouble();

                            System.out.println("Student updated successfully.");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid roll number!");
                        sc.nextLine();
                        break;
                    }
                    int deleteRoll = sc.nextInt();

                    boolean removed = students.removeIf(s -> s.rollNo == deleteRoll);

                    if (removed) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}