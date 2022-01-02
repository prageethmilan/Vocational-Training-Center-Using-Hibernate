package bo.custom.impl;

import bo.custom.StudentFormBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;
import view.tm.StudentTM;

import java.util.ArrayList;

public class StudentFormBOImpl implements StudentFormBO {
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String generateStudentIds() throws Exception {
        return studentDAO.generateIds();
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        Student student = new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDateOfBirth(), studentDTO.getAge());
        return studentDAO.add(student);
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        ArrayList<Student> students = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        for (Student s : students) {
            allStudents.add(new StudentDTO(s.getId(),s.getName(),s.getAddress(),s.getContact(),s.getDateOfBirth(),s.getAge()));
        }
        return allStudents;
    }

    @Override
    public ArrayList<StudentDTO> searchStudent(String newValue) throws Exception {
        ArrayList<Student> students = studentDAO.searchStudents(newValue);
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        for (Student s : students) {
            allStudents.add(new StudentDTO(s.getId(),s.getName(),s.getAddress(),s.getContact(),s.getDateOfBirth(),s.getAge()));
        }
        return allStudents;
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        Student student = new Student(dto.getStudentId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDateOfBirth(), dto.getAge());
        return studentDAO.update(student);
    }

    @Override
    public boolean deleteStudent(StudentTM selectedItem) throws Exception {
        return studentDAO.delete(selectedItem.getId());
    }
}
