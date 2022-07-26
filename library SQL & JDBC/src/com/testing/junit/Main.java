package com.testing.junit;

import java.sql.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
	
		System.out.println("-------------------Library Application-------------------"); 
		System.out.println("Select from the following options:"); 
		System.out.println(" 1: Show all users"); 
		System.out.println(" 2: Show all books"); 
		System.out.println(" 3: Show all users that have books loaned"); 
		System.out.println(" 4: Show all books that are loaned by users"); 
		System.out.println(" 5: Show all loaned books and the users who loaned them"); 
		System.out.println(" 6: Show all overdue books"); 
		System.out.println(" 7: Books in stock"); 
		System.out.println(" 8: Books low stock"); 
		System.out.println(" 9: Exit this program"); 
		System.out.print(">> ");
		
		Scanner input = new Scanner(System.in);
		int userInput = input.nextInt();
			
			switch (userInput) {
			case 1:
				System.out.println("All users:");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT * FROM users;");
				db.printResults();
				
				break;
			
			case 2:
				System.out.println("All books:");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT * FROM books;");
				db.printResults();
				
				break;
			
			case 3:
				System.out.println("All users that have books loaned:");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT u.user_id, u.first_name, u.last_name FROM users u JOIN loan l ON l.user_id=u.user_id;");
				db.printResults();
				
				break;
			
			case 4:
				System.out.println("All books loaned by users:");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT l.ISBN, b.title, COUNT(l.ISBN) FROM loan l JOIN books b ON b.ISBN=l.ISBN GROUP BY l.ISBN;");
				db.printResults();
				break;
			
			case 5:
				System.out.println("All loaned book and the users who loaned them");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT l.ISBN, b.title, u.user_id, u.first_name, u.last_name, l.date_lent, l.date_due FROM loan l JOIN books b ON b.ISBN=l.ISBN JOIN users u ON u.user_id=l.user_id;");
				db.printResults();
				break;
			
			case 6:
				System.out.println("Books currently overdue:");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT l.ISBN, b.title, u.user_id, u.first_name, u.last_name, l.date_lent, l.date_due FROM loan l JOIN books b ON b.ISBN=l.ISBN JOIN users u ON u.user_id=l.user_id WHERE l.date_due < CURDATE();");
				db.printResults();
				break;
			
			case 7:
				System.out.println("Books currently in stock");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT title FROM books WHERE stock > 0;");
				db.printResults();
				break;
			
			case 8:
				System.out.println("Books low in stock");
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
				db.query("SELECT title FROM books WHERE stock <= 3;");
				db.printResults();
				break;
			
			case 9:
				//close connection
				db.close();
				System.out.println(db.getConn());
				System.out.println("Program exited! ");
				return;
			}
			
		
	}//end of main public void
}//end of main class
