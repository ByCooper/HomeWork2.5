package ru.byCooper.employeeProject.service;

import ru.byCooper.employeeProject.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    int maxSalary(int office);
    int minSalary(int office);
    String officeSalary(int office);
    List<Employee> officeUsers(int office);
    Map<Integer, List<Employee>> allUsers();
}
