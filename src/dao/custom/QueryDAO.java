package dao.custom;

import dao.SuperDAO;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<Object[]> getAllRegistrationsByStudentid(String id) throws Exception;

    ArrayList<Object[]> getAllRegistrationsByProgramIds(String pid) throws Exception;

    ArrayList<Object[]> getAllRegistrationByRegNo(String regNo) throws Exception;
}
