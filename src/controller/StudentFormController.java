package controller;

import bo.BOFactory;
import bo.custom.StudentFormBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import view.tm.StudentTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class StudentFormController {
    public Label lblStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker txtDateOfBirth;
    public JFXTextField txtAge;
    public TableView<StudentTM> tblStudents;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDateOfBirth;
    public TableColumn colAge;
    public JFXTextField txtSearchId;
    public JFXButton btnSaveAndUpdate;
    StudentFormBO studentFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        btnSaveAndUpdate.setText("Save");
        try {
            loadStudentsToTable(studentFormBO.getAllStudents());
            generateStudentIDs();

        } catch (Exception e) {

        }
        txtSearchId.textProperty().addListener((observable, oldValue, newValue) -> {
            searchStudent(newValue);
        });
    }

    private void searchStudent(String newValue) {
        try {
            ArrayList<StudentDTO> students = studentFormBO.searchStudent(newValue);
            ObservableList<StudentTM> obList = FXCollections.observableArrayList();
            students.forEach(e->{
                obList.add(new StudentTM(e.getStudentId(),e.getName(),e.getAddress(),e.getContact(),e.getDateOfBirth(),e.getAge()));
            });
            tblStudents.setItems(obList);
        } catch (Exception e) {

        }
    }

    private void loadStudentsToTable(ArrayList<StudentDTO> allStudents) {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        allStudents.forEach(e->{
            obList.add(new StudentTM(e.getStudentId(),e.getName(),e.getAddress(),e.getContact(),e.getDateOfBirth(),e.getAge()));
        });
        tblStudents.setItems(obList);
        initStudentTable();
    }

    private void initStudentTable() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    }

    private void generateStudentIDs() throws Exception {
        String studentId = studentFormBO.generateStudentIds();
        lblStudentId.setText(studentId);
    }


    public void saveAndUpdateStudent_OnAction(MouseEvent mouseEvent) {
        if(!txtStudentName.getText().matches("[A-Za-z. ]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid name", ButtonType.CLOSE).showAndWait();
            txtStudentName.requestFocus();
            return;
        }else if(!txtAddress.getText().matches("[A-Za-z,0-9./]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid address",ButtonType.CLOSE).showAndWait();
            txtAddress.requestFocus();
            return;
        }else if(!txtContact.getText().matches("[0-9]{10}")){
            new Alert(Alert.AlertType.ERROR,"Invalid Contact",ButtonType.CLOSE).showAndWait();
            txtContact.requestFocus();
            return;
        }else if(!txtAge.getText().matches("[0-9]{1,2}")){
            new Alert(Alert.AlertType.ERROR,"Invalid age",ButtonType.CLOSE).showAndWait();
            txtAge.requestFocus();
            return;
        }else if (txtDateOfBirth.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Date of birth is empty.Please fill it.",ButtonType.CLOSE).showAndWait();
            txtDateOfBirth.requestFocus();
            return;
        }

        StudentDTO studentDTO = new StudentDTO(lblStudentId.getText(), txtStudentName.getText(), txtAddress.getText(), txtContact.getText(), String.valueOf(txtDateOfBirth.getValue()), Integer.parseInt(txtAge.getText()));
        if (btnSaveAndUpdate.getText().equalsIgnoreCase("Save")){
            try {
                boolean added = studentFormBO.saveStudent(studentDTO);
                if (added){
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully",ButtonType.OK).show();
                    generateStudentIDs();
                    txtStudentName.clear();
                    txtAddress.clear();
                    txtContact.clear();
                    txtAge.clear();
                    txtDateOfBirth.setValue(null);
                    loadStudentsToTable(studentFormBO.getAllStudents());
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try again",ButtonType.CLOSE).show();
                }
            } catch (Exception e) {

            }
        }else if (btnSaveAndUpdate.getText().equalsIgnoreCase("Update")){
            try {
                boolean updated = studentFormBO.updateStudent(studentDTO);
                if (updated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully",ButtonType.OK).show();
                    generateStudentIDs();
                    txtStudentName.clear();
                    txtAddress.clear();
                    txtContact.clear();
                    txtAge.clear();
                    txtSearchId.clear();
                    txtDateOfBirth.setValue(null);
                    loadStudentsToTable(studentFormBO.getAllStudents());
                    btnSaveAndUpdate.setText("Save");
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try again",ButtonType.CLOSE).show();
                }
            } catch (Exception e) {

            }
        }
    }

    public void clearTextFields_OnAction(MouseEvent mouseEvent) {
        try {
            generateStudentIDs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtStudentName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDateOfBirth.setValue(null);
        txtAge.clear();
        btnSaveAndUpdate.setText("Save");
    }

    public void updateStudent_OnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblStudents.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            lblStudentId.setText(selectedItem.getId());
            txtStudentName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtContact.setText(selectedItem.getContact());
            txtDateOfBirth.setValue(LocalDate.parse(selectedItem.getDateOfBirth()));
            txtAge.setText(String.valueOf(selectedItem.getAge()));
            btnSaveAndUpdate.setText("Update");
        }else{
            new Alert(Alert.AlertType.WARNING,"Please select a student",ButtonType.CLOSE).show();

        }
    }

    public void deleteStudent_OnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblStudents.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",no,yes);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == yes) {
                try {
                    boolean deleted = studentFormBO.deleteStudent(selectedItem);
                    if (deleted){
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully",ButtonType.OK).show();
                        generateStudentIDs();
                        loadStudentsToTable(studentFormBO.getAllStudents());
                    }
                } catch (Exception e) {

                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please select a student",ButtonType.CLOSE).show();
        }
    }
}
