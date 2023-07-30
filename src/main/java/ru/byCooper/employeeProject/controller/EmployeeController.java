package ru.byCooper.employeeProject.controller;

import org.springframework.web.bind.annotation.*;
import ru.byCooper.employeeProject.service.EmployeeServiceImpl;
import ru.byCooper.employeeProject.model.Employee;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    @ExceptionHandler
//    public ResponseEntity<String> handleException(Exception e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("name") String name,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("middleName") String middleName,
                                @RequestParam("office") int office,
                                @RequestParam("salary") int salary) {
        return employeeService.addPerson(name, lastName, middleName, office, salary);
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("name") String name,
                                 @RequestParam("lastName") String lastName) {
        return employeeService.deletePerson(name, lastName);
    }

    @GetMapping("/find")
    public Employee searchEmployee(@RequestParam("name") String name,
                                   @RequestParam("lastName") String lastName) {
        return employeeService.searchPerson(name, lastName);
    }

    @GetMapping("/all")
    public Collection<Employee> allEmployee() {
        return employeeService.showAllPersons();
    }

    @GetMapping("/allSalary")
    public String allSalary() {
        return employeeService.allSalary();
    }

    @GetMapping("/minSalary")
    public String minSalary() {
        return employeeService.minSalary();
    }

    @GetMapping("/maxSalary")
    public String maxSalary() {
        return employeeService.maxSalary();
    }

    @GetMapping("/avrgSalary")
    public String averageSalary() {
        return employeeService.averageSalary();
    }

    @GetMapping("/indexSalary")
    public String indexSalary(@RequestParam("percent") int percent) {
        return employeeService.indexSalary(percent);
    }

    @GetMapping("/findSalaryLow")
    public List<Employee> findSalaryLow(@RequestParam("value") int value) {
        return employeeService.sourceSalaryOfEmployeeLow(value);
    } // Надо проверить выдает только одного

    @GetMapping("/findSalaryHigh")
    public List<Employee> findSalaryHigh(@RequestParam("value") int value) {
        return employeeService.sourceSalaryOfEmployeeHigh(value);
    }// Надо проверить выдает только одного

    @GetMapping("/minSalaryOffice")
    public String minSalaryOffice(@RequestParam("office") int office) {
        return employeeService.getMinimalSalaryInOffice(office);
    }

    @GetMapping("/maxSalaryOffice")
    public String maxSalaryOffice(@RequestParam("office") int office) {
        return employeeService.getMaximalSalaryInOffice(office);
    }

    @GetMapping("/allSalaryOffice")
    public String allSalaryOffice(@RequestParam("office") int office) {
        return employeeService.getAllSalaryOffice(office);
    }

    @GetMapping("/averageSalaryOffice")
    public String averageSalaryOffice(@RequestParam("office") int office) {
        return employeeService.getAverageSalaryOffice(office);
    }

    @GetMapping("/indexSalaryOffice")
    public String indexSalaryOffice(@RequestParam("office") int office,
                                    @RequestParam("percent") int percent)
    {
        return employeeService.getIndexSalaryOffice(office, percent);
    }

    @GetMapping("/getAllEmployeeOffice")
    public List<Employee> getAllEmployeeOffice(@RequestParam("office") int office)
    {
        return employeeService.getAllEmployeeOffice(office);
    }

    @GetMapping("/changeSalary")
    public String changeSalary(@RequestParam("name") String name,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("salary") int salary)
    {
        return employeeService.changeSalary(name, lastName,salary);
    }

    @GetMapping("/changeOffice")
    public String changeOffice(@RequestParam("name") String name,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("office") int office)
    {
        return employeeService.changeOffice(name, lastName,office);
    }

    @GetMapping("/printOffice")
    public List<Employee> printOffice()
    {
        return employeeService.printEmployeeWithOffice();
    }
}
