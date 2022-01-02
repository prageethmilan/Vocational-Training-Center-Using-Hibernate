package dao.custom.impl;

import dao.custom.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(user);
        if (save != null){
            transaction.commit();
            session.close();
            return true;
        }
        transaction.rollback();
        session.close();
        return false;
        /*try{
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }*/
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public User search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User");
        List<User> list = query.list();
        transaction.commit();
        session.close();
        return (ArrayList<User>) list;
    }

    @Override
    public boolean update(User user) throws Exception {
        return false;
    }

    @Override
    public User searchUser(String username, String password) throws Exception{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE userName = :userName AND password = :password");
        query.setParameter("userName",username);
        query.setParameter("password",password);
        List<User> users = query.list();
        for (User user : users) {
            transaction.commit();
            session.close();
            return user;
        }
        return null;
    }
}
