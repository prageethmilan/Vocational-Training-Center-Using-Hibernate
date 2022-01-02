package bo.custom;

import bo.SuperBO;
import dto.CourseDTO;
import view.tm.CourseTM;

import java.util.ArrayList;

public interface CoursesFormBO extends SuperBO {
    boolean saveCourse(CourseDTO courseDTO) throws Exception;

    ArrayList<CourseDTO> getAllCourses() throws Exception;

    boolean deleteCourse(CourseTM selectedItem) throws Exception;

}
