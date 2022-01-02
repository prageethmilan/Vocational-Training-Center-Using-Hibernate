package view.tm;

import java.util.Date;

public class AllRegistrationTM {
    private String regNo;
    private Date regDate;
    private double totalFee;
    private String studentId;

    public AllRegistrationTM() {
    }

    public AllRegistrationTM(String regNo, Date regDate, double totalFee, String studentId) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.totalFee = totalFee;
        this.studentId = studentId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
