package ru.byCooper.employeeProject;

import org.springframework.stereotype.Service;
import ru.byCooper.employeeProject.exception.EmployeeAlreadyAddedException;
import ru.byCooper.employeeProject.exception.EmployeeNotFoundException;
import ru.byCooper.employeeProject.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final int maxEmployee = 10;
    private final List<Employee> employee = new ArrayList<>();
    @Override
    public Employee addPerson(String name, String lastName) {
        Employee person = new Employee(name, lastName);
        if (employee.size() > maxEmployee) {
            throw new EmployeeStorageIsFullException("Нет свободных позиций");
        } else if (employee.contains(person)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже существует");
        } else {
            employee.add(person);
            return employee.get(employee.indexOf(person));
        }
    }
    @Override
    public Employee deletePerson(String name, String lastName) {
        Employee person = new Employee(name, lastName);
        if (!employee.contains(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            employee.remove(person);
            return person;
        }
    }
    @Override
    public Employee searchPerson(String name, String lastName) {
        Employee person = new Employee(name, lastName);
        if (!employee.contains(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return person;
        }
    }
    @Override
    public List<Employee> showAllPersons() {
        return employee;
    }
}
