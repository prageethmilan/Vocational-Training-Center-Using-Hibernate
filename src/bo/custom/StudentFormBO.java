package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import view.tm.StudentTM;

import java.util.ArrayList;

public interface StudentFormBO extends SuperBO {
    String generateStudentIds() throws Exception;

    boolean saveStudent(StudentDTO studentDTO) throws Exception;

    ArrayList<StudentDTO> getAllStudents() throws Exception;

    ArrayList<StudentDTO> searchStudent(String newValue) throws Exception;

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    boolean deleteStudent(StudentTM selectedItem) throws Exception;
}
