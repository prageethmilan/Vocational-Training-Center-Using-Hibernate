package view.tm;

public class RegistrationByStudentIdTM {
    private String studentId;
    private String name;
    private String address;
    private String contact;
    private String age;
    private String regNo;
    private String programId;
    private String program;

    public RegistrationByStudentIdTM() {
    }

    public RegistrationByStudentIdTM(String studentId, String name, String address, String contact, String age, String regNo, String programId, String program) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.age = age;
        this.regNo = regNo;
        this.programId = programId;
        this.program = program;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
