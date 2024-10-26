package fall24.hsf301.slot02.gui;

import java.util.Scanner;
import fall24.hsf301.slot02.dao.JStudentDAO;
import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.service.IStudentService;
import fall24.hsf301.slot02.service.StudentService;

public class MainGUI {
    public static void main(String[] args) {
        String jpaName = "JPAs";
        
        JStudentDAO StudentDAO = new JStudentDAO(jpaName);
        Student Student = new Student("Duong", "Manh", 1);
        StudentDAO.save(Student);

        IStudentService StudentService = new StudentService(jpaName, 1);

        int n = -1;

        Scanner sc = new Scanner(System.in); // Create the Scanner only once

        do {
            System.out.println("Menu");
            System.out.println("0. Quit");
            System.out.println("1. Add");
            System.out.println("2. Show ALl");
            System.out.println("3. Find ID");
            System.out.println("4. Delete");
            System.out.println("5. Update");
            System.out.println("Enter your choice:");
            
            if (sc.hasNextInt()) {
                n = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
                continue;
            }

            switch (n) {
                case 0:
                    return;

                case 1:
                    Student s = StudentService.readInformation();
                    StudentService.save(s);
                    break;

                case 2:
                    StudentService.getStudents().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Enter StudentId: ");
                    if (sc.hasNextLong()) {
                        long id = sc.nextLong();
                        System.out.println(StudentService.findById(id));
                    } else {
                        System.out.println("Invalid input. Please enter a valid Student ID.");
                        sc.next(); // Clear the invalid input
                    }
                    break;

                case 4:
                    System.out.println("Enter StudentId to delete: ");
                    if (sc.hasNextLong()) {
                        long idToDelete = sc.nextLong();
                        StudentService.delete(idToDelete);
                        System.out.println("Delete Success");
                    } else {
                        System.out.println("Invalid input. Please enter a valid Student ID.");
                        sc.next(); // Clear the invalid input
                    }
                    break;

                case 5:
                    StudentService.update(StudentService.readInformation());
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (n != 0);

        sc.close(); // Close the Scanner when done
    }
}
