package com.qlhs.mapper;

import com.qlhs.dto.ClassSvDTO;
import com.qlhs.model.ClassSv;

public class ClassSvMapper {
    public ClassSv toClass(ClassSvDTO classSvDTO) {
        ClassSv classSv = new ClassSv();
        classSv.setId(classSvDTO.getId());
        classSv.setName(classSvDTO.getName());
        classSv.setNumberOfStudents(classSvDTO.getNumberOfStudents());
        return classSv;
    }
}
