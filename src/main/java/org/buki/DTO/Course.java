package org.buki.DTO;

import java.io.Serializable;

public class Course implements Comparable<Course>, Serializable {

    private int cID;
    private String cName;
    private String instructor;
    private int instID;
    private String department;
    private int depID;

    public Course() {

    }

    public Course(int cID, String cName, String instructor, int instID, String department, int depID) {
        super();
        this.cID = cID;
        this.cName = cName;
        this.instructor = instructor;
        this.instID = instID;
        this.department = department;
        this.depID = depID;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    public int getInstID() {
        return instID;
    }

    public void setInstID(int instID) {
        this.instID = instID;
    }

    @Override
    public int compareTo(Course o) {
        if (this.cID == o.cID) {
            return 0;
        } else if (this.cID > o.cID) {
            return 1;
        }
        return -1;
    }

    public boolean equals(Course o) {
        if (this.cID == o.cID) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "ID: " + cID + "\nCourse Name: " + cName + "\nDepartment: " + department + "\nDep ID: " + depID
                + "\n Instructor: " + instructor + "\nInstructor ID: " + instID;
    }

}
