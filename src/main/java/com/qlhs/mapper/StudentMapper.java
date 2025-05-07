package com.qlhs.mapper;

import com.qlhs.dto.StudentDTO;
import com.qlhs.model.Student;

public class StudentMapper {
    public Student toStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setId(studentDTO.getId());
        student.setBirthDate(studentDTO.getBirthDate());
        student.setGender(studentDTO.getGender());
        try {
            student.setHeight(Double.parseDouble(studentDTO.getHeight().replace(",", ".")));
        } catch (NumberFormatException e) {
            System.out.println("Chiều cao là số thực!");
        }
        try {
            student.setWeight(Double.parseDouble(studentDTO.getWeight().replace(",", ".")));
        } catch (NumberFormatException e) {
            System.out.println("Cân nặng là số thực!");
        }
        student.setClassId(studentDTO.getClassId());
        student.setAge(studentDTO.getAge());
        return student;
    }

}
