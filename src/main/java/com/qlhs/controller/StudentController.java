package com.qlhs.controller;

import com.qlhs.dto.StudentDTO;
import com.qlhs.mapper.StudentMapper;
import com.qlhs.model.Student;
import com.qlhs.service.StudentService;

import java.time.LocalDate;
import java.util.Scanner;

public class StudentController {
    private StudentService service;
    private StudentMapper mapper;
    private Scanner scanner;

    public StudentController(StudentService service, StudentMapper mapper, Scanner scanner) {
        this.service = service;
        this.mapper = mapper;
        this.scanner = scanner;
    }

    public StudentDTO inputStudentInfor() {
        System.out.println("Nhập tên học sinh: ");
        String name = scanner.nextLine();
        System.out.println("Nhập ngày tháng năm sinh (dd-MM-YYYY): ");
        String birth = scanner.nextLine();
        LocalDate birthDate = service.parseDate(birth);
        System.out.println("Nhập giới tính: ");
        String gender = scanner.nextLine();
        System.out.println("Nhập chiều cao(m): ");
        String height = scanner.next();
        System.out.println("Nhập cân nặng(kg): ");
        String weight = scanner.next();
        scanner.nextLine();
        System.out.println("Nhập id lớp học của hs:  ");
        String classID = scanner.nextLine();
        StudentDTO studentDTO = new StudentDTO(birthDate, classID, gender, height, null, name, weight);
        return studentDTO;
    }

    public void createStudent() {
        System.out.println("Nhập id học sinh: ");
        String id = scanner.nextLine();
        StudentDTO studentDTO = inputStudentInfor();
        studentDTO.setId(id);
        Student newStudent = mapper.toStudent(studentDTO);
        service.addStudent(newStudent);
        System.out.println();
    }

    public void updateStudent() {
        System.out.println("Nhập id học sinh cần sửa: ");
        String id = scanner.nextLine();
        Student exsitStudent = service.getStudentById(id);
        if (exsitStudent != null) {
            System.out.println("Thông tin học sinh trước khi sửa: ");
            service.displayStudent(exsitStudent);
            StudentDTO updatedStudentDTO = inputStudentInfor();
            updatedStudentDTO.setId(id);
            Student updatedStudent = mapper.toStudent(updatedStudentDTO);
            service.changeStudent(exsitStudent,updatedStudent);
        } else System.out.println("Không tìm thấy hs với id " + id);
    }

    public void deleteStudent(){
        System.out.println("Nhập id hs cần xóa!");
        String id = scanner.nextLine();
        Student exsitStudent= service.getStudentById(id);
        if(exsitStudent != null) {
            System.out.println("Thông tin học sinh sẽ xóa: ");
            service.displayStudent(exsitStudent);
            service.deleteStudent(exsitStudent,id);
        }
        else System.out.println("Không tìm thấy hs với id " + id);
    }
}
