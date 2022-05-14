package mycom.practice.school.service;

import mycom.practice.school.entity.Student;
import mycom.practice.school.entity.StudentResponse;
import mycom.practice.school.payload.StudentDto;

import java.util.Optional;

public interface StudentService {
    StudentResponse getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);
    Optional<Student> getStudentById(int id);

    void deleteStudentById(int id);
    StudentDto createStudent(StudentDto studentDto);
    void addTenFakeStudents();

    Student saveOrUpdate(StudentDto studentDto, int id);
}
