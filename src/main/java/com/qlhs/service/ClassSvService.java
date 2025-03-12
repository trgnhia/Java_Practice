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

    private void validateClass(ClassSv c) {
       try{
           int number = c.getNumberOfStudents();
           if (number <= 0)
               throw new IllegalArgumentException("Số lượng hs phải lớn hơn 0!");
       } catch(NumberFormatException e) {
           throw new IllegalArgumentException("Số lượng hs phải nguyên!");
       }

        if (c.getName() == null || c.getName().isEmpty())
            throw new IllegalArgumentException("Tên lớp học không được để trống");
    }

    public void addClass(ClassSv lopHoc) {
        validateClass(lopHoc);
        ClassSv existClass = classRepo.findById(lopHoc.getId());
        if (existClass != null)
            throw new IllegalArgumentException("Id lớp học đã tồn tại! ");
        classRepo.addClass(lopHoc);
        System.out.println("Thêm lớp học thành công!");
    }

    public void changeName(ClassSv lopHoc, String name) {
        validateClass(lopHoc);
        classRepo.updateName(lopHoc, name);
        System.out.println("Thông tin lớp học sau khi sửa tên: ");
        displayClass(lopHoc);
    }
    public void deleteClass(ClassSv lopHoc){
        classRepo.removeClass(lopHoc);
        System.out.println("Xóa lớp học thành công!!");
    }
}
