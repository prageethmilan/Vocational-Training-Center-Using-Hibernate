package dao.custom;

import dao.SuperDAO;
import entity.Course;

public interface CourseDAO extends SuperDAO<Course, String> {

    String countCourses() throws Exception;
}
