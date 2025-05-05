package com.enum_practice;

import java.util.Arrays;

// khai báo enum ở ngoài class
enum Color {
    RED(false),
    BLACK(true),
    WHITE(true),
    GREEN(false);
    private final Boolean isMix;
    Color(Boolean isMix) {
        this.isMix = isMix;
    }
    public Boolean getMix() {
        return isMix;
    }
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
        switch (c1) {
            case RED:
                System.out.println("Color is Red");
                break;
            case BLUE:
                System.out.println("Color is blue");
                break;
            case ORANGE:
                System.out.println("Color is orange");
                break;
            case YELLOW:
                System.out.println("Color is yellow");
                break;
            default:
                System.out.println("Unknown color");
        }
        Color[] colors = Color.values();
        System.out.println(Arrays.toString(colors));
        System.out.println(color1.getMix());
    }
}
