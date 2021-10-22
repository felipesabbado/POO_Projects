package pt.iade.unimanagerdb.models.repositories;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import pt.iade.unimanagerdb.models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    public Iterable<Student> findByBdateBetween(LocalDate start, LocalDate end);
    public Iterable<Student> findByUnitId(int id);
}
