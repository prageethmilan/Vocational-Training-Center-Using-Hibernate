package view.tm;

public class CourseTM {
    private String programId;
    private String program;
    private String duration;
    private double fee;

    public CourseTM() {
    }

    public CourseTM(String programId, String program, String duration, double fee) {
        this.programId = programId;
        this.program = program;
        this.duration = duration;
        this.fee = fee;
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
}
