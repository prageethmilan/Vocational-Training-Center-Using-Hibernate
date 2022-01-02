package controller;

import bo.BOFactory;
import bo.custom.HomeFormBO;
import javafx.scene.control.Label;

public class HomeFormController {

    public Label lblStudentCount;
    public Label lblCourseCount;
    HomeFormBO homeFormBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HOME);

    public void initialize() {
        try {
            lblStudentCount.setText(homeFormBO.getStudentCount());
            lblCourseCount.setText(homeFormBO.getCoursesCount());
        } catch (Exception e) {
        }

    }
}
