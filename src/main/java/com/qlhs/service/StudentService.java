package com.qlhs.service;

import com.qlhs.model.ClassSv;
import com.qlhs.model.Student;
import com.qlhs.repository.ClassSvRepo;
import com.qlhs.repository.StudentRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StudentService {
    private StudentRepo studentRepo;
    private ClassSvRepo classSvRepo;

    public StudentService(StudentRepo studentRepo, ClassSvRepo classSvRepo) {
        this.studentRepo = studentRepo;
        this.classSvRepo = classSvRepo;
    }

    public Student getStudentById(String id) {
        return studentRepo.getStudent(id);
    }

    public void displayStudent(Student student) {
        System.out.println("========== ***************** ==========");
        System.out.println("ID: " + student.getId());
        System.out.println("Tên: " + student.getName());
        System.out.println("Ngày sinh: " + student.getBirthDate());
        System.out.println("Tuổi: " + student.getAge());
        System.out.println("Giới tính: " + student.getGender());
        System.out.println("Chiều cao: " + student.getHeight() + " m");
        System.out.println("Cân nặng: " + student.getWeight() + " kg");
        System.out.println("Lớp học: " + student.getClassId());
        System.out.println("========================================");
    }

    private void validateStudent(Student student){
        if (classSvRepo.findById(student.getClassId()) == null) {
            throw new IllegalArgumentException("Lop khong ton tai voi id " + student.getClassId());
        }
    }

    public void addStudent(Student student) {
        if (studentRepo.getStudent(student.getId()) != null) {
            throw new IllegalArgumentException("Học sinh đã tồn tại!");
        }
        validateStudent(student);
        studentRepo.saveStudent(student);
        ClassSv classSv = classSvRepo.findById(student.getClassId());
        classSvRepo.addStudentToClass(classSv, student);
        System.out.println("Thêm học sinh mới thành công!");
    }

    public void changeStudent(Student olDstudent, Student newStudent) {
        validateStudent(newStudent);
        studentRepo.updateStudent(newStudent);
        if (!olDstudent.getClassId().equals(newStudent.getClassId())) {
            ClassSv oldClassSv = classSvRepo.findById(olDstudent.getClassId());
            ClassSv newClassSv = classSvRepo.findById(newStudent.getClassId());
            classSvRepo.addStudentToClass(newClassSv, newStudent);
            classSvRepo.removeStudentFromClass(oldClassSv, olDstudent);
        }
        System.out.println("Thông tin học sinh sau khi sửa là: ");
        displayStudent(newStudent);
    }

    public void deleteStudent(Student student, String id) {
        studentRepo.deleteStudent(id);
        ClassSv classOfStudent = classSvRepo.findById(student.getClassId());
        classSvRepo.removeStudentFromClass(classOfStudent, student);
        System.out.println("Xóa thành công!!");
    }
}
