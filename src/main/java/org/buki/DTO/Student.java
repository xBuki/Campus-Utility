package org.buki.DTO;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {
    private int sID;
    private String firstName;
    private String lastName;
    private String address;

    public Student() {

    }

    public Student(int sID, String firstName, String lastName, String address) {
        this.sID = sID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Student o) {
        if (this.sID == o.sID) {
            return 0;
        } else if (this.sID > o.sID) {
            return 1;
        }
        return -1;
    }

    public boolean equals(Student o) {
        if (this.sID == o.sID) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return sID + "First Name " + firstName + " Last Name " + lastName + " Address " + address;
    }
}

