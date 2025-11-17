package com.example.calculator_builder;

public class Course {
    private final String courseName;
    private final String courseCode;
    private final int credit;
    private final String teacher1;
    private final String teacher2;
    private final String grade;

    public Course(String courseName, String courseCode, int credit, String teacher1, String teacher2, String grade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.teacher1 = teacher1;
        this.teacher2 = teacher2;
        this.grade = grade;
    }


    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public int getCredit() { return credit; }
    public String getGrade() { return grade; }

    public String getTeacher1() { return teacher1 != null ? teacher1 : ""; }
    public String getTeacher2() { return teacher2 != null ? teacher2 : ""; }


    public String getTeachers() {
        if (teacher1 == null || teacher1.isEmpty()) {
            return "No instructor";
        }
        if (teacher2 == null || teacher2.isEmpty()) {
            return teacher1;
        }
        return teacher1 + ", " + teacher2;
    }
}