package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;

import java.util.ArrayList;

public interface RegistrationListFormBO extends SuperBO {
    ArrayList<RegistrationDTO> getAllRegistrations() throws Exception;

    ArrayList<RegistrationDTO> getRegistrationOnDateRange(String fromDateValue, String toDateValue) throws Exception;

    ArrayList<StudentDTO> getAllStudents() throws Exception;

    ArrayList<Object[]> getRegistrationsOnStudentIds(String selectedItem) throws Exception;

    ArrayList<CourseDTO> getAllCourses() throws Exception;

    ArrayList<Object[]> getRegistrationsOnProgramId(String value) throws Exception;

    ArrayList<Object[]> getRegistrationsByRegNo(String value) throws Exception;
}
