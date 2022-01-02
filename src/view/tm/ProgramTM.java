package view.tm;

import javafx.scene.control.Button;

public class ProgramTM {
    private String programId;
    private String program;
    private String duration;
    private double fee;
    private Button button;

    public ProgramTM() {
    }

    public ProgramTM(String programId, String program, String duration, double fee, Button button) {
        this.programId = programId;
        this.program = program;
        this.duration = duration;
        this.fee = fee;
        this.button = button;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
