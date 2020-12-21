package ru.job4j.rest.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.rest.model.Employee;
import ru.job4j.rest.model.Person;
import ru.job4j.rest.repository.EmployeeRepository;
import ru.job4j.rest.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/employee")
public class EmployeeContoller {
    private final EmployeeRepository employees;

    public EmployeeContoller(EmployeeRepository employees) {
        this.employees = employees;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return StreamSupport.stream(
                this.employees.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        var person = this.employees.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Employee()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                employees.save(employee),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        employees.save(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employees.delete(employee);
        return ResponseEntity.ok().build();
    }
}
