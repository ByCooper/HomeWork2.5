package ru.byCooper.employeeProject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {

    int maxSalary(int office);
    int minSalary(int office);
    String officeSalary(int office);
    List<Employee> officeUsers(int office);
    Map<Integer, List<Employee>> allUsers();
}
