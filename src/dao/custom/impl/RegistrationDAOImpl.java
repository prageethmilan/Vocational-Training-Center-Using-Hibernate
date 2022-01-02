package dao.custom.impl;

import dao.custom.RegistrationDAO;
import entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean add(Registration registration) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(registration);
        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Registration registration) throws Exception {
        return false;
    }

    @Override
    public Registration search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Registration> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Registration");
        List<Registration> list = query.list();
        transaction.commit();
        session.close();
        return (ArrayList<Registration>) list;
    }

    @Override
    public String generateRegistrationNo() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT regNo FROM Registration ORDER BY regNo DESC LIMIT 1");
        String regNo = (String) sqlQuery.uniqueResult();
        transaction.commit();
        session.close();
        if (regNo != null) {
            int tempId = Integer.parseInt(regNo.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "Reg-00" + tempId;
            } else if (tempId <= 99) {
                return "Reg-0" + tempId;
            } else {
                return "Reg-" + tempId;
            }
        } else {
            return "Reg-001";
        }
    }

    @Override
    public ArrayList<Registration> getRegistrationDateRange(String fromDateValue, String toDateValue) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Registration WHERE regDate BETWEEN '" + fromDateValue + "' AND '" + toDateValue + "'");
        List<Registration> list = query.list();
        transaction.commit();
        session.close();
        return (ArrayList<Registration>) list;
    }
}
