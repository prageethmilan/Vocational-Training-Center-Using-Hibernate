package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case USERSIGNUP:
                return (T) new SignUpFormBOImpl();
            case USERLOGIN:
                return (T) new LogInFormBOImpl();
            case STUDENT:
                return (T) new StudentFormBOImpl();
            case DASHBOARD:
                return (T) new DashboardFormBOImpl();
            case HOME:
                return (T) new HomeFormBOImpl();
            case COURSE:
                return (T) new CourseFormBOImpl();
            case REGISTRATION:
                return (T) new RegistrationFormBOImpl();
            case REGISTRATIONLIST:
                return (T) new RegistrationLIstFormBOImpl();
            default:
                return null;
        }

    }

    public enum BOTypes {
        USERSIGNUP, USERLOGIN, STUDENT, DASHBOARD, HOME, COURSE, REGISTRATION, REGISTRATIONLIST
    }
}
