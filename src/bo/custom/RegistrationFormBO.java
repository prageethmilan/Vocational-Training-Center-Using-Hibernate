package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;

import java.util.ArrayList;

public interface RegistrationFormBO extends SuperBO {
    String generateRegNo() throws Exception;

    ArrayList<StudentDTO> getAllStudents() throws Exception;

    ArrayList<CourseDTO> getAllCourses() throws Exception;

    StudentDTO getStudent(String id) throws Exception;

    CourseDTO getCourse(String programId) throws Exception;

    boolean saveRegistration(RegistrationDTO registrationDTO) throws Exception;
}
