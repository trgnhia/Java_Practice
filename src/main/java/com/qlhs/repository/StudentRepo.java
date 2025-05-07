package com.qlhs.repository;

import com.qlhs.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    List<Student> lsStudents = new ArrayList<Student>();

    public void saveStudent(Student student) {
        lsStudents.add(student);
    }

    public Student getStudent(String id) {
        for (Student student : lsStudents) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < lsStudents.size(); i++) {
            if (lsStudents.get(i).getId().equals(updatedStudent.getId())) {
                lsStudents.set(i, updatedStudent);
            }
        }
    }

    public boolean deleteStudent(String id) {
        return lsStudents.removeIf(student -> student.getId().equals(id));
    }
}
