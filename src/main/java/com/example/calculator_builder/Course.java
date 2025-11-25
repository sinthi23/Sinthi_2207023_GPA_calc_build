package com.example.calculator_builder;

public class Course {
    private Integer id; // DB primary key, null if not persisted
    private final String courseName;
    private final String courseCode;
    private final int credit;
    private final String teacher1;
    private final String teacher2;
    private final String grade;

    public Course(Integer id, String courseName, String courseCode, int credit, String teacher1, String teacher2, String grade) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.teacher1 = teacher1;
        this.teacher2 = teacher2;
        this.grade = grade;
    }

    public Course(String courseName, String courseCode, int credit, String teacher1, String teacher2, String grade) {
        this(null, courseName, courseCode, credit, teacher1, teacher2, grade);
    }

    // REQUIRED GETTERS FOR TABLE COLUMNS:

    // For "Course Name" column - must match PropertyValueFactory("courseName")
    public String getCourseName() {
        return courseName;
    }

    // For "Code" column - must match PropertyValueFactory("courseCode")
    public String getCourseCode() {
        return courseCode;
    }

    // For "Credits" column - must match PropertyValueFactory("credit")
    public int getCredit() {
        return credit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // For "Grade" column - must match PropertyValueFactory("grade")
    public String getGrade() {
        return grade;
    }

    // For "Instructors" column - must match PropertyValueFactory("teachers")
    public String getTeachers() {
        if (teacher1 == null || teacher1.isEmpty()) {
            return "No instructor";
        }
        if (teacher2 == null || teacher2.isEmpty()) {
            return teacher1;
        }
        return teacher1 + ", " + teacher2;
    }

    // Optional: Additional getters for debugging
    public String getTeacher1() {
        return teacher1 != null ? teacher1 : "";
    }

    public String getTeacher2() {
        return teacher2 != null ? teacher2 : "";
    }

    // For debugging
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", credit=" + credit +
                ", teacher1='" + teacher1 + '\'' +
                ", teacher2='" + teacher2 + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}