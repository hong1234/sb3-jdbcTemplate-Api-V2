package com.hong.demo9.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public class StudentsExtractor implements ResultSetExtractor<List<Student>> {
    @Override
    public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Student student;
        List<Course> courses;
        // Map<String, Course> courses;
        Map<String, Student> studentMap = new HashMap<>();

        while(rs.next()) {

            if (studentMap.containsKey(rs.getString("STUDENT_ID"))) {
                student = studentMap.get(rs.getString("STUDENT_ID"));
                courses = student.getCourses();
            } else {
                student = new Student();
                student.setStudentId(rs.getInt("STUDENT_ID"));
                student.setName(rs.getString("NAME"));
                student.setAge(rs.getInt("AGE"));
                courses = new ArrayList<>();
                // courses = new HashMap<>();
            }

            if (rs.getString("COURSE_ID") != null){
                Course course = new Course();
                course.setCourseId(rs.getInt("COURSE_ID"));
                course.setUuid(rs.getString("UUID"));
                course.setDescription(rs.getString("DESCRIPTION"));
                courses.add(course);
                // courses.put(course.getUuid(), course);
            }

            student.setCourses(courses);
            studentMap.put(rs.getString("STUDENT_ID"), student);
        }

        return new ArrayList<>(studentMap.values());
    }
}
