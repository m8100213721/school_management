package mycom.practice.school.service.impl;

import mycom.practice.school.entity.Student;
import mycom.practice.school.entity.StudentResponse;
import mycom.practice.school.exception.StudentNotFoundException;
import mycom.practice.school.payload.StudentDto;
import mycom.practice.school.repository.StudentDAO;
import mycom.practice.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDao;

    @Override
    public StudentResponse findAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Student> students = studentDao.findAll(pageable);
        List<Student> listOfStudents = students.getContent();
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(listOfStudents);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalElements(students.getTotalElements());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setLast(students.isLast());
        return studentResponse;
    }
    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> allStudentDtos = new ArrayList<>();
        List<Student> studentList = studentDao.findAll();
        studentList.stream().forEach(student -> allStudentDtos.add(mapToDto(student)));
        return allStudentDtos;
    }
    @Override
    public StudentDto getStudentById(int id) {
        Optional<Student> student = studentDao.findById(id);
        if(!student.isPresent()){
            throw new StudentNotFoundException(id);
            // throws new exception,
            // you will get a warning message in the log along with HTTP_STATUS code of NOT_FOUND: 404
        }
         return mapToDto(student.get());
    }

    @Override
    public void deleteStudentById(int id) {
        Optional<Student> student = studentDao.findById(id);
        if(!student.isPresent()) {
            //return new ResponseEntity<Student> (HttpStatus.NOT_FOUND);
            throw new StudentNotFoundException(id);
        }
        studentDao.deleteById(id);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto){
        Student student = mapToEntity(studentDto);
        Integer id = studentDao.findAll().size() + 1;
        student.setId(id);
        return mapToDto(studentDao.save(student));
    }

    @Override
    public void addTenFakeStudents(){
        Integer id = studentDao.findAll().size() + 1;
        Student student = null;
        for(int i = 0; i<10 ; i++) {
            student = new Student();
            student.setId(id);
            student.setClassroom(5+i);
            student.setBirthdate("01-01-2014");
            student.setName("test student "+i);
            student.setPlace("test student place "+i);
            studentDao.save(student);
            id++;
        }
    }

    @Override
    public Student saveOrUpdate(StudentDto studentDto, int id) {
        return studentDao.findById(id)
                .map(student -> {
                    student.setName(studentDto.getName());
                    student.setBirthdate(studentDto.getBirthdate());
                    student.setPlace(studentDto.getPlace());
                    student.setClassroom(studentDto.getClassroom());
                    return studentDao.save(student);
                })
                .orElseGet(() -> {
                    Student student = mapToEntity(studentDto);
                    student.setId(id);
                    return studentDao.save(student);
                });
    }

    private StudentDto mapToDto(Student student){
        return new StudentDto(student.getId(), student.getName(), student.getClassroom(), student.getBirthdate(), student.getPlace());
    }

    private Student mapToEntity(StudentDto studentDto){
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setBirthdate(studentDto.getBirthdate());
        student.setPlace(studentDto.getPlace());
        student.setClassroom(studentDto.getClassroom());
        return student;
    }
}
