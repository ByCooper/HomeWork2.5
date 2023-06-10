package ru.byCooper.employeeProject;

import java.util.List;

public interface EmployeeService {
    Employee addPerson(Employee person);
    Employee deletePerson(Employee person);
    Employee searchPerson(Employee person);
    List<Employee> showAllPersons();
}
