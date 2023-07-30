package ru.byCooper.employeeProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.byCooper.employeeProject.service.DepartmentServiceImpl;
import ru.byCooper.employeeProject.model.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentServiceImpl service;

    public DepartmentController(DepartmentServiceImpl service) {
        this.service = service;
    }

    @GetMapping(path = "{id}/max-salary") //Возвращает максимальную зп по офису
    public int maxSalary(@PathVariable int id) {
        return service.maxSalary(id);
    }

    @GetMapping(path = "{id}/min-salary") //Возвращает минимальную зп по офису
    public int minSalary(@PathVariable int id) {
        return service.minSalary(id);
    }

    @GetMapping(path = "{id}/all") //Возвращает список сотрудников офиса
    public List<Employee> officeUsers(@PathVariable int id) {
        return service.officeUsers(id);
    }

    @GetMapping(path = "/all-employee")
    public Map<Integer, List<Employee>> allOfficeUsers() {
        return service.allUsers();
    }

    @GetMapping(path = "{id}/sum") //Возвращает список сотрудников офиса
    public String officeSalary(@PathVariable int id) {
        return service.officeSalary(id);
    }
}
