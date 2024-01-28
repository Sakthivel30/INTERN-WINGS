import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    private String name;
    private int age;
    private String course;
    private double grade;

    public Student(String studentId, String name, int age, String course, double grade) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
        this.grade = grade;
    }

    // Getter methods

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                ", grade=" + grade +
                '}';
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = loadStudentsFromFile();
    }

    private ArrayList<Student> loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    // Other methods for updating, deleting, and generating reports can be added here
}

public class StudentManagementSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Display All Students");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addStudent(scanner, studentManagementSystem);
                    break;
                case 2:
                    searchStudent(scanner, studentManagementSystem);
                    break;
                case 3:
                    displayAllStudents(studentManagementSystem);
                    break;
                case 4:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentManagementSystem studentManagementSystem) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        // Add validation for unique student ID if needed

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student course: ");
        String course = scanner.nextLine();

        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Student newStudent = new Student(studentId, name, age, course, grade);
        studentManagementSystem.addStudent(newStudent);

        System.out.println("Student added successfully!");
    }

    private static void searchStudent(Scanner scanner, StudentManagementSystem studentManagementSystem) {
        System.out.print("Enter student ID to search: ");
        String studentId = scanner.nextLine();

        Student foundStudent = studentManagementSystem.findStudentById(studentId);

        if (foundStudent != null) {
            System.out.println("Student found:\n" + foundStudent);
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static void displayAllStudents(StudentManagementSystem studentManagementSystem) {
        ArrayList<Student> students = studentManagementSystem.getStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}