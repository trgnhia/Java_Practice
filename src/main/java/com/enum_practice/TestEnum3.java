package com.enum_practice;

import java.util.Scanner;

enum HocLuc{
    TRUNG_BINH("TB"),
    KHA ("K"),
    GIOI("G"),
    XUAT_SAC("XS");
    private final String detail;
    HocLuc(String detail){
        this.detail = detail;
    }
    public String getDetail(){
        return detail;
    }
}

public class TestEnum3 {
    HocLuc hl;
    public void checkHocLuc(double mark){
        if(mark <= 5) hl = HocLuc.TRUNG_BINH;
        else if (mark <= 7) hl = HocLuc.KHA;
        else if (mark <= 9) hl = HocLuc.GIOI;
        else if (mark <= 10) hl = HocLuc.XUAT_SAC;
        System.out.println("Ban dang o hoc luc: " + hl + " - " + hl.getDetail());
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap diem cua ban: ");
        float mark = sc.nextFloat();
        TestEnum3 t3 = new TestEnum3();
        t3.checkHocLuc(mark);
    }
}
