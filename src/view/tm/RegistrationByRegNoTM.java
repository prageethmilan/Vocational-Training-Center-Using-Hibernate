package view.tm;

import java.util.Date;

public class RegistrationByRegNoTM {
    private String regNo;
    private Date regDate;
    private String studentId;
    private String programId;
    private String program;

    public RegistrationByRegNoTM() {
    }

    public RegistrationByRegNoTM(String regNo, Date regDate, String studentId, String programId, String program) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.studentId = studentId;
        this.programId = programId;
        this.program = program;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
