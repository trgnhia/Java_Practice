package com.qlhs.dto;

import java.time.LocalDate;

public class StudentDTO {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String height;
    private String weight;
    private String classId;

    public StudentDTO() {}

    public StudentDTO(LocalDate birthDate, String classId, String gender, String height, String id, String name, String weight) {
        this.birthDate = birthDate;
        this.classId = classId;
        this.gender = gender;
        this.height = height;
        this.id = id;
        this.name = name;
        this.weight = weight;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    public int getAge(){
        if(birthDate == null) return 0;
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}
