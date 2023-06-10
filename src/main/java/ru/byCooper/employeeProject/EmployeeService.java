package ru.byCooper.employeeProject;

public interface EmployeeService {
    void addPerson(Employee person);
    void deletePerson(Employee person);
    Employee searchPerson(Employee person);
    void showAllPersons();
}
