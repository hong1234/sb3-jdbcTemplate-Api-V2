package com.hong.demo9.repository;

// import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.UUID;
import java.util.List;
// import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private Integer studentId;
    private String name;
    private Integer age;
    private List<Course> courses;
    // private Map<String, Course> courses;
}
