package pt.iade.unimanagerdb.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanagerdb.models.Student;
import pt.iade.unimanagerdb.models.exceptions.NotFoundException;
import pt.iade.unimanagerdb.models.repositories.StudentRepository;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
    private Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Student> getStudents(){
        logger.info("Getting all students");
        return studentRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable int id) {
        logger.info("Getting student with id " + id);
        Optional<Student> _student = studentRepository.findById(id);
        if (_student.isEmpty())
            throw new NotFoundException("" + id, "Student", "id");
        else return _student.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student saveStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        logger.info("Saving student with id " + savedStudent.getId());
        return savedStudent;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteStudent(@PathVariable int id) {
        logger.info("Deleting student with id " + id);
        Optional<Student> _student = studentRepository.findById(id);
        if (_student.isEmpty()){
            throw new NotFoundException("" + id, "Student", "id");
        } else {
            studentRepository.deleteById(id);
            return new Response("Delete student with id " + id, null);
        }
        // No verification to see if it exists
        //studentRepository.deleteById(id);
        //return new Response("Delete student with id " + id, null);
    }

    // Devíamos usar o @RequestParam
    @GetMapping(path = "/date/{sdate}/{edate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Student> getStudentsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate sdate,
                                                @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate edate) {
        logger.info("Sending all students with birth dates between: " + sdate + " and " + edate);
        return studentRepository.findByBdateBetween(sdate, edate);
    }
}
