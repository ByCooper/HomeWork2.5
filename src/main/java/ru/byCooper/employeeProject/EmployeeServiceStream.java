package ru.byCooper.employeeProject;

import java.util.Collection;
import java.util.List;

public interface EmployeeServiceStream {

    int maxSalary(int office);
    int minSalary(int office);
    List<Employee> officeUsers(int office);
    Collection<Employee> allUsers();
}
