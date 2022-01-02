package bo.custom.impl;

import bo.custom.RegistrationListFormBO;
import dao.*;
import dao.custom.CourseDAO;
import dao.custom.QueryDAO;
import dao.custom.RegistrationDAO;
import dao.custom.StudentDAO;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Registration;
import entity.Student;

import java.util.ArrayList;

public class RegistrationLIstFormBOImpl implements RegistrationListFormBO {
    RegistrationDAO registrationDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    QueryDAO queryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public ArrayList<RegistrationDTO> getAllRegistrations() throws Exception {
        ArrayList<Registration> list = registrationDAO.getAll();
        ArrayList<RegistrationDTO> allList = new ArrayList<>();
        for (Registration r : list) {
            Student s = r.getStudent();
            StudentDTO dto = new StudentDTO(s.getId(), s.getName(), s.getAddress(), s.getContact(), s.getDateOfBirth(), s.getAge());
            allList.add(new RegistrationDTO(r.getRegNo(), r.getRegDate(), r.getTotalFee(), dto));
        }
        return allList;
    }

    @Override
    public ArrayList<RegistrationDTO> getRegistrationOnDateRange(String fromDateValue, String toDateValue) throws Exception {
        ArrayList<Registration> list = registrationDAO.getRegistrationDateRange(fromDateValue, toDateValue);
        ArrayList<RegistrationDTO> allList = new ArrayList<>();
        for (Registration r : list) {
            Student s = r.getStudent();
            StudentDTO dto = new StudentDTO(s.getId(), s.getName(), s.getAddress(), s.getContact(), s.getDateOfBirth(), s.getAge());
            allList.add(new RegistrationDTO(r.getRegNo(), r.getRegDate(), r.getTotalFee(), dto));
        }
        return allList;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        ArrayList<Student> allStudents = studentDAO.getAll();
        ArrayList<StudentDTO> studentList = new ArrayList<>();
        for (Student s : allStudents) {
            studentList.add(new StudentDTO(s.getId(), s.getName(), s.getAddress(), s.getContact(), s.getDateOfBirth(), s.getAge()));
        }
        return studentList;
    }

    @Override
    public ArrayList<Object[]> getRegistrationsOnStudentIds(String id) throws Exception {
        ArrayList<Object[]> list = queryDAO.getAllRegistrationsByStudentid(id);
        return list;
    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws Exception {
        ArrayList<Course> allCourses = courseDAO.getAll();
        ArrayList<CourseDTO> courseList = new ArrayList<>();
        for (Course c : allCourses) {
            courseList.add(new CourseDTO(c.getProgramId(), c.getProgram(), c.getDuration(), c.getFee()));
        }
        return courseList;
    }

    @Override
    public ArrayList<Object[]> getRegistrationsOnProgramId(String pid) throws Exception {
        ArrayList<Object[]> list = queryDAO.getAllRegistrationsByProgramIds(pid);
        return list;
    }

    @Override
    public ArrayList<Object[]> getRegistrationsByRegNo(String regNo) throws Exception {
        ArrayList<Object[]> list = queryDAO.getAllRegistrationByRegNo(regNo);
        return list;
    }
}
