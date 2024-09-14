package com.fe.main;

import java.util.Scanner;

import com.fe.dao.StudentDAO;
import com.fe.pojo.Student;

public class StudentMain {

	public static void main(String[] args) {
		System.out.println("++++++++++++++MENU++++++++++++++");
		System.out.println("1. Add Student  :");
		System.out.println("2. Delete Student: ");
		System.out.println("3. Update Student: ");
		System.out.println("4. Get a Student: ");
		System.out.println("0. Quit");
		System.out.println("+++++++++++++++++++++++++++++++++");

		int inputKey = -1;
		while (inputKey != 0) {
			Scanner console = new Scanner(System.in);

			System.out.println("Please enter a number: ");
			inputKey = console.nextInt();

			StudentDAO studentDAO = new StudentDAO("JPAs");
			Student student = new Student("Hoang", "Luu", 10);
			switch (inputKey) {
			case 0:
				break;
			case 1:
				studentDAO.save(student);
				break;
			case 2:
				studentDAO.delete(1L);
				break;
			case 3:
				student = new Student(1L, "Duong", "Nguyen",  9);
				studentDAO.updateStudent(student);
				break;
			case 4:
				studentDAO.findById(1L);
				break;
			default:
				break;
			}

		}

	}

}
