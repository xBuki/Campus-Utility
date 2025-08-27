package org.buki.DTO;

import java.io.Serializable;

public class Department implements Comparable<Department>, Serializable {

    private int dID;
    private String dName;

    public Department() {

    }

    public Department(int dID, String dName) {

        this.dID = dID;
        this.dName = dName;
    }

    public int getdID() {
        return dID;
    }

    public void setcID(int dID) {
        this.dID = dID;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    @Override
    public int compareTo(Department o) {
        if (this.dID == o.dID) {
            return 0;
        } else if (this.dID > o.dID) {
            return 1;
        }
        return -1;
    }

    public boolean equals(Department o) {
        if (this.dID == o.dID) {
            return true;
        } else {
            return false;
        }
    }

}