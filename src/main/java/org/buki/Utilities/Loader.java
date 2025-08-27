package org.buki.Utilities;

import org.buki.DTO.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Loader {

    public static void main(String[] args) throws IOException {
        Student st1 = new Student(1, "Jesus", "Estrada", "Main Street");
        Student st2 = new Student(2, "Benjamin", "Ubach", "One Street");
        Student st3 = new Student(3, "Edson", "Castellanos", "Deeble Street");
        Student st4 = new Student(4, "Katarina", "Nose", "Ninth Street");
        Department d1 = new Department(5, "English");
        Department d2 = new Department(2, "Computer Science");
        Instructor ints1 = new Instructor(1, "Patil", "Ashok", "Computer Science");
        Course c = new Course(1, "Java", "Patil", 1, "Computer Science", 2);
        Enrollment en = new Enrollment(1, 1, "Spring", "2015", "X", "Jesus Estrada", "Java", "Patil", 1, "Computer Science", 2);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<Instructor> instructors = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Enrollment> er = new ArrayList<>();
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        departments.add(d1);
        departments.add(d2);
        instructors.add(ints1);
        courses.add(c);
        er.add(en);

        // Create the stream objects.
        FileOutputStream outStream = new FileOutputStream("student.dat");
        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

        // Write the serialized objects to the file.
        for (int i = 0; i < students.size(); i++) {
            objectOutputFile.writeObject(students.get(i));
        }

        outStream = new FileOutputStream("department.dat");
        objectOutputFile = new ObjectOutputStream(outStream);

        // Write the serialized objects to the file.
        for (int i = 0; i < departments.size(); i++) {
            objectOutputFile.writeObject(departments.get(i));
        }

        outStream = new FileOutputStream("instructor.dat");
        objectOutputFile = new ObjectOutputStream(outStream);

        // Write the serialized objects to the file.
        for (int i = 0; i < instructors.size(); i++) {
            objectOutputFile.writeObject(instructors.get(i));
        }

        outStream = new FileOutputStream("course.dat");
        objectOutputFile = new ObjectOutputStream(outStream);

        // Write the serialized objects to the file.
        for (int i = 0; i < courses.size(); i++) {
            objectOutputFile.writeObject(courses.get(i));
        }

        outStream = new FileOutputStream("enrollment.dat");
        objectOutputFile = new ObjectOutputStream(outStream);

        // Write the serialized objects to the file.
        for (int i = 0; i < er.size(); i++) {
            objectOutputFile.writeObject(er.get(i));
        }

        // Close the file.
        objectOutputFile.close();

        System.out.println("The serialized objects " + "were written to the Objects.dat file.");
    }
}

