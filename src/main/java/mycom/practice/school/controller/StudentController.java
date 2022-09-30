package mycom.practice.school.controller;

import mycom.practice.school.constant.AppConstants;
import mycom.practice.school.entity.Student;
import mycom.practice.school.entity.StudentResponse;
import mycom.practice.school.payload.StudentDto;
import mycom.practice.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/testui")
    public String testConnection(){
        return "test ui connection successful";
    }

    @GetMapping(path = "/getstudent/{id}")
    public StudentDto getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
        /*
        if(!student.isPresent()){
            return new ResponseEntity<Student> (HttpStatus.NOT_FOUND);
            //throw new StudentNotFoundException(id);
            // throws new exception,
            // you will get a warning message in the log along with HTTP_STATUS code of NOT_FOUND: 404
        }
        return new ResponseEntity<Student> (student.get(), HttpStatus.OK);
        */
    }

    @DeleteMapping("/deletestudent/{id}")
    public void deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
        /*
        if(!student.isPresent()){
            return new ResponseEntity<Student> (HttpStatus.NOT_FOUND);
            //throw new StudentNotFoundException(id);
            // throws new exception,
            // you will get a warning message in the log along with HTTP_STATUS code of NOT_FOUND: 404
        }
        return new ResponseEntity<Student> (student.get(), HttpStatus.OK);
        */
    }

    @PostMapping(path= "/addstudent/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto)
    {
        /*
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(student.getId()).toUri();
                return ResponseEntity.created(location).build();
        */
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}")
    public Student saveOrUpdate(@RequestBody StudentDto studentDto, @PathVariable int id) {
        return studentService.saveOrUpdate(studentDto, id);
    }

    @GetMapping(path= "/addTenfakestudents/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTenfakestudents()
    {
        studentService.addTenFakeStudents();
    }

    @GetMapping(path = "/findallstudents")
    public StudentResponse findAllStudents(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return studentService.findAllStudents(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping(path = "/getallstudents", produces = "application/json")
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudents();
    }
}
