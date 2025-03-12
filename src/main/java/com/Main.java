package com;

import com.qlhs.controller.ClassSvController;
import com.qlhs.controller.StudentController;
import com.qlhs.mapper.ClassSvMapper;
import com.qlhs.mapper.StudentMapper;
import com.qlhs.repository.ClassSvRepo;
import com.qlhs.repository.StudentRepo;
import com.qlhs.service.ClassSvService;
import com.qlhs.service.StudentService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //
        ClassSvRepo classSvRepo = new ClassSvRepo();
        ClassSvMapper classSvMapper = new ClassSvMapper();
        ClassSvService classSvService = new ClassSvService(classSvRepo);
        ClassSvController classSvController = new ClassSvController(classSvService, classSvMapper, sc);

        StudentRepo studentRepo = new StudentRepo();
        StudentMapper studentMapper = new StudentMapper();
        StudentService studentService = new StudentService(studentRepo, classSvRepo);
        StudentController studentController = new StudentController(studentService, studentMapper, sc);


        while (true) {
            System.out.println("*-------Hệ thống quản lý học sinh/ sinh viên*---------");
            System.out.println("1.Nhập thông tin lớp học ");
            System.out.println("2.Nhập thông tin hoc sinh ");
            System.out.println("3.Sửa đổi thông tin lớp học ");
            System.out.println("4.Sửa đổi thông tin học sinh ");
            System.out.println("5.Xóa học sinh");
            System.out.println("6.Xóa lớp");
            System.out.println("7.In all dữ liệu ra file JSON");
            System.out.println("8.Ghi toàn bộ dữ liệu xuống file");
            System.out.println("9.Đọc dữ liệu từ file JSON ");
            System.out.println("*----------***----------*\n");
            System.out.println("Chon thao tác của bạn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                while (true) {
                    classSvController.createClass();
                    System.out.println("Bạn có muốn nhập thêm lớp học không? Y/N");
                    String decide = sc.nextLine();
                    if (decide.equals("N") || decide.equals("n")) break;
                }
            }
            if (choice == 2) {
                while (true) {
                    studentController.createStudent();
                    System.out.println("Bạn có muốn nhập thêm học sinh không? Y/N");
                    String decide = sc.nextLine();
                    if (decide.equals("N") || decide.equals("n")) break;
                }
            }
            if (choice == 3) {
                while (true) {
                    classSvController.changeClass();
                    System.out.println("Bạn có muốn tiếp tục sửa tên lớp khác không? Y/N");
                    String decide = sc.nextLine();
                    if (decide.equals("N") || decide.equals("n")) break;
                }
            }
            if (choice == 4) {
                while (true) {
                    studentController.updateStudent();
                    System.out.println("Bạn có muốn tiếp tục sửa thông tin hs khác không? Y/N");
                    String decide = sc.nextLine();
                    if (decide.equals("N") || decide.equals("n")) break;
                }
            }
            if(choice == 5){
                while (true) {
                    studentController.deleteStudent();
                    System.out.println("Bạn có muốn tiếp tục xóa hs khác không? Y/N");
                    String decide = sc.nextLine();
                    if (decide.equals("N") || decide.equals("n")) break;
                }
            }
            if(choice == 6){
                while (true) {
                    classSvController.deleteClass();
                    System.out.println("Bạn có tiếp tục muốn xóa class khác không? Y/N");
                    String decide = sc.nextLine();
                    if (decide.equals("N") || decide.equals("n")) break;
                }
            }
            if(choice == 7){
                // transform dữ liệu từ object qua Json
                String jsonOutput = classSvRepo.exportToJSON();
                System.out.println("Dữ lieu Json :\n"+jsonOutput);
                // Ghi dữ liệu Json ra file text
                classSvRepo.saveJsonFile("data.json");
            }
            if(choice == 8)
            {
                // Ghi dữ liệu ra file binary
                classSvRepo.saveBinaryFile("data.bin");
                // Đọc dữ liệu từ file binary vào classData
                //classSvRepo.loadBinaryFile("data.bin");
            }
            if(choice == 9){
                classSvRepo.loadJsonFile("data.json");
            }
            System.out.println("Bạn có muốn tiếp tục thao tác: Y/N?");
            String decide = sc.next();
            if (decide.equals("N") || decide.equals("n")) break;
        }
    }
}