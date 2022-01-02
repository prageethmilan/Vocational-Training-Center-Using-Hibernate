package dao.custom;

import dao.SuperDAO;
import entity.Registration;

import java.util.ArrayList;

public interface RegistrationDAO extends SuperDAO<Registration, String> {
    String generateRegistrationNo() throws Exception;

    ArrayList<Registration> getRegistrationDateRange(String fromDateValue, String toDateValue) throws Exception;
}
