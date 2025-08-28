# Campus-Utility
CampusUtility is a desktop-based academic management system that enables users to add, edit, and view departments, instructors, courses, students, and grades. (2021)

There are two implementations:
- Generic Node (file-backed) - uses serialized `.dat` files (legacy).
- Database - uses MySQL with a small configuration layer. (revamp).


Requirements 
- JDK 8 with JavaFX(I am using Azul Zulu 8 FX) or you can use JDK+11 with OpenJFX dependencies.
- Gradle Wrapper (included)
- MySQL 8.

Getting Started & Run
- A. Generic Nodes
  - Create data files.
    You MUST run the `Loader` class once (it writes `student.dat`, `department.dat`, `instructor.dat`, `course.dat` and `enrollment.dat` to the working directory).
      - If you see a FileNotFoundException: `student.dat` or any other `.dat` file, your working directory is wrong or the files are empty. Run `Loader` again.
    Once you run the loader, you can run the `driver.java` class.

- B. Database (MySQL) 
  - Build Gradle.kts must include MySQLConnector.
  - Create a database (local or from a 3rd party)
     - I have 5 tables (`students`, `departments`, `instructors`, `courses` and `enrollments`).
  - If done correctly, you should be able to run it and insert data and get database on the console.

Troubleshooting
Some of the problems I encountered:
 - Package javafx.* does not exist.
   - IDE tends to switch JDK's so make sure to reapply Java 8 with JavaFX.
 - FileNotFoundException:
   - Loader was not ran or Loader failed to create and fill `.dat` files.
  
License 
- Apache-2.0 license
