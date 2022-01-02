package dto;

import java.util.Date;
import java.util.List;

public class RegistrationDTO {
    private String regNo;
    private Date regDate;
    private double totalFee;
    private StudentDTO studentDTO;
    private List<CourseDTO> courseDTOS;

    public RegistrationDTO() {

    }

    public RegistrationDTO(String regNo, Date regDate, double totalFee, StudentDTO studentDTO) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.totalFee = totalFee;
        this.studentDTO = studentDTO;
    }

    public RegistrationDTO(String regNo, Date regDate, double totalFee, StudentDTO studentDTO, List<CourseDTO> courseDTOS) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.totalFee = totalFee;
        this.studentDTO = studentDTO;
        this.courseDTOS = courseDTOS;
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

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public List<CourseDTO> getCourseDTOS() {
        return courseDTOS;
    }

    public void setCourseDTOS(List<CourseDTO> courseDTOS) {
        this.courseDTOS = courseDTOS;
    }
}
