package bo.custom.impl;

import bo.custom.RegistrationFormBO;
import dao.custom.CourseDAO;
import dao.DAOFactory;
import dao.custom.RegistrationDAO;
import dao.custom.StudentDAO;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Registration;
import entity.Student;

import java.util.ArrayList;

public class RegistrationFormBOImpl implements RegistrationFormBO {
    RegistrationDAO registrationDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public String generateRegNo() throws Exception {
        return registrationDAO.generateRegistrationNo();
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
    public ArrayList<CourseDTO> getAllCourses() throws Exception {
        ArrayList<Course> allCourses = courseDAO.getAll();
        ArrayList<CourseDTO> courseList = new ArrayList<>();
        for (Course c : allCourses) {
            courseList.add(new CourseDTO(c.getProgramId(), c.getProgram(), c.getDuration(), c.getFee()));
        }
        return courseList;
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        Student student = studentDAO.search(id);
        StudentDTO dto = new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getContact(), student.getDateOfBirth(), student.getAge());
        return dto;
    }

    @Override
    public CourseDTO getCourse(String programId) throws Exception {
        Course c = courseDAO.search(programId);
        CourseDTO dto = new CourseDTO(c.getProgramId(), c.getProgram(), c.getDuration(), c.getFee());
        return dto;
    }

    @Override
    public boolean saveRegistration(RegistrationDTO registrationDTO) throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        StudentDTO dto = registrationDTO.getStudentDTO();
        Student student = new Student(dto.getStudentId(),dto.getName(),dto.getAddress(),dto.getContact());
        for (CourseDTO courseDTO : registrationDTO.getCourseDTOS()) {
            courses.add(new Course(courseDTO.getProgramId(),courseDTO.getProgram(),courseDTO.getDuration(),courseDTO.getFee()));
        }
        Registration registration = new Registration(registrationDTO.getRegNo(),registrationDTO.getRegDate(),registrationDTO.getTotalFee(),student,courses);
        return registrationDAO.add(registration);
    }
}
