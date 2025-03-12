package com.qlhs.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;  // ID phiên bản
    private String id;
    private String name;
    private LocalDate birthDate;
    private int age;
    private String gender;
    private Double height;
    private Double weight;
    private String classId;
    public Student() {}

    public Student( LocalDate birthDate, String classId, String gender, Double height, String id, String name, Double weight) {
        this.birthDate = birthDate;
        this.classId = classId;
        this.gender = gender;
        this.height = height;
        this.id = id;
        this.name = name;
        this.weight = weight;
    }


    public int getAge() {
        if (birthDate == null) {
            return 0;
        }
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
