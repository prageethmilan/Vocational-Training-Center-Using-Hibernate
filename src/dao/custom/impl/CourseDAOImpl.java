package dao.custom.impl;

import dao.custom.CourseDAO;
import entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course course) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Serializable save = session.save(course);
        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Course WHERE programId = :programId");
        query.setParameter("programId", id);
        if (query.executeUpdate() > 0) {
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Course course) throws Exception {
        return false;
    }

    @Override
    public Course search(String programId) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, programId);
        transaction.commit();
        session.close();
        return course;
    }

    @Override
    public ArrayList<Course> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Course");
        List<Course> list = query.list();
        if (list.size() != 0) {
            transaction.commit();
            session.close();
            return (ArrayList<Course>) list;
        }
        transaction.rollback();
        session.close();
        return null;
    }

    @Override
    public String countCourses() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT COUNT(programId) FROM Course");
        List<Integer> list = sqlQuery.list();
        transaction.commit();
        session.close();
        return String.valueOf(list.get(0));
    }
}
