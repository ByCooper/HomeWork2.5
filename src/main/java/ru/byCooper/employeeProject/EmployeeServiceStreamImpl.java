package ru.byCooper.employeeProject;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceStreamImpl implements DepartmentService {

    private final EmployeeServiceImpl employee;

    public EmployeeServiceStreamImpl(EmployeeServiceImpl employee) {
        this.employee = employee;
    }


    @Override
    public Optional<Employee> maxSalary(int office) {
        return employee.getAll().stream()
                .filter(e -> e.getOffice() == office)
                .max(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public Optional<Employee> minSalary(int office) {
        return employee.getAll().stream()
                .filter(e -> e.getOffice() == office)
                .min(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public List<Employee> officeUsers(int office) {
        return employee.getAll().stream()
                .filter(e -> e.getOffice() == office)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> allUsers() {
        return employee.getAll().stream()
                .sorted(Comparator.comparing(Employee::getOffice))
                .collect(Collectors.toList());
    }
}
