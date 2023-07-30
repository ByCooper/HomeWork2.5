package ru.byCooper.employeeProject.service;

import ru.byCooper.employeeProject.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Employee addPerson(String name, String lastName, String middleName, int office, int salary);

    String deletePerson(String name, String lastName);

    Employee searchPerson(String name, String lastName);

    Collection<Employee> showAllPersons();
    String allSalary();
    String minSalary();
    String maxSalary();
    String averageSalary();
    String indexSalary(int percent);
    String getMinimalSalaryInOffice(int office);
    String getMaximalSalaryInOffice(int office);
    String getAllSalaryOffice(int office);
    String getAverageSalaryOffice(int office);
    String getIndexSalaryOffice(int office, int percent);
    List<Employee> getAllEmployeeOffice(int office);
    List<Employee> sourceSalaryOfEmployeeLow(int salary);
    List<Employee> sourceSalaryOfEmployeeHigh(int salary);
    String changeSalary(String name, String lastName, int salary);
    String changeOffice(String name, String lastName, int office);
    List<Employee> printEmployeeWithOffice();
    default List<Employee> printAllInOffice(int office) {
        return null;
    }
}
