package org.buki.DTO;

import java.io.Serializable;

public class Enrollment implements Comparable<Enrollment>, Serializable {
    private Integer sID;
    private int cID;
    private String semseter;
    private String year;
    private String grade;

    private String studentName;
    private String courseName;
    private String profName;
    private int instructorID;
    private String dep;
    private int depID;

    public Enrollment() {

    }



    public Enrollment(Integer sID, int cID, String semseter, String year, String grade, String studentName,
                      String courseName, String profName, int instructorID, String dep, int depID) {
        super();
        this.sID = sID;
        this.cID = cID;
        this.semseter = semseter;
        this.year = year;
        this.grade = grade;
        this.studentName = studentName;
        this.courseName = courseName;
        this.profName = profName;
        this.instructorID = instructorID;
        this.dep = dep;
        this.depID = depID;
    }



    public int getSID() {
        return sID;
    }

    public void setSID(int sID) {
        this.sID = sID;
    }

    public int getCID() {
        return cID;
    }

    public void setCID(int cID) {
        this.cID = cID;
    }

    public String getSemseter() {
        return semseter;
    }

    public void setSemseter(String semseter) {
        this.semseter = semseter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getGrade() {
        if (grade == null) {
            return "X";
        } else {
            return grade;
        }
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    @Override
    public int compareTo(Enrollment o) {
        if (this.sID == o.sID) {
            return 0;
        } else if (this.sID > o.sID) {
            return 1;
        }
        return -1;
    }

    public boolean equals(Enrollment o) {
        if (this.sID == o.sID) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Student ID: " + sID + "\nStudent Name: " + studentName + "\nCourse ID: " + cID + "\nCourse Name: "
                + courseName + "\nProfessors Name: " + profName + "\nProfID" + instructorID + "\nSemester: " + semseter
                + "\nYear: " + year + "\nDepartment: " + dep + "\nDepartment ID: " + depID;

    }
}
