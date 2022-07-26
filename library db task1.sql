-- DROP DATABASE IF EXISTS library;
-- CREATE DATABASE library;
USE library;

DROP TABLE IF EXISTS loan;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

-- Creating a table of books containing title and author info and unique ISBN number (ID) for each book
CREATE TABLE books(
title VARCHAR(600) NOT NULL,
author VARCHAR(50) NOT NULL,
ISBN VARCHAR(20) NOT NULL,
PRIMARY KEY (ISBN),
stock INT
);

INSERT INTO books VALUES ("HP & the goblet of fire", "JK Rowling", "155192515X", 6);
INSERT INTO books VALUES ("HP & the order of the pheonix", "JK Rowling", "0439957869", 7);
INSERT INTO books VALUES ("HP & the deathly hallows", "JK Rowling", "0747591067", 4);
INSERT INTO books VALUES ("Twilight New Moon", "Stephenie Meyer", "0316160199", 9);
INSERT INTO books VALUES ("Gone", "Michael Grant", "1405242353", 5);
INSERT INTO books VALUES ("A song of ice and fire", "George R R Martin", "0553897845", 4);
INSERT INTO books VALUES ("A Child Called 'It'", "Dave Pelzer", "0752837508", 2);
INSERT INTO books VALUES ("Fantastic Mr. Fox", "Roald Dahl", "0142410349", 4);
INSERT INTO books VALUES ("The Cat in the Hat", "Dr. Seuss", "000734869X", 3);
INSERT INTO books VALUES ("Of Mice and Men", "John Steinbeck", "0140186425", 3);
INSERT INTO books VALUES ("Great Expectations", "Charles Dickens", "0393960692", 5);
INSERT INTO books VALUES ("Wuthering Heights", "Emily Bronte", 0141439556, 4);
INSERT INTO books VALUES ("Price and Prejudice", "Jane Austen", "0486284735", 4);

-- Creating a table for users containing their info and a user ID 
CREATE TABLE users(
user_id INT AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
contact_number INT NOT NULL,
address_line1 VARCHAR(50) NOT NULL,
address_line2 VARCHAR(50),
county VARCHAR(50) NOT NULL,
postcode VARCHAR(20) NOT NULL,
PRIMARY KEY (user_id)
);

INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Jade", "Sale", 01214567891, "123 Bridge Lane", "", "Warwickshire", "CV11 9BT");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Danny", "Kirwan", 01214567891, "123 Bridge Lane", "", "Warwickshire", "CV11 9BT");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("John", "Smith", 01217664925, "52 Trine Way", "", "Warwickshire", "CV10 5TY");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Jane", "Doe", 01219305728, "21 Pine Cresent", "", "Warwickshire", "CV11 9BT");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Bob", "Sponge", 01234567891, "124 Conch St", "Pineapple under the sea", "Bikini Bottom", "Pacific Ocean");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Peter", "Parker", 02088888888, "246 Webb Ave", "Queens", "NYC", "SP1 D47");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Tony", "Stark", 01214577631, "The Razor House", "Malibu", "California", "1R0N M4N");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Adam", "Plum", 01216185728, "15 Grove Farm", "", "Warwickshire", "CV8 2AJ");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Michael", "Aldridge", 01213485299, "915 Hollow Lane", "", "Warwickshire", "CV7 5LP");
INSERT INTO users (first_name, last_name, contact_number, address_line1, address_line2, county, postcode) 
VALUES ("Nathan", "Drake", 01210783425, "353 Heath End Rd", "", "Warwickshire", "CV10 7HG");

-- creating a table containing user ID from the users table and ISBN ID from the books table and joining them together to show which user loaned which book.
-- Table also includes dates for book loaned (date_lent) and book due back (date_due)
CREATE TABLE loan(
ISBN VARCHAR(20) NOT NULL,
user_id INT NOT NULL,
date_lent DATE NOT NULL,
date_due DATE NOT NULL,
-- foreign key pulls data from the second column stated (references column) to the first column stated (foreign key column) 
FOREIGN KEY (ISBN) REFERENCES books(ISBN),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO loan VALUES("0316160199", 4, "2022-07-02", "2022-07-07");
INSERT INTO loan VALUES("0439957869", 2, "2022-07-17", "2022-07-22");
INSERT INTO loan VALUES("1405242353", 1, "2022-07-17", "2022-07-22");
INSERT INTO loan VALUES("0140186425", 9, "2022-07-13", "2022-07-18");
INSERT INTO loan VALUES("000734869X", 6, "2022-07-05", "2022-07-10");
INSERT INTO loan VALUES("0747591067", 1, "2022-07-17", "2022-07-22");
INSERT INTO loan VALUES("0393960692", 3, "2022-07-09", "2022-07-14");
INSERT INTO loan VALUES("0439957869", 7, "2022-07-11", "2022-07-16");

-- TASK 2

-- Query 1
-- Show all users
SELECT * FROM users;

-- Query 2
-- Show all books
SELECT * FROM books;


-- Query 3
-- Show all users that have loaned a book
SELECT u.user_id AS "User ID", u.first_name AS "First Name", u.last_name AS "Last Name", l.ISBN AS "ISBN Loaned" FROM users u
JOIN loan l ON l.user_id=u.user_id;

-- Query 4
-- Show all books loaned by a user (no dupes)
-- SELECT DISTINCT ISBN FROM loan; << This would show the distinct ISBN only but I felt like this is 1) too basic and 2) doesn't know a true representation of how many of that book is loaned out. 
-- Instead I used count method and group by to show which books are on loan and count how many of each book are on loan
SELECT l.ISBN, b.title AS "Title", COUNT(l.ISBN) AS "On Loan" FROM loan l
JOIN books b ON b.ISBN=l.ISBN
GROUP BY l.ISBN;

-- Query 5 (combined query 3 & 4)
-- Show all books loaned (title, author, date out and date due) and the user who loaned it
SELECT l.ISBN, b.title AS "Book", u.user_id AS "User ID", u.first_name AS "First Name", u.last_name AS "Last Name", l.date_lent AS "Checked Out", l.date_due AS "Due In" FROM loan l
JOIN books b ON b.ISBN=l.ISBN
JOIN users u ON u.user_id=l.user_id;

-- Query 6
-- Shows all books that are overdue, showing the user who has it, the book title & author, and the date it was due
SELECT l.ISBN, b.title AS "Book", u.user_id AS "User ID", u.first_name AS "First Name", u.last_name AS "Last Name", l.date_lent AS "Checked Out", l.date_due AS "Due In" FROM loan l
JOIN books b ON b.ISBN=l.ISBN
JOIN users u ON u.user_id=l.user_id
WHERE l.date_due < CURDATE(); -- NOW() method would also work here CURDATE() returns current date whereas NOW() returns current date and time. 

-- Query 7
-- Show all books in stock
SELECT title FROM books WHERE stock > 0;

-- Query 8
-- Show books low in stock
SELECT title FROM books WHERE stock <= 3;

COMMIT;