package com.hong.demo9.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.jdbc.core.ResultSetExtractor;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class CourseRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public Iterable<Course> getAllCourses() {
        String sql = "SELECT * FROM COURSE";
        return jdbcTemplate.query(sql, new RowMapper<Course>(){
            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course course = new Course();
                course.setCourseId(rs.getInt("COURSE_ID"));
                course.setUuid(rs.getString("UUID"));
                course.setDescription(rs.getString("DESCRIPTION"));
                return course;
            }
        });
    }

    public Course saveCourse(Course course) {
        String sql = "INSERT INTO COURSE (uuid, description) VALUES (:uuid, :description)";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("uuid", course.getUuid())
                .addValue("description", course.getDescription());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, parameters, generatedKeyHolder);
        Number key = generatedKeyHolder.getKey();
        return getCourseById(key.intValue());
    }

    public Course getCourseById(Integer courseId) {
        String sql = """
            SELECT c.COURSE_ID, c.UUID, c.DESCRIPTION, s.STUDENT_ID, s.NAME, s.AGE
            FROM COURSE c
            LEFT JOIN STUDENT_COURSE cb ON c.COURSE_ID = cb.COURSE_ID
            LEFT JOIN STUDENT s ON cb.STUDENT_ID = s.STUDENT_ID
            WHERE c.COURSE_ID = :courseId
        """;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseId", courseId);

        return jdbcTemplate.query(sql, parameters, new ResultSetExtractor<Course>() {
            @Override
            public Course extractData(ResultSet rs) throws SQLException, DataAccessException {
                Course course = null;
                Map<String, Student> studentMap = new HashMap<>();

                while(rs.next()) {
                    if (course == null) {
                        course = new Course();
                        course.setCourseId(rs.getInt("COURSE_ID"));
                        course.setUuid(rs.getString("UUID"));
                        course.setDescription(rs.getString("DESCRIPTION"));
                    }

                    if (rs.getString("STUDENT_ID") != null) {
                        Student student = new Student();
                        student.setStudentId(rs.getInt("STUDENT_ID"));
                        student.setName(rs.getString("NAME"));
                        student.setAge(rs.getInt("AGE"));
                        // student.setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime());

                        studentMap.put(Integer.toString(student.getStudentId()), student);
                    }
                }

                if(course != null) {
                    List<Student> students = new ArrayList<>(studentMap.values());
                    course.setStudents(students);
                }
                return course;
            }
        });

    }

}
