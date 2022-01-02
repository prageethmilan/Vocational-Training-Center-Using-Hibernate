package bo.custom.impl;

import bo.custom.HomeFormBO;
import dao.custom.CourseDAO;
import dao.DAOFactory;
import dao.custom.StudentDAO;

public class HomeFormBOImpl implements HomeFormBO {
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public String getStudentCount() throws Exception {
        return studentDAO.countStudents();
    }

    @Override
    public String getCoursesCount() throws Exception {
        return courseDAO.countCourses();
    }
}
