package ru.job4j.rest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.job4j.rest.model.Employee;
import ru.job4j.rest.model.Person;

import java.util.List;

@RestController
@RequestMapping("/person-employee")
public class EmployeePersonController {

    @Autowired
    private RestTemplate rest;

    private static final String API = "http://localhost:8080/person/";

    private static final String API_ID = "http://localhost:8080/person/{id}";

    @GetMapping("/")
    public ResponseEntity<Employee> getEmployee(@RequestHeader("Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        List<Person> accounts = rest.exchange(
                API,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
        Employee employee = new Employee("Yugay", "9876543",
                null, accounts);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id,
                                                @RequestHeader("Authorization") String token) {
        Person person = new Person();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            person = rest.exchange(
                    API + id,
                    HttpMethod.GET, entity, new ParameterizedTypeReference<Person>() { }
            ).getBody();
        } catch (HttpClientErrorException ex)   {
            return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> createPerson(@RequestBody Person person,
                                               @RequestHeader("Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Person> entity = new HttpEntity<>(person, headers);
        Person rsl = rest.postForObject(API + "sign-up", entity, Person.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> updatePerson(@RequestBody Person person,
                                             @RequestHeader("Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Person> entity = new HttpEntity<>(person, headers);
        rest.put(API, entity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id,
                                             @RequestHeader("Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        rest.exchange(
                API + id,
                HttpMethod.DELETE, entity, new ParameterizedTypeReference<Void>() { }
        );
        return ResponseEntity.ok().build();
    }
}
