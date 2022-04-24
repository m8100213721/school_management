package mycom.practice.school.service;

import mycom.practice.school.entity.StudentResponse;

public interface StudentService {
    StudentResponse getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);
}
