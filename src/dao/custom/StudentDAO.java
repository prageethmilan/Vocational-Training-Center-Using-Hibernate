package dao.custom;

import dao.SuperDAO;
import entity.Student;

import java.util.ArrayList;

public interface StudentDAO extends SuperDAO<Student,String> {
    String generateIds() throws Exception;

    ArrayList<Student> searchStudents(String id) throws Exception;

    String countStudents() throws Exception;
}
