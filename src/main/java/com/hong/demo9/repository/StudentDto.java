package com.hong.demo9.repository;

import lombok.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String name;
    private Integer age;
    private List<String> courses;
}
