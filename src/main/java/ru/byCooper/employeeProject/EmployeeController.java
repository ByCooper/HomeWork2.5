package ru.byCooper.employeeProject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private final EmployeeServiceImpl employeeService;
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("name") String name,
                              @RequestParam("lastName") String lastName)
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
