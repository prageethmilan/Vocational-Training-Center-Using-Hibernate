package controller;

import bo.BOFactory;
import bo.custom.CoursesFormBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import view.tm.CourseTM;

import java.util.ArrayList;
import java.util.Optional;

public class CoursesFormController {
    public JFXTextField txtProgramId;
    public JFXTextField txtProgram;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public Spinner<String> spTime;
    public JFXButton btnSaveAndUpdate;
    public TableView<CourseTM> tblPrograms;
    public TableColumn colProgramId;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colFee;
    CoursesFormBO coursesFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    public void initialize() {
        btnSaveAndUpdate.setText("Save");
        ObservableList<String> duration = FXCollections.observableArrayList();
        duration.addAll("Years", "Months");
        SpinnerValueFactory<String> durationValues = new SpinnerValueFactory.ListSpinnerValueFactory<>(duration);
        spTime.setValueFactory(durationValues);

        try {
            loadCoursesToTable(coursesFormBO.getAllCourses());
        } catch (Exception e) {

        }
    }

    private void loadCoursesToTable(ArrayList<CourseDTO> allCourses){
        ObservableList<CourseTM> obList = FXCollections.observableArrayList();
        allCourses.forEach(e->{
            obList.add(new CourseTM(e.getProgramId(),e.getProgram(),e.getDuration(),e.getFee()));
        });
        tblPrograms.setItems(obList);
        iniColumns();
    }

    private void iniColumns() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    public void clearTextFields_OnAction(MouseEvent mouseEvent) {
        txtProgramId.clear();
        txtProgram.clear();
        txtDuration.clear();
        txtFee.clear();
        spTime.getValueFactory().setValue("Years");
    }

    public void saveAndUpdateCourseDetails_OnAction(MouseEvent mouseEvent) {
        if (!txtProgramId.getText().matches("^(CT)[0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Program ID", ButtonType.CLOSE).showAndWait();
            txtProgramId.requestFocus();
            return;
        } else if (!txtProgram.getText().matches("^[A-z &-]{1,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Program Name", ButtonType.CLOSE).showAndWait();
            txtProgramId.requestFocus();
            return;
        } else if (!txtDuration.getText().matches("^[0-9]{1,2}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Duration", ButtonType.CLOSE).showAndWait();
            txtProgramId.requestFocus();
            return;
        } else if (!txtFee.getText().matches("^[0-9]{1,}(.00)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Fee.\nFee must be in 100.00 format", ButtonType.CLOSE).showAndWait();
            txtProgramId.requestFocus();
            return;
        }

        CourseDTO courseDTO = new CourseDTO(txtProgramId.getText(), txtProgram.getText(), txtDuration.getText() + " " + spTime.getValue(), Double.parseDouble(txtFee.getText()));

        try {
            boolean added = coursesFormBO.saveCourse(courseDTO);
            if (added){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully",ButtonType.OK).show();
                txtProgramId.clear();
                txtProgram.clear();
                txtDuration.clear();
                txtFee.clear();
                spTime.getValueFactory().setValue("Years");
                loadCoursesToTable(coursesFormBO.getAllCourses());
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again",ButtonType.CLOSE).show();
            }
        } catch (Exception e) {

        }
    }

    public void deleteCourseOnAction(ActionEvent actionEvent) {
        CourseTM selectedItem = tblPrograms.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",no,yes);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == yes) {
                try {
                    boolean deleted = coursesFormBO.deleteCourse(selectedItem);
                    if (deleted){
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully",ButtonType.OK).close();
                        loadCoursesToTable(coursesFormBO.getAllCourses());
                    }
                } catch (Exception e) {

                }
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"PLease select a course",ButtonType.CLOSE).show();
        }
    }
}
