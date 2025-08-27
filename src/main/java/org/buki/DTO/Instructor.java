package org.buki.DTO;

import java.io.Serializable;

public class Instructor implements Comparable<Instructor>, Serializable {

    private int instID;
    private String fname;
    private String lname;
    private String dep;
    private int depID;

    public Instructor() {

    }

    public Instructor(int instID, String fname, String lname, String dep) {
        this.instID = instID;
        this.fname = fname;
        this.lname = lname;
        this.dep = dep;
    }

    public int getInstID() {
        return instID;
    }

    public void setInstID(int instID) {
        this.instID = instID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    @Override
    public int compareTo(Instructor o) {
        if (this.instID == o.instID) {
            return 0;
        } else if (this.instID > o.instID) {
            return 1;
        }
        return -1;
    }

    public boolean equals(Instructor o) {
        if (this.instID == o.instID) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "ID: " + instID + "\n First Name: " +  fname + "\n Last Name: " + lname + "\n Department Name: " + dep + "\n Department ID: " + depID;

    }

}

