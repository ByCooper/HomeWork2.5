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

    public Employee addPerson(Employee person) {
        if (employee.size() > maxEmployee) {
            throw new EmployeeStorageIsFullException("Превышение количества сотрудников");
        } else if (employee.contains(person)) {
            throw new EmployeeAlreadyAddedException("Такая запись уже существует");
        } else {
            employee.add(person);
            return employee.get(employee.indexOf(person));
        }
    }

        public void deletePerson(Employee person) throws EmployeeNotFoundException{
        if (!employee.contains(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            employee.remove(person);
        }
    }

    public Employee searchPerson(Employee person) throws EmployeeNotFoundException{
        if (!employee.contains(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return person;
        }
    }

    public void showAllPersons() {
        for (Employee person : employee) {
            System.out.println(person);
        }
    }
    public String sayHello() {
        return "Добро пожаловать";
    }
}
