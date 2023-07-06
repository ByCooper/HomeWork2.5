package ru.byCooper.employeeProject;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private int office;
    private int salary;

    public Employee(String fName, String lName, String mName, int office, int salary) {
        this.firstName = StringUtils.capitalize(fName);
        this.lastName = StringUtils.capitalize(lName);
        this.middleName = StringUtils.capitalize(mName);
        this.office = office;
        this.salary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Сотрудник " + firstName + " " + middleName + " " + lastName + ", office=" + office + ", salary=" + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}