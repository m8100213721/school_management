package mycom.practice.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/student")
public class StudentController {
    @GetMapping(name = "/findstudent/{id}")
    private String getStudent(int id){
        return "student"+id;
    }
}
