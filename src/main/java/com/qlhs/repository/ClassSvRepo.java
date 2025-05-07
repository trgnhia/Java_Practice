package com.qlhs.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.qlhs.model.ClassSv;
import com.qlhs.util.ClassSvComparator;
import com.qlhs.model.Student;
import com.qlhs.util.StudentComparator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class ClassSvRepo {
    private List<ClassSv> lsClass = new ArrayList<ClassSv>();
    private TreeMap<ClassSv, TreeSet<Student>> classData = new TreeMap<>(new ClassSvComparator());
    public ClassSv findById(String id) {
        for (ClassSv c : lsClass) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void updateNumberOfStudent(String id, Boolean check) {
        ClassSv c = findById(id);
        // tạo 1 tập hợp students sắp xếp theo chiều cao tăng dần
        TreeSet<Student> setStudents = new TreeSet<>(new StudentComparator());
        setStudents.addAll(classData.get(c));
        classData.remove(c);
        if (check) {
            c.setNumberOfStudents(c.getNumberOfStudents() + 1);
        } else {
            c.setNumberOfStudents(c.getNumberOfStudents() - 1);
        }
        //Thêm lại vào TreeMap với key đã cập nhật, lúc này key và value đều có thuộc tính được sắp xếp
        classData.put(c, setStudents);
    }

    public void updateName(ClassSv lopHoc, String name) {
        lopHoc.setName(name);
    }

    public void addClass(ClassSv lopHoc) {
        classData.put(lopHoc, new TreeSet<>(new StudentComparator())); //mỗi lớp có danh sách học sinh rỗng
        lsClass.add(lopHoc);
    }

    public void removeClass(ClassSv lopHoc) {
        classData.remove(lopHoc);//Xóa cả danh sách học sinh của lớp đó
        lsClass.removeIf(c -> c.getId().equals(lopHoc.getId()));
    }

    public void addStudentToClass(ClassSv lopHoc, Student student) {
        classData.get(lopHoc).add(student);  //Thêm học sinh vào danh sách
        updateNumberOfStudent(lopHoc.getId(), true);
    }

    public void removeStudentFromClass(ClassSv lopHoc, Student student) {
        classData.get(lopHoc).remove(student);
        updateNumberOfStudent(lopHoc.getId(), false);
    }

    public String exportToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // format json đẹp hơn
        try {
            return objectMapper.writeValueAsString(classData); // Chuyển đổi classData thành JSON String
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // Trả về chuỗi JSON rỗng nếu có lỗi
        }
    }

    public void saveJsonFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(fileName), classData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // xử lý dữ liệu => binary
    public void saveBinaryFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(classData);
            System.out.println("Dữ liệu đã được lưu vào file binary: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // đọc dữ liệu từ file binary
    public void loadBinaryFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            classData = (TreeMap<ClassSv, TreeSet<Student>>) ois.readObject();
            System.out.println("Dữ liệu đã được tải lại từ file binary");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // đọc dữ liệu từ file JSON
    public void loadJsonFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            classData = objectMapper.readValue(new File(fileName), new TypeReference<TreeMap<ClassSv, TreeSet<Student>>>() {
            });
            System.out.println("Dữ liệu đã được tải lại từ file JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
