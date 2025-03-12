package com.qlhs.model;

public class ClassSv implements java.io.Serializable {
    private static final long serialVersionUID = 1L;  // ID phiên bản
    private String id;
    private String name;
    private Integer numberOfStudents;

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

    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public ClassSv(String id, String name, int numberOfStudents) {
        this.id = id;
        this.name = name;
        this.numberOfStudents = numberOfStudents;
    }
    public ClassSv() {

    }

    @Override
    public String toString() {
        return "ClassSv{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                '}';
    }
}
