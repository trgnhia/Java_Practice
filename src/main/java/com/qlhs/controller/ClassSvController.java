package com.qlhs.controller;

import com.qlhs.dto.ClassSvDTO;
import com.qlhs.mapper.ClassSvMapper;
import com.qlhs.model.ClassSv;
import com.qlhs.service.ClassSvService;

import java.util.Scanner;

public class ClassSvController {
    private ClassSvService service;
    private ClassSvMapper mapper;
    private Scanner scanner;

    public ClassSvController(ClassSvService service, ClassSvMapper mapper, Scanner scanner) {
        this.mapper = mapper;
        this.service = service;
        this.scanner = scanner;
    }


    public void createClass() {
        System.out.println("Nhập mã lớp học: ");
        String id = scanner.nextLine();
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID lớp học không được để trống!");
        }
        System.out.println("Nhập tên lớp học: ");
        String name = scanner.nextLine();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Tên lớp học không được để trống!");
        }
        System.out.println("Nhập số học sinh trong lớp: ");
        int students = Integer.parseInt(scanner.nextLine());
        try {
            int number = students;
            if (number <= 0) {
                throw new IllegalArgumentException("Số lượng hs phải lớn hơn 0!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Số lượng hs phải nguyên!");
        }
        ClassSvDTO classSvDTO = new ClassSvDTO(id, name, students);
        ClassSv newClass = mapper.toClass(classSvDTO);
        service.addClass(newClass);
    }

    public void changeClass() {
        System.out.println("Nhập id lớp học muốn đổi tên: ");
        String id = scanner.nextLine();
        ClassSv lopHoc = service.getClassById(id);
        if (lopHoc != null) {
            System.out.println("Thông tin lớp học:");
            service.displayClass(lopHoc);
            System.out.println("Nhập tên lớp học mới: ");
            String name = scanner.nextLine();
            service.changeName(lopHoc, name);
        } else {
            System.out.println("Lớp học không tồn tại");
        }
    }

    public void deleteClass() {
        System.out.println("Nhập id lớp cần xóa: ");
        String id = scanner.nextLine();
        ClassSv lopHoc = service.getClassById(id);
        if (lopHoc != null) {
            System.out.println("Thông tin lớp học sẽ xóa: ");
            service.displayClass(lopHoc);
            service.deleteClass(lopHoc);
        } else {
            System.out.println("Lớp học không tồn tại");
        }
    }
}
