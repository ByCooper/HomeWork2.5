package ru.byCooper.employeeProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("name") String name, @RequestParam("lastName") String lastName)
    {
           return employeeService.addPerson(name, lastName);
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("name") String name,
                                   @RequestParam("lastName") String lastName)
    {
           return employeeService.deletePerson(name, lastName);
    }

    @GetMapping("/find")
    public Employee searchEmployee(@RequestParam("name") String name,
                                   @RequestParam("lastName") String lastName)
    {
            return employeeService.searchPerson(name, lastName);
    }

    @GetMapping("/all")
    public List<Employee> allEmployee() {
        return employeeService.showAllPersons();
    }
}
