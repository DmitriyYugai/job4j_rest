package ru.job4j.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.rest.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
