package ru.byCooper.employeeProject;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Optional<Employee> maxSalary(int office);
    Optional<Employee> minSalary(int office);
    List<Employee> officeUsers(int office);
    Collection<Employee> allUsers();
}
