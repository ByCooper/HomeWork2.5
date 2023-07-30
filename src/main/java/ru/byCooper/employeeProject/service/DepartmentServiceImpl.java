package ru.byCooper.employeeProject.service;

import org.springframework.stereotype.Service;
import ru.byCooper.employeeProject.model.Employee;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employee;

    public DepartmentServiceImpl(EmployeeServiceImpl employee) {
        this.employee = employee;
    }


    @Override
    public int maxSalary(int office) {
        return employee.getAll().stream()
                .filter(e -> e.getOffice() == office)
                .mapToInt(Employee::getSalary)
                .max()
                .getAsInt();
    }

    @Override
    public int minSalary(int office) {
        return employee.getAll().stream()
                .filter(e -> e.getOffice() == office)
                .mapToInt(Employee::getSalary)
                .min()
                .getAsInt();
    }

    @Override
    public String officeSalary(int office) {
        return "Сумма расходов составляет " + employee.getAll().stream()
                .filter(e -> e.getOffice() == office)
                .mapToInt(Employee::getSalary)
                .sum() + " рублей";
    }

    @Override
    public List<Employee> officeUsers(int office) {
        return employee.getAll().stream()
                .filter(e -> e.getOffice() == office)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> allUsers() {
        return employee.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getOffice, Collectors.toList()));
    }
}
