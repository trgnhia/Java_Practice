package com.enum_practice;

// khai báo enum ở ngoài class
enum Color {
    RED, BLACK, WHITE,GREEN;
}

public class TestEnum1 {
    enum Colors{
        RED, BLUE, ORANGE, YELLOW;
    }
    public static void main(String[] args) {
        Color color1 = Color.RED;
        Color color2 = Color.BLACK;
        System.out.println(color1 + " and "  + color2);
        Colors c1 = Colors.ORANGE;
        System.out.println(c1);
    }
}
