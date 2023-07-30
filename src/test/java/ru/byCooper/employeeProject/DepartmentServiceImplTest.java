package ru.byCooper.employeeProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.byCooper.employeeProject.model.Employee;
import ru.byCooper.employeeProject.service.DepartmentServiceImpl;
import ru.byCooper.employeeProject.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @InjectMocks
    private DepartmentServiceImpl cut;
    @Mock
    private EmployeeServiceImpl cutMock;

    private List<Employee> dataTest() {
        String name1= "Jack";
        String lname1= "Sparow";
        String mname1= "Krakenovich";
        int salary1= 950;
        int office1= 1;
        Employee user1 = new Employee(name1, lname1, mname1, office1, salary1);

        String name2= "Shrek";
        String lname2= "Bolotov";
        String mname2= "Fionovich";
        int salary2= 870;
        Employee user2 = new Employee(name2, lname2, mname2, office1, salary2);

        String name3= "Hulk";
        String lname3= "Incredible";
        String mname3= "Yodovich";
        int salary3= 930;
        int office2= 2;
        Employee user3 = new Employee(name3, lname3, mname3, office2, salary3);

        List<Employee> test = new ArrayList<>();
        test.add(user1);
        test.add(user2);
        test.add(user3);
        return test;
    }

    @Test
    void maxSalary() {
        //Подготовка входных данных
        dataTest();

        //Подготовка ожидаемого результата
        when(cutMock.getAll()).thenReturn(dataTest());
        int expectedResult = Math.max(dataTest().get(1).getSalary(), dataTest().get(0).getSalary());

        //Начало теста
        int actualResult = cut.maxSalary(dataTest().get(0).getOffice());
        assertEquals(expectedResult, actualResult);
        assertNotEquals(dataTest().get(0).getOffice(), dataTest().get(2).getOffice());
        verify(cutMock).getAll();
        verifyNoMoreInteractions(cutMock);
    }

    @Test
    void minSalary() {
        //Подготовка входных данных
        dataTest();

        //Подготовка ожидаемого результата
        when(cutMock.getAll()).thenReturn(dataTest());
        int expectedResult = Math.min(dataTest().get(1).getSalary(), dataTest().get(0).getSalary());

        //Начало теста
        int actualResult = cut.minSalary(dataTest().get(0).getOffice());
        assertEquals(expectedResult, actualResult);
        assertNotEquals(dataTest().get(0).getOffice(), dataTest().get(2).getOffice());
        verify(cutMock).getAll();
        verifyNoMoreInteractions(cutMock);

    }

    @Test
    void officeSalary() {
       //Подготовка входных данных
        dataTest();

        //Подготовка ожидаемого результата
        when(cutMock.getAll()).thenReturn(dataTest());
        String expectedResult = "Сумма расходов составляет " + (dataTest().get(1).getSalary() + dataTest().get(0).getSalary()) + " рублей";

        //Начало теста
        String actualResult = cut.officeSalary(dataTest().get(0).getOffice());
        assertEquals(expectedResult, actualResult);
        assertNotEquals(dataTest().get(0).getOffice(), dataTest().get(2).getOffice());
        verify(cutMock).getAll();
        verifyNoMoreInteractions(cutMock);
    }

    @Test
    void officeUsers() {
        //Подготовка входных данных
        dataTest();

        //Подготовка ожидаемого результата
        when(cutMock.getAll()).thenReturn(dataTest());
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(dataTest().get(0));
        expectedResult.add(dataTest().get(1));

        //Начало теста
        List<Employee> actualResult = cut.officeUsers(dataTest().get(0).getOffice());
        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
        verify(cutMock).getAll();
        verifyNoMoreInteractions(cutMock);
    }

    @Test
    void allUsers() {
        //Подготовка входных данных
        dataTest();

        //Подготовка ожидаемого результата
        when(cutMock.getAll()).thenReturn(dataTest());
        Map<Integer,List<Employee>> expectedResult = dataTest().stream()
                .collect(Collectors.groupingBy(Employee::getOffice, Collectors.toList()));

        //Начало теста
        Map<Integer,List<Employee>> actualResult = cut.allUsers();
        assertEquals(expectedResult, actualResult);
        verify(cutMock).getAll();
        verifyNoMoreInteractions(cutMock);
    }
}