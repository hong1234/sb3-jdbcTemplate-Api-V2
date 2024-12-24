package com.hong.demo9.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.hong.demo9.repository.StudentRepository;
import com.hong.demo9.repository.CourseRepository;
import com.hong.demo9.repository.Student;
import com.hong.demo9.repository.Course;
import com.hong.demo9.repository.StudentCourse;
// import java.util.*;
import java.util.UUID;

@AllArgsConstructor
@Service
public class StudentService {

    StudentRepository studentRepository;
    CourseRepository courseRepository;

    public Iterable<Student> allStudents(){
        return studentRepository.getAllStudents();
    }

    public Student getStudent(Integer studentId){
        studentRepository.findById(studentId);
        return studentRepository.getStudentById(studentId);
    }

    public Student addStudent(Student student){
        return studentRepository.saveStudent(student);
    }

    public void deleteStudent(Integer studentId){
        studentRepository.findById(studentId);
        studentRepository.deleteById(studentId);
    }

    public void addStudentCourse(StudentCourse stucou){
        studentRepository.findAssign(stucou.getStudentId(), stucou.getCourseId());
        studentRepository.assignCourse(stucou.getStudentId(), stucou.getCourseId());
    }

    public Iterable<Course> getCourses(){
        return courseRepository.getAllCourses();
    }

    public Course getCourseById(Integer courseId){
        return courseRepository.getCourseById(courseId);
    }

    public Course addCourse(Course course){
        course.setUuid(UUID.randomUUID().toString());
        return courseRepository.saveCourse(course);
    }
}
