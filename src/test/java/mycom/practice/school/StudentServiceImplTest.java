package mycom.practice.school;

import mycom.practice.school.payload.StudentDto;
import mycom.practice.school.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {
    @Test
    public void getStudentById(int id) {
        StudentService doService = mock(StudentService.class);
        StudentDto studentDto = new StudentDto(1, "student1", 11, "01-01-2000", "patna");
        when(doService.getStudentById(1)).thenReturn(studentDto);
    }
}
