package com.qlhs.controller;

import com.qlhs.dto.StudentDTO;
import com.qlhs.mapper.StudentMapper;
import com.qlhs.model.Student;
import com.qlhs.service.StudentService;
import com.qlhs.util.DateUtils;

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
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Không được để trống tên học sinh!");
        }
        System.out.println("Nhập ngày tháng năm sinh (dd-MM-YYYY): ");
        String birth = scanner.nextLine();
        LocalDate birthDate = DateUtils.parseDate(birth);
        System.out.println("Nhập giới tính: ");
        String gender = scanner.nextLine();
        if (gender == null || gender.isEmpty()) {
            throw new IllegalArgumentException("Không được để trống giới tính hs !");
        }
        System.out.println("Nhập chiều cao(m): ");
        String heightStr = scanner.next();
        heightStr = heightStr.replace(",", ".");
        try {
            double height = Double.parseDouble(heightStr);
            if (height <= 0) {
                throw new IllegalArgumentException("Chiều cao phải lớn hơn 0!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Chiều cao phải là số hợp lệ!");
        }

        System.out.println("Nhập cân nặng(kg): ");
        String weightStr = scanner.next();
        try {
            double weight = Double.parseDouble(weightStr);
            if (weight <= 0) {
                throw new IllegalArgumentException("Cân nặng phải lớn hơn 0!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cân nặng phải là số hợp lệ!");
        }
        scanner.nextLine();
        System.out.println("Nhập id lớp học của hs:  ");
        String classID = scanner.nextLine();
        StudentDTO studentDTO = new StudentDTO(birthDate, classID, gender, heightStr, null, name, weightStr);
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
            service.changeStudent(exsitStudent, updatedStudent);
        } else {
            System.out.println("Không tìm thấy hs với id " + id);
        }
    }

    public void deleteStudent() {
        System.out.println("Nhập id hs cần xóa!");
        String id = scanner.nextLine();
        Student exsitStudent = service.getStudentById(id);
        if (exsitStudent != null) {
            System.out.println("Thông tin học sinh sẽ xóa: ");
            service.displayStudent(exsitStudent);
            service.deleteStudent(exsitStudent, id);
        } else {
            System.out.println("Không tìm thấy hs với id " + id);
        }
    }
}
