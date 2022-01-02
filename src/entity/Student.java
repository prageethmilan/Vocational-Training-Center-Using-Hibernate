package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private String contact;
    private String dateOfBirth;
    private int age;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    public Student() {
    }

    public Student(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Student(String id, String name, String address, String contact, String dateOfBirth, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public Student(String id, String name, String address, String contact, String dateOfBirth, int age, List<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.registrations = registrations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", age=" + age +
                '}';
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
