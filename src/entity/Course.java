package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements SuperEntity {
    @Id
    private String programId;
    private String program;
    private String duration;
    private double fee;
    @ManyToMany(mappedBy = "courses")
    private List<Registration> registrations = new ArrayList<>();

    public Course() {
    }

    public Course(String programId, String program, String duration, double fee) {
        this.programId = programId;
        this.program = program;
        this.duration = duration;
        this.fee = fee;
    }

    public Course(String programId, String program, String duration, double fee, List<Registration> registrations) {
        this.programId = programId;
        this.program = program;
        this.duration = duration;
        this.fee = fee;
        this.registrations = registrations;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Course{" +
                "programId='" + programId + '\'' +
                ", program='" + program + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
