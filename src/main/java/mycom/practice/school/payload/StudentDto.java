package mycom.practice.school.payload;

public class StudentDto {
    private int id;
    private String name;
    private int classroom;
    private String birthdate;
    private String place;

    public StudentDto(){

    }

    public StudentDto(int id, String name, int classroom, String birthdate, String place) {
        this.id = id;
        this.name = name;
        this.classroom = classroom;
        this.birthdate = birthdate;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
