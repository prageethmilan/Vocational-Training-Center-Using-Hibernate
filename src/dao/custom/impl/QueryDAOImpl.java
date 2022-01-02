package dao.custom.impl;

import dao.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public boolean add(Object o) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object o) throws Exception {
        return false;
    }

    @Override
    public boolean update(Object o) throws Exception {
        return false;
    }

    @Override
    public Object search(Object o) throws Exception {
        return null;
    }

    @Override
    public ArrayList getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Object[]> getAllRegistrationsByStudentid(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("SELECT s.id,s.name,s.address,s.contact,s.age,r.regNo,rd.Course_ID,c.program FROM Student s INNER JOIN Registration r INNER JOIN Registration_Details rd,Course c WHERE s.id = :studentId and r.Student_ID=s.id and r.regNo=rd.Reg_ID and c.programId=rd.Course_ID");
        query.setParameter("studentId", id);
        List<Object[]> list = query.list();
        transaction.commit();
        session.close();
        return (ArrayList<Object[]>) list;
    }

    @Override
    public ArrayList<Object[]> getAllRegistrationsByProgramIds(String pid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("select s.id,s.name, s.address,s.contact,s.age,r.regNo,rc.Course_ID from Student s INNER JOIN Course c,Registration r,Registration_Details rc where c.programId = :programId and r.Student_ID=s.id and r.regNo=rc.Reg_ID and rc.Course_ID=c.programId");
        query.setParameter("programId", pid);
        List<Object[]> list = query.list();
        transaction.commit();
        session.close();
        return (ArrayList<Object[]>) list;
    }

    @Override
    public ArrayList<Object[]> getAllRegistrationByRegNo(String regNo) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("SELECT r.regNo,r.regDate,r.Student_ID,rd.Course_ID,c.program FROM Registration r INNER JOIN Registration_Details rd INNER JOIN Course c WHERE rd.Reg_ID = :registrationNo and r.regNo=rd.Reg_ID and rd.Course_ID=c.programId");
        query.setParameter("registrationNo",regNo);
        List<Object[]> list = query.list();
        transaction.commit();
        session.close();
        return (ArrayList<Object[]>) list;
    }
}
