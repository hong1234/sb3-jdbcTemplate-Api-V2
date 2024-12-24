package com.hong.demo9.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.hong.demo9.service.StudentService;

//import com.hong.demo9.repository.Student;
//import com.hong.demo9.repository.Course;
//import com.hong.demo9.repository.StudentCourse;

import com.hong.demo9.repository.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AppController {

    private StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<Iterable<Student>> allStudents(){
        return ResponseEntity.ok(studentService.allStudents());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> geStudent(@PathVariable("studentId") Integer studentId){
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/course")
    public ResponseEntity<Iterable<Course>> allCourses(){
        return ResponseEntity.ok(studentService.getCourses());
    }

    @PostMapping("/student/course")
    public void addStudentCourse(@RequestBody StudentCourse stucou){
        studentService.addStudentCourse(stucou);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable("courseId") Integer courseId){
        return ResponseEntity.ok(studentService.getCourseById(courseId));
    }

    @PostMapping("/course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        return ResponseEntity.ok(studentService.addCourse(course));
    }

    // TESTS ----

    @GetMapping("/test")
    public ResponseEntity<List<String>> test(){
        List<String> courses = new ArrayList<>();
        courses.add("abc123");
        courses.add("ABC321");
        courses.add("BBC333");
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/test2")
    public ResponseEntity<StudentDto> test2(@RequestBody StudentDto dto){
        return ResponseEntity.ok(dto);
    }



//    @PostMapping("/add")
//    public ResponseEntity<Student> addStudent(@RequestBody Student student){
//        return ResponseEntity.ok(student);
//    }

//    @PostMapping(path="/add", consumes="application/json")
//    public Student addStudent(@RequestBody Student student){
//        return student;
//    }

//    @PostMapping("/add")
//    public Student addStudent(@RequestBody Student student){
//        return student;
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Iterable<Course>> addStudent(@RequestBody Student student){
//        return ResponseEntity.ok(student.getCourses());
//    }

}
