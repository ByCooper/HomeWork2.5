package ru.byCooper.employeeProject;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceStreamImpl implements EmployeeServiceStream{

    private final EmployeeServiceImpl employee;

    public EmployeeServiceStreamImpl(EmployeeServiceImpl employee) {
        this.employee = employee;
    }


    @Override
    public int maxSalary(int office) {
        int max = employee.forStream().values().stream()
                .filter(e -> e.getOffice() == office)
                .mapToInt(Employee::getSalary)
                .max().orElse(0);

        return max;
    }

    @Override
    public int minSalary(int office) {
        int min = employee.forStream().values().stream()
                .filter(e -> e.getOffice() == office)
                .mapToInt(Employee::getSalary)
                .min().orElse(0);

        return min;
    }

    @Override
    public List<Employee> officeUsers(int office) {
        return employee.forStream().values().stream()
                .filter(e -> e.getOffice() == office)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> allUsers() {
        return employee.forStream().values().stream()
                .sorted(Comparator.comparing(Employee::getOffice))
                .collect(Collectors.toList());
    }
}
