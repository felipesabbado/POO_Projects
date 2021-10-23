package pt.iade.unimanagerdb.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id") private int id;
    @Column(name = "stu_name") private String name;
    @Column(name = "stu_place") private String place;
    @Column(name = "stu_bdate") private LocalDate bdate;
    @Column(name = "stu_gender") private char gender;
    @Column(name = "stu_email") private String email;
    @Column(name = "stu_cour_id") private int cour_id;

    public Student(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public LocalDate getBdate() {
        return bdate;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public int getCourseId() {
        return cour_id;
    }
}
