package bo.custom;

import bo.SuperBO;

public interface DashboardFormBO extends SuperBO {
    String getStudentCount() throws Exception;

    String getCoursesCount() throws Exception;
}
