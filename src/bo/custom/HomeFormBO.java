package bo.custom;

import bo.SuperBO;

public interface HomeFormBO extends SuperBO {
    String getStudentCount() throws Exception;

    String getCoursesCount() throws Exception;
}
