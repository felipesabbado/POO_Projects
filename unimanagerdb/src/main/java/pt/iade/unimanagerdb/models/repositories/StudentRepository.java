package pt.iade.unimanagerdb.models.repositories;

import org.springframework.data.repository.CrudRepository;

import pt.iade.unimanagerdb.models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
