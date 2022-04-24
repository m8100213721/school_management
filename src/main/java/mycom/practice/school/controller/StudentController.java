package mycom.practice.school.controller;

import mycom.practice.school.entity.Student;
import mycom.practice.school.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("/students")
public class StudentController {
    @Autowired
    private StudentDAO studentDao;

    @GetMapping(path="/", produces = "application/json")
    public List<Student> getStudents()
    {
        return studentDao.findAll();
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
