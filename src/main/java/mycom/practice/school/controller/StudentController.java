package mycom.practice.school.controller;

import mycom.practice.school.entity.Student;
import mycom.practice.school.exception.StudentNotFoundException;
import mycom.practice.school.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController("/students")
public class StudentController {
    @Autowired
    private StudentDAO studentDao;

    @GetMapping(path="/", produces = "application/json")
    public List<Student> getStudents()
    {
        return studentDao.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
        Optional<Student> student = studentDao.findById(id);
        if(!student.isPresent()){
            return new ResponseEntity<Student> (HttpStatus.NOT_FOUND);
            //throw new StudentNotFoundException(id);
            // throws new exception,
            // you will get a warning message in the log along with HTTP_STATUS code of NOT_FOUND: 404
        }
        return new ResponseEntity<Student> (student.get(), HttpStatus.OK);
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addStudent(@RequestBody Student student)
    {
        Integer id = studentDao.findAll().size() + 1;
        student.setId(id);
        studentDao.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
