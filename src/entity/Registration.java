package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Registration implements SuperEntity {
    @Id
    private String regNo;
    private Date regDate;
    private double totalFee;
    @ManyToOne
    @JoinColumn(name = "Student_ID")
    private Student student;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Registration_Details",joinColumns = @JoinColumn(name = "Reg_ID"),inverseJoinColumns = @JoinColumn(name = "Course_ID"))
    private List<Course> courses = new ArrayList<>();

    public Registration() {
    }

    public Registration(String regNo, Date regDate, double totalFee, Student student) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.totalFee = totalFee;
        this.student = student;
    }

    public Registration(String regNo, Date regDate, double totalFee, Student student, ArrayList<Course> courses) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.totalFee = totalFee;
        this.student = student;
        this.courses = courses;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo='" + regNo + '\'' +
                ", regDate=" + regDate +
                ", totalFee=" + totalFee +
                ", student=" + student +
                ", courses=" + courses +
                '}';
    }
}
