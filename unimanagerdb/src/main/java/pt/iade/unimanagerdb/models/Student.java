package pt.iade.unimanagerdb.models;

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
    @Column(name = "stu_bdate") private String bdate;
    @Column(name = "stu_gender") private String gender;
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

    public String getBdate() {
        return bdate;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public int getCour_id() {
        return cour_id;
    }
}
