package controller;

import bo.BOFactory;
import bo.custom.RegistrationListFormBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import view.tm.AllRegistrationTM;
import view.tm.RegistrationByProgramIdTM;
import view.tm.RegistrationByRegNoTM;
import view.tm.RegistrationByStudentIdTM;

import java.util.ArrayList;
import java.util.Date;

public class RegistrationListFormController {
    public JFXDatePicker fromDate;
    public JFXDatePicker toDate;
    public TableView<AllRegistrationTM> tblAllRegistration;
    public TableColumn colRegNoOfAllRegistration;
    public TableColumn colRegDateOfAllRegistration;
    public TableColumn colTotalFeeOfAllRegistration;
    public TableColumn colStudentIdOfAllRegistration;
    public TableView<RegistrationByStudentIdTM> tblStudentWiseRegistration;
    public TableColumn colStudentIdOnStudentWise;
    public TableColumn colStudentNameOnStudentWise;
    public TableColumn colAddressOnStudentWise;
    public TableColumn colContactOnStudentWise;
    public TableColumn colAgeOnStudentWise;
    public TableColumn colRegNoOnStudentWise;
    public TableColumn colProgramIdOnStudentWise;
    public TableColumn colProgramOnStudentWise;
    public JFXComboBox<String> cmbStudentId;
    public TableView<RegistrationByProgramIdTM> tblProgramIdWiseRegistration;
    public TableColumn colStudentIdOnProgramWise;
    public TableColumn colStudentNameOnProgramWise;
    public TableColumn colAddressOnProgramWise;
    public TableColumn colContactOnProgramWise;
    public TableColumn colAgeOnProgramWise;
    public TableColumn colRegNoOnProgramWise;
    public TableColumn colProgramIdOnProgramWise;
    public JFXComboBox<String> cmbProgramId;
    public TableView<RegistrationByRegNoTM> tblRegistrationByRegNo;
    public TableColumn colRegNoOnRegistrationByRegNo;
    public TableColumn colRegDateOnRegistrationByRegNo;
    public TableColumn colStudentIdOnRegistrationByRegNo;
    public TableColumn colCourseIdOnRegistrationByRegNo;
    public TableColumn colProgramOnRegistrationByRegNo;
    public JFXComboBox<String> cmbRegNo;
    RegistrationListFormBO registrationListFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATIONLIST);

    public void initialize() {
        try {
            loadAllRegistrationsToTable();
            setStudentIdsToCmb();
            setProgramIdsToCmb();
            setRegNoToCmb();
        } catch (Exception e) {

        }
    }

    private void setRegNoToCmb() throws Exception {
        ArrayList<RegistrationDTO> regNoList = registrationListFormBO.getAllRegistrations();
        for (RegistrationDTO dto : regNoList) {
            cmbRegNo.getItems().add(dto.getRegNo());
        }
    }

    private void setProgramIdsToCmb() throws Exception {
        ArrayList<CourseDTO> courseList = registrationListFormBO.getAllCourses();
        for (CourseDTO dto : courseList) {
            cmbProgramId.getItems().add(dto.getProgramId());
        }
    }

    private void setStudentIdsToCmb() throws Exception {
        ArrayList<StudentDTO> studentList = registrationListFormBO.getAllStudents();
        for (StudentDTO dto : studentList) {
            cmbStudentId.getItems().add(dto.getStudentId());
        }
    }

    private void loadAllRegistrationsToTable() throws Exception {
        ArrayList<RegistrationDTO> list = registrationListFormBO.getAllRegistrations();
        ObservableList<AllRegistrationTM> obList = FXCollections.observableArrayList();
        list.forEach(e -> {
            obList.add(new AllRegistrationTM(e.getRegNo(), e.getRegDate(), e.getTotalFee(), e.getStudentDTO().getStudentId()));
        });
        tblAllRegistration.setItems(obList);
        initAllRegistrationColumns();
    }

    private void initAllRegistrationColumns() {
        colRegNoOfAllRegistration.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colRegDateOfAllRegistration.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colTotalFeeOfAllRegistration.setCellValueFactory(new PropertyValueFactory<>("totalFee"));
        colStudentIdOfAllRegistration.setCellValueFactory(new PropertyValueFactory<>("studentId"));
    }

    public void searchRegistrationOnDateRange(MouseEvent mouseEvent) {
        if (fromDate.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select from date", ButtonType.CLOSE).showAndWait();
            fromDate.requestFocus();
            return;
        } else if (toDate.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select to date", ButtonType.CLOSE).showAndWait();
            toDate.requestFocus();
            return;
        }
        String fromDateValue = String.valueOf(fromDate.getValue());
        String toDateValue = String.valueOf(toDate.getValue());

        try {
            ArrayList<RegistrationDTO> list = registrationListFormBO.getRegistrationOnDateRange(fromDateValue, toDateValue);
            ObservableList<AllRegistrationTM> obList = FXCollections.observableArrayList();
            list.forEach(e -> {
                obList.add(new AllRegistrationTM(e.getRegNo(), e.getRegDate(), e.getTotalFee(), e.getStudentDTO().getStudentId()));
            });
            tblAllRegistration.setItems(obList);
        } catch (Exception e) {

        }
    }

    public void clearFieldsOnAction(MouseEvent mouseEvent) {
        fromDate.setValue(null);
        toDate.setValue(null);
        try {
            loadAllRegistrationsToTable();
        } catch (Exception e) {

        }
    }

    public void searchRegistrationOnStudentId(MouseEvent mouseEvent) {
        if (cmbStudentId.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a student id", ButtonType.CLOSE).showAndWait();
            cmbStudentId.requestFocus();
            return;
        }

        try {
            ArrayList<Object[]> list = registrationListFormBO.getRegistrationsOnStudentIds(cmbStudentId.getSelectionModel().getSelectedItem());
            ObservableList<RegistrationByStudentIdTM> obLIst = FXCollections.observableArrayList();
            for (Object[] obj : list) {
                obLIst.add(new RegistrationByStudentIdTM(obj[0].toString(), obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString()));
            }
            tblStudentWiseRegistration.setItems(obLIst);
            initTblStudentWiseRegistration();
        } catch (Exception e) {
        }

    }

    private void initTblStudentWiseRegistration() {
        colStudentIdOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentNameOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddressOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAgeOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("age"));
        colRegNoOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colProgramIdOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramOnStudentWise.setCellValueFactory(new PropertyValueFactory<>("program"));
    }

    public void clearStudentWiseRegistration(MouseEvent mouseEvent) {
        cmbStudentId.getSelectionModel().clearSelection();
        ObservableList<RegistrationByStudentIdTM> items = tblStudentWiseRegistration.getItems();
        items.clear();
        tblStudentWiseRegistration.refresh();
    }

    public void searchRegistrationOnProgramId(MouseEvent mouseEvent) {
        if (cmbProgramId.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a program id", ButtonType.CLOSE).showAndWait();
            cmbProgramId.requestFocus();
            return;
        }

        try {
            ArrayList<Object[]> list = registrationListFormBO.getRegistrationsOnProgramId(cmbProgramId.getValue());
            ObservableList<RegistrationByProgramIdTM> obList = FXCollections.observableArrayList();
            for (Object[] obj : list) {
                obList.add(new RegistrationByProgramIdTM(obj[0].toString(), obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString()));
            }
            tblProgramIdWiseRegistration.setItems(obList);
            initTblProgramWiseRegistration();
        } catch (Exception e) {
        }
    }

    private void initTblProgramWiseRegistration() {
        colStudentIdOnProgramWise.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentNameOnProgramWise.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddressOnProgramWise.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactOnProgramWise.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAgeOnProgramWise.setCellValueFactory(new PropertyValueFactory<>("age"));
        colRegNoOnProgramWise.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colProgramIdOnProgramWise.setCellValueFactory(new PropertyValueFactory<>("programId"));
    }

    public void clearOnProgramWiseRegistration(MouseEvent mouseEvent) {
        cmbProgramId.getSelectionModel().clearSelection();
        ObservableList<RegistrationByProgramIdTM> items = tblProgramIdWiseRegistration.getItems();
        items.clear();
        tblProgramIdWiseRegistration.refresh();
    }

    public void searchRegistrationByRegNo(MouseEvent mouseEvent) {
        if (cmbRegNo.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please select a registration No",ButtonType.CLOSE).showAndWait();
            cmbRegNo.requestFocus();
            return;
        }

        try {
            ArrayList<Object[]> regList = registrationListFormBO.getRegistrationsByRegNo(cmbRegNo.getValue());
            ObservableList<RegistrationByRegNoTM> obList = FXCollections.observableArrayList();
            for (Object[] obj : regList) {
                Date date = (Date) obj[1];
                obList.add(new RegistrationByRegNoTM(obj[0].toString(),date,obj[2].toString(),obj[3].toString(),obj[4].toString()));
            }
            tblRegistrationByRegNo.setItems(obList);
            initTblRegistrationByRegNo();
        } catch (Exception e) {

        }
    }

    private void initTblRegistrationByRegNo() {
        colRegNoOnRegistrationByRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colRegDateOnRegistrationByRegNo.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colStudentIdOnRegistrationByRegNo.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseIdOnRegistrationByRegNo.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramOnRegistrationByRegNo.setCellValueFactory(new PropertyValueFactory<>("program"));

    }

    public void clearRegistrationByRegNo(MouseEvent mouseEvent) {
        cmbRegNo.getSelectionModel().clearSelection();
        ObservableList<RegistrationByRegNoTM> items = tblRegistrationByRegNo.getItems();
        items.clear();
        tblRegistrationByRegNo.refresh();
    }
}
