package ru.byCooper.employeeProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class EmployeeControllerStream {
    private final EmployeeServiceStreamImpl service;

    public EmployeeControllerStream(EmployeeServiceStreamImpl service) {
        this.service = service;
    }

    @GetMapping(path = "/max-salary")
    public int maxSalary(@RequestParam("office") int office) {
        return service.maxSalary(office);
    }

    @GetMapping(path = "/min-salary")
    public int minSalary(@RequestParam("office") int office) {
        return service.minSalary(office);
    }

    @GetMapping(path = "/all")
    public List<Employee> officeUsers(@RequestParam("office") int office) {
        return service.officeUsers(office);
    }

    @GetMapping(path = "/all-in")
    public Collection<Employee> allOfficeUsers() {
        return service.allUsers();
    }
}
