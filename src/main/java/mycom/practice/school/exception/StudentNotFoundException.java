package mycom.practice.school.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(int id){
        super("Student id " +id+ " NOT FOUND: ");
    }
}
