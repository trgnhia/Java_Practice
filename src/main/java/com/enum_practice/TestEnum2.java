package com.enum_practice;

enum Day{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
public class TestEnum2 {
    Day dayInWeek;
    public TestEnum2(Day dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public void dayIsLike(){
        switch(dayInWeek){
            case MONDAY:
                System.out.println("Monday is bad");
                break;
            case TUESDAY:
                System.out.println("Tuesday is much better");
                break;
            case WEDNESDAY:
                System.out.println("Wednesday is a sunny day");
                break;
            case SUNDAY:
                System.out.println("Weekend is the best");
                break;
            default:
                System.out.println("Mid week days are normal");
                break;
        }
    }

    public static void main(String[] args) {
        String str = "MONDAY";
        TestEnum2 test = new TestEnum2(Day.valueOf(str));
        test.dayIsLike();
    }
}
