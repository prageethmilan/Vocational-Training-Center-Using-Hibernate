package dto;

public class StudentDTO {
    private String studentId;
    private String name;
    private String address;
    private String contact;
    private String dateOfBirth;
    private int age;

    public StudentDTO() {
    }

    public StudentDTO(String studentId, String name, String address, String contact) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public StudentDTO(String studentId, String name, String address, String contact, String dateOfBirth, int age) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
