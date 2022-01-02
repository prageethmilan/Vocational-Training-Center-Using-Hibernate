package bo.custom.impl;

import bo.custom.CoursesFormBO;
import dao.custom.CourseDAO;
import dao.DAOFactory;
import dto.CourseDTO;
import entity.Course;
import view.tm.CourseTM;

import java.util.ArrayList;

public class CourseFormBOImpl implements CoursesFormBO {
    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean saveCourse(CourseDTO dto) throws Exception {
        Course course = new Course(dto.getProgramId(), dto.getProgram(), dto.getDuration(), dto.getFee());
        return courseDAO.add(course);
    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws Exception {
        ArrayList<Course> all = courseDAO.getAll();
        ArrayList<CourseDTO> allCourses = new ArrayList<>();
        for (Course c : all) {
            allCourses.add(new CourseDTO(c.getProgramId(),c.getProgram(),c.getDuration(),c.getFee()));
        }
        return allCourses;
    }

    @Override
    public boolean deleteCourse(CourseTM selectedItem) throws Exception {
        return courseDAO.delete(selectedItem.getProgramId());
    }
}
