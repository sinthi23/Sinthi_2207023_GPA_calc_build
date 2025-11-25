package com.example.calculator_builder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:calculator.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    public static void initDatabase() throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS courses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "course_name TEXT NOT NULL, " +
                    "course_code TEXT, " +
                    "credit INTEGER, " +
                    "teacher1 TEXT, " +
                    "teacher2 TEXT, " +
                    "grade TEXT" +
                    ")";
            conn.createStatement().execute(sql);
        }
    }

    public static Course insertCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses(course_name, course_code, credit, teacher1, teacher2, grade) VALUES(?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setInt(3, course.getCredit());
            stmt.setString(4, course.getTeacher1());
            stmt.setString(5, course.getTeacher2());
            stmt.setString(6, course.getGrade());
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Inserting course failed, no rows affected.");
            }
            try (java.sql.ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    course.setId(rs.getInt(1));
                }
            }
        }
        return course;
    }

    public static java.util.List<Course> getAllCourses() throws SQLException {
        java.util.List<Course> list = new java.util.ArrayList<>();
        String sql = "SELECT id, course_name, course_code, credit, teacher1, teacher2, grade FROM courses";
        try (Connection conn = getConnection();
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("id"),
                        rs.getString("course_name"),
                        rs.getString("course_code"),
                        rs.getInt("credit"),
                        rs.getString("teacher1"),
                        rs.getString("teacher2"),
                        rs.getString("grade")
                );
                list.add(c);
            }
        }
        return list;
    }

    public static boolean updateCourse(Course course) throws SQLException {
        if (course.getId() == null) return false;
        String sql = "UPDATE courses SET course_name = ?, course_code = ?, credit = ?, teacher1 = ?, teacher2 = ?, grade = ? WHERE id = ?";
        try (Connection conn = getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setInt(3, course.getCredit());
            stmt.setString(4, course.getTeacher1());
            stmt.setString(5, course.getTeacher2());
            stmt.setString(6, course.getGrade());
            stmt.setInt(7, course.getId());
            int affected = stmt.executeUpdate();
            return affected > 0;
        }
    }

    public static boolean deleteCourse(int id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
