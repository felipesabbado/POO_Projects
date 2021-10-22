package pt.iade.unimanagerdb.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanagerdb.models.Student;
import pt.iade.unimanagerdb.models.Unit;
import pt.iade.unimanagerdb.models.exceptions.NotFoundException;
import pt.iade.unimanagerdb.models.repositories.StudentRepository;
import pt.iade.unimanagerdb.models.repositories.UnitRepository;

@RestController
@RequestMapping(path = "/api/units")
public class UnitController {
    private Logger logger = LoggerFactory.getLogger(UnitController.class);
    @Autowired
    private UnitRepository unitRepository;
    private StudentRepository studentRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Unit> getUnits() {
        logger.info("Getting all units");
        return unitRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Unit getUnit(@PathVariable int id) {
        logger.info("Getting unit with id " + id);
        Optional<Unit> _unit = unitRepository.findById(id);
        if (_unit.isEmpty())
            throw new NotFoundException("" + id, "Unit", "id");
        else return _unit.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Unit saveUnit(@RequestBody Unit unit) {
        Unit savedUnit = unitRepository.save(unit);
        logger.info("Saving unit with id " + savedUnit.getId());
        return savedUnit;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUnit(@PathVariable int id) {
        logger.info("Deleting unit with id " + id);
        Optional<Unit> _unit = unitRepository.findById(id);
        if (_unit.isEmpty()){
            throw new NotFoundException("" + id, "Unit", "id");
        } else {
            unitRepository.deleteById(id);
            return new Response("Delete unit with id " + id, null);
        }
        // No verification to see if it exists
        //unitRepository.deleteById(id);
        //return new Response("Delete unit with id " + id, null);
    }

    @RequestMapping(path = "/{id}/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Student> getUnitStudents(@PathVariable int id){
        logger.info("Sending all students of the unit");
        return studentRepository.findByUnitId(id);
    }
}
