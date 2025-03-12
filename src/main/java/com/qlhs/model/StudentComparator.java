package com.qlhs.model;

import java.io.Serializable;
import java.util.Comparator;

public class StudentComparator implements Comparator<Student> , Serializable {
    private static final long serialVersionUID = 1L;  // ID phiên bản
    @Override
    public int compare(Student o1, Student o2) {
        int res = Double.compare(o1.getHeight(), o2.getHeight());
        if(res == 0){
            return o1.getId().compareTo(o2.getId());
        }
        return res;
    }
}
