package ru.byCooper.employeeProject;

import org.springframework.stereotype.Service;
import ru.byCooper.employeeProject.exception.EmployeeAlreadyAddedException;
import ru.byCooper.employeeProject.exception.EmployeeNotFoundException;
import ru.byCooper.employeeProject.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl {
    final int maxEmployee = 10;
    List<Employee> employee = new ArrayList<>();

    public Employee addPerson(String name, String lastName) {
        Employee person = new Employee(name, lastName);
        if (employee.size() > maxEmployee) {
            throw new EmployeeStorageIsFullException("Превышение количества сотрудников");
        } else if (employee.contains(person)) {
            throw new EmployeeAlreadyAddedException("Такая запись уже существует");
        } else {
            employee.add(person);
            return employee.get(employee.indexOf(person));
        }
    }

    public Employee deletePerson(String name, String lastName) throws EmployeeNotFoundException {
        Employee person = new Employee(name, lastName);
        if (!employee.contains(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            employee.remove(person);
            return person;
        }
    }

    public Employee searchPerson(String name, String lastName) throws EmployeeNotFoundException {
        Employee person = new Employee(name, lastName);
        if (!employee.contains(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return person;
        }
    }

    public List<Employee> showAllPersons() {
        return employee;
    }
}
