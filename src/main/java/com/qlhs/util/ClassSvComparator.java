package com.qlhs.util;

import com.qlhs.model.ClassSv;

import java.io.Serializable;
import java.util.Comparator;

public class ClassSvComparator implements Comparator<ClassSv>, Serializable {
    private static final long serialVersionUID = 1L;  // ID phiên bản

    @Override
    public int compare(ClassSv o1, ClassSv o2) {
        int res = Integer.compare(o1.getNumberOfStudents(), o2.getNumberOfStudents());
        if (res == 0) {
            return o1.getId().compareTo(o2.getId());
        }
        return res;
    }
}
