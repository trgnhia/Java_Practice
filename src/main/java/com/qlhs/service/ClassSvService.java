package com.qlhs.service;

import com.qlhs.model.ClassSv;
import com.qlhs.repository.ClassSvRepo;

public class ClassSvService {
    private ClassSvRepo classRepo;

    public ClassSvService(ClassSvRepo classRepo) {
        this.classRepo = classRepo;
    }

    public void displayClass(ClassSv lopHoc) {
        System.out.println("Mã lớp: " + lopHoc.getId());
        System.out.println("Tên lớp: " + lopHoc.getName());
        System.out.println("Số học sinh: " + lopHoc.getNumberOfStudents());
    }
    public ClassSv getClassById(String id) {
        return classRepo.findById(id);
    }

    public void addClass(ClassSv lopHoc) {
        ClassSv existClass = classRepo.findById(lopHoc.getId());
        if (existClass != null) {
            throw new IllegalArgumentException("Id lớp học đã tồn tại! ");
        }
        classRepo.addClass(lopHoc);
        System.out.println("Thêm lớp học thành công!");
    }

    public void changeName(ClassSv lopHoc, String name) {
        classRepo.updateName(lopHoc, name);
        System.out.println("Thông tin lớp học sau khi sửa tên: ");
        displayClass(lopHoc);
    }

    public void deleteClass(ClassSv lopHoc) {
        classRepo.removeClass(lopHoc);
        System.out.println("Xóa lớp học thành công!!");
    }
}
