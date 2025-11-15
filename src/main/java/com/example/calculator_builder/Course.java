package com.example.calculator_builder;

public class Course {
    private String courseName;
    private String courseCode;
    private int credit;
    private String teacher1;
    private String teacher2;
    private String grade;

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
    public String getTeacher1() { return teacher1; }
    public String getTeacher2() { return teacher2; }
    public String getGrade() { return grade; }
}
