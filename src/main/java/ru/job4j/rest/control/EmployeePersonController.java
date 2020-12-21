package ru.job4j.rest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.model.Employee;
import ru.job4j.rest.model.Person;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/person-employee")
public class EmployeePersonController {

    @Autowired
    private RestTemplate rest;

    private static final String API = "http://localhost:8080/person/";

    private static final String API_ID = "http://localhost:8080/person/{id}";

    @GetMapping("/")
    public ResponseEntity<Employee> getEmployee() {
        List<Person> accounts = rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
        Employee employee = new Employee("Yugay", "9876543",
                Timestamp.valueOf(LocalDateTime.now()), accounts);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        Person person = new Person();
        try {
            person = rest.getForObject(API_ID, Person.class, id);
        } catch (HttpClientErrorException ex)   {
            return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person rsl = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
