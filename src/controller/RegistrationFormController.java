package controller;

import bo.BOFactory;
import bo.custom.RegistrationFormBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import view.tm.ProgramTM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistrationFormController {
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXComboBox<String> cmbProgramId;
    public JFXTextField txtProgram;
    public JFXTextField txtDuration;
    public TableView<ProgramTM> tblRegistration;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;
    public Label lblRegNo;
    public Label lblTotalFee;
    public JFXTextField txtFee;
    public TableColumn colDelete;
    public JFXButton btnAdd;
    ObservableList<ProgramTM> programList = FXCollections.observableArrayList();
    RegistrationFormBO registrationFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);

    public void initialize() {
        lblTotalFee.setText("0.0");
        setProgramsToTable();
        btnAdd.setDisable(true);
        try {
            generateRegistrationNo();
            loadStudentIds();
            loadProgramIds();
        } catch (Exception e) {

        }

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!cmbProgramId.getSelectionModel().isEmpty()) {
                    btnAdd.setDisable(false);
                }
                try {
                    setStudentDetails(newValue);
                } catch (Exception e) {
                }
            } else {
                txtName.clear();
                txtAddress.clear();
                txtContact.clear();
            }
        });

        cmbProgramId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!cmbStudentId.getSelectionModel().isEmpty()) {
                    btnAdd.setDisable(false);
                }
                try {
                    setProgramDetails(newValue);
                } catch (Exception e) {
                }
            } else {
                txtProgram.clear();
                txtDuration.clear();
                txtFee.clear();
            }
        });

    }

    private void setProgramsToTable() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    private void setProgramDetails(String programId) throws Exception {
        CourseDTO dto = registrationFormBO.getCourse(programId);
        if (dto != null) {
            txtProgram.setText(dto.getProgram());
            txtDuration.setText(dto.getDuration());
            txtFee.setText(String.valueOf(dto.getFee()));
        }
    }

    private void setStudentDetails(String id) throws Exception {
        StudentDTO dto = registrationFormBO.getStudent(id);
        if (dto != null) {
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtContact.setText(dto.getContact());
        }
    }

    private void loadProgramIds() throws Exception {
        ArrayList<CourseDTO> courseList = registrationFormBO.getAllCourses();
        for (CourseDTO dto : courseList) {
            cmbProgramId.getItems().add(dto.getProgramId());
        }
    }

    private void loadStudentIds() throws Exception {
        ArrayList<StudentDTO> studentList = registrationFormBO.getAllStudents();
        for (StudentDTO dto : studentList) {
            cmbStudentId.getItems().add(dto.getStudentId());
        }
    }

    private void generateRegistrationNo() throws Exception {
        lblRegNo.setText(registrationFormBO.generateRegNo());
    }

    public void addCourseDetailsToTable(MouseEvent mouseEvent) {
        String programId = cmbProgramId.getValue();
        String program = txtProgram.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());
        Button button = new Button("Delete");

        ProgramTM programTM = new ProgramTM(programId, program, duration, fee, button);
        int rowNumber = isExists(programTM);
        if (rowNumber == -1) {
            programList.add(programTM);
        }
        btnOnAction(button);
        tblRegistration.setItems(programList);
        calculateTotalFee();
        cmbProgramId.getSelectionModel().clearSelection();
        btnAdd.setDisable(true);
    }

    private void calculateTotalFee() {
        double ttlFee = 0;
        for (ProgramTM tm : programList) {
            ttlFee += tm.getFee();
        }
        lblTotalFee.setText(String.valueOf(ttlFee));
    }

    private void btnOnAction(Button button) {
        button.setOnAction(e -> {
            try {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ok, cancel);
                alert.setTitle("Confirmation Alert");
                Optional<ButtonType> type = alert.showAndWait();
                if (type.orElse(cancel) == ok) {
                    int index = tblRegistration.getSelectionModel().getFocusedIndex();
                    programList.remove(index);
                    tblRegistration.refresh();
                } else {

                }
            } catch (Exception ex) {
            }
        });
    }

    private int isExists(ProgramTM programTM) {
        for (int i = 0; i < programList.size(); i++) {
            if (programTM.getProgramId().equals(programList.get(i).getProgramId())) {
                return i;
            }
        }
        return -1;
    }

    public void clearFields_OnAction(MouseEvent mouseEvent) {
        cmbStudentId.getSelectionModel().clearSelection();
        cmbProgramId.getSelectionModel().clearSelection();
        btnAdd.setDisable(true);
        lblTotalFee.setText("0.0");
        programList.clear();
        tblRegistration.refresh();
    }

    public void saveRegistration_OnAction(MouseEvent mouseEvent) {
        String regNo = lblRegNo.getText();
        Date date = Date.valueOf(LocalDate.now());
        double totalFee = Double.parseDouble(lblTotalFee.getText());
        StudentDTO studentDTO = new StudentDTO(cmbStudentId.getValue(),txtName.getText(),txtAddress.getText(),txtContact.getText());
        List<CourseDTO> courses = new ArrayList<>();
        for (ProgramTM tm : programList) {
            courses.add(new CourseDTO(tm.getProgramId(),tm.getProgram(),tm.getDuration(),tm.getFee()));
        }
        if (!cmbStudentId.getSelectionModel().isEmpty()) {
            if (programList.size() != 0) {
                RegistrationDTO registrationDTO = new RegistrationDTO(regNo,date,totalFee,studentDTO,courses);
                try {
                    boolean saved = registrationFormBO.saveRegistration(registrationDTO);
                    if (saved){
                        new Alert(Alert.AlertType.CONFIRMATION,"Registration Successful",ButtonType.OK).show();
                        generateRegistrationNo();
                        btnAdd.setDisable(true);
                        programList.clear();
                        tblRegistration.refresh();
                        cmbStudentId.getSelectionModel().clearSelection();
                        cmbProgramId.getSelectionModel().clearSelection();
                        lblTotalFee.setText("0.0");
                    }
                } catch (Exception e) {

                }
            }else{
                new Alert(Alert.AlertType.WARNING,"Please add program details",ButtonType.CLOSE).showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a student", ButtonType.CLOSE).showAndWait();
        }
    }

    public void CancelRegistration_OnAction(MouseEvent mouseEvent) {

    }
}
