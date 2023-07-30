package ru.byCooper.employeeProject;

import org.junit.jupiter.api.Test;
import ru.byCooper.employeeProject.exception.EmployeeAlreadyAddedException;
import ru.byCooper.employeeProject.exception.EmployeeNotFoundException;
import ru.byCooper.employeeProject.exception.EmployeeStorageIsFullException;
import ru.byCooper.employeeProject.model.Employee;
import ru.byCooper.employeeProject.service.EmployeeServiceImpl;

import java.text.DecimalFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl cut = new EmployeeServiceImpl();

    @Test
    void addPersonSuccess() {
        //Подготовка входных данных
        Employee actual = cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        //Подготовка ожидаемого результата
        Employee expected = new Employee("Igor", "Smirnov", "Sanich", 3, 180000);
        //Начало теста
        assertEquals(expected, actual);
    }

    @Test
    void addPersonDublicate() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        Exception exception = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        });
        //Подготовка ожидаемого результата
        String expectedMessage = "Данный сотрудник уже существует";
        //Начало теста
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addPersonOverflow() {
        //Подготовка входных данных
        cut.addPerson("Igorq", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igore", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igort", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igoru", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 3, 180000);
        Exception exception = assertThrows(EmployeeStorageIsFullException.class, () -> {
            cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        });
        //Подготовка ожидаемого результата
        String expectedMessage = "Нет свободных позиций";
        //Начало теста
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void deletePersonNotFound() {
        //Подготовка входных данных
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            cut.deletePerson("Igor", "Smirnov");
        });
        //Подготовка ожидаемого результата
        String expectedMessage = "Сотрудник не найден";
        //Начало теста
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void deletePersonSuccess() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        String actualMessage = cut.deletePerson("Igor", "Smirnov");
        //Подготовка ожидаемого результата
        String expectedMessage = "Сотрудник " + "Smirnov " + "Igor" + " удален";
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void searchPersonNotFound() {
        //Подготовка входных данных
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            cut.searchPerson("Igor", "Smirnov");
        });
        //Подготовка ожидаемого результата
        String expectedMessage = "Сотрудник не найден";
        //Начало теста
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void searchPersonSuccess() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        Employee actual = cut.searchPerson("Igor", "Smirnov");
        //Подготовка ожидаемого результата
        Employee expected = new Employee("Igor", "Smirnov", "Sanich", 3, 180000);
        //Начало теста
        assertEquals(expected, actual);
    }

    @Test
    void showAllPersons() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        Collection<Employee> actual = cut.showAllPersons();
        //Подготовка ожидаемого результата
        Map<String, Employee> expectedList = new HashMap<>();
        expectedList.put("Smirnov Igor", new Employee("Igor", "Smirnov", "Sanich", 3, 180000));
        expectedList.put("Egorov Ivan" ,new Employee("Ivan", "Egorov", "Petrovich", 1, 165000));
        Collection<Employee> expected = expectedList.values();
        //Начало теста
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void allSalary() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        String actualMessage = cut.allSalary();
        //Подготовка ожидаемого результата
        String expectedMessage = "Ежемесячные расходы на зарплату сотрудников предприятия составляет " + (180000 + 165000) + " RUB";
        //Начало теста
        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void minSalary() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        String actualMessage = cut.minSalary();
        //Подготовка ожидаемого результата
        Employee testPerson = new Employee("Ivan", "Egorov", "Petrovich", 1, 165000);
        String expectedMessage = "Минимальная зарплата по организации: " + testPerson.getSalary() + " RUB" + System.lineSeparator() + testPerson;
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void maxSalary() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        String actualMessage = cut.maxSalary();
        //Подготовка ожидаемого результата
        Employee testPerson = new Employee("Igor", "Smirnov", "Sanich", 3, 180000);
        String expectedMessage = "Максимальная зарплата по организации: " + testPerson.getSalary() + " RUB" + System.lineSeparator() + testPerson;
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void averageSalary() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        cut.addPerson("Jack", "Sparow", "Krakenovich", 1, 198000);
        String actualMessage = cut.averageSalary();
        //Подготовка ожидаемого результата
        String expectedMessage = "Среднее занчение зарплат равно " + new DecimalFormat("###,###.##").format((180000.0 + 165000.0 + 198000.0) / 3) + " RUB";
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void indexSalary() {
        //Подготовка входных данных
        cut.addPerson("Jack", "Sparow", "Krakenovich", 1, 198000);
        String actualMessage = cut.indexSalary(9);
        int actualSalary = cut.searchPerson("Jack","Sparow").getSalary();
        //Подготовка ожидаемого результата
        String expectedMessage = "Повышение зарплаты на " + 9 + " % произведено";
        int expectedSalary = 198000 + ((198000 * 9) / 100);
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void getMinimalSalaryInOffice() {
        //Подготовка входных данных
        cut.addPerson("Igorq", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 170000);
        cut.addPerson("Igore", "Smirnov", "Sanich", 1, 160000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 1, 140000);
        cut.addPerson("Igort", "Smirnov", "Sanich", 2, 110000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 2, 130000);
        cut.addPerson("Igoru", "Smirnov", "Sanich", 4, 185000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 4, 187000);
        String actualMessage = cut.getMinimalSalaryInOffice(1);
        //Подготовка ожидаемого результата
        Employee testPerson = new Employee("Igorr", "Smirnov", "Sanich", 1, 140000);
        String expectedMessage = "Минимальная зарплата в " + 1 + " отделе: " + 140000 + " RUB" + testPerson;
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getMaximalSalaryInOffice() {
        //Подготовка входных данных
        cut.addPerson("Igorq", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 170000);
        cut.addPerson("Igore", "Smirnov", "Sanich", 1, 160000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 1, 140000);
        cut.addPerson("Igort", "Smirnov", "Sanich", 2, 110000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 2, 130000);
        cut.addPerson("Igoru", "Smirnov", "Sanich", 4, 185000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 4, 187000);
        String actualMessage = cut.getMaximalSalaryInOffice(1);
        //Подготовка ожидаемого результата
        Employee testPerson = new Employee("Igore", "Smirnov", "Sanich", 1, 160000);
        String expectedMessage = "Максимальная зарплата в " + 1 + " отделе: " + 160000 + " RUB" + testPerson;
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getAllSalaryOffice() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        cut.addPerson("Jack", "Sparow", "Krakenovich", 1, 198000);
        String actualMessage = cut.getAllSalaryOffice(1);
        //Подготовка ожидаемого результата
        String expectedMessage = "Сумма затрат на зарплату по отделу № " + 1 + " составляет " + (165000 + 198000) + " RUB";
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getAverageSalaryOffice() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        cut.addPerson("Jack", "Sparow", "Krakenovich", 1, 198000);
        String actualMessage = cut.getAverageSalaryOffice(1);
        //Подготовка ожидаемого результата
        String expectedMessage = "Средняя зарплата по отделу № " + 1 + " составляет " + new DecimalFormat("###,###.##").format((165000.0 + 198000.0) / 2) + " RUB";
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getIndexSalaryOffice() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        String actualMessage = cut.getIndexSalaryOffice(1, 8);
        int actualResult = cut.searchPerson("Ivan", "Egorov").getSalary();
        //Подготовка ожидаемого результата
        String expectedMessage = "Зарплата сотрудников " + 1 + " отдела, после индексации на " + 8 + " %";
        int expectedResult = 165000 + ((165000 * 8) / 100);
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAllEmployeeOffice() {
        //Подготовка входных данных
        cut.addPerson("Igor", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Ivan", "Egorov", "Petrovich", 1, 165000);
        cut.addPerson("Jack", "Sparow", "Krakenovich", 1, 198000);
        List<Employee> actualResult = cut.getAllEmployeeOffice(1);
        //Подготовка ожидаемого результата
        List<Employee> test = new ArrayList<>();
        test.add(new Employee("Ivan", "Egorov", "Petrovich", 1, 165000));
        test.add(new Employee("Jack", "Sparow", "Krakenovich", 1, 198000));
        //Начало теста
        assertArrayEquals(test.toArray(), actualResult.toArray());
    }

    @Test
    void sourceSalaryOfEmployeeLow() {
        //Подготовка входных данных
        cut.addPerson("Igorq", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 170000);
        cut.addPerson("Igore", "Smirnov", "Sanich", 1, 160000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 1, 140000);
        cut.addPerson("Igort", "Smirnov", "Sanich", 2, 110000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 2, 130000);
        cut.addPerson("Igoru", "Smirnov", "Sanich", 4, 185000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 4, 187000);
        List<Employee> actualResult = cut.sourceSalaryOfEmployeeLow(160000);
        //Подготовка ожидаемого результата
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(new Employee("Igory", "Smirnov", "Sanich", 1, 140000));
        expectedResult.add(new Employee("Igorr", "Smirnov", "Sanich", 2, 110000));
        expectedResult.add(new Employee("Igort", "Smirnov", "Sanich", 2, 130000));
        //Начало теста
        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @Test
    void sourceSalaryOfEmployeeHigh() {
        //Подготовка входных данных
        cut.addPerson("Igorq", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 170000);
        cut.addPerson("Igore", "Smirnov", "Sanich", 1, 160000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 1, 140000);
        cut.addPerson("Igort", "Smirnov", "Sanich", 2, 110000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 2, 130000);
        cut.addPerson("Igoru", "Smirnov", "Sanich", 4, 185000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 4, 187000);
        List<Employee> actualResult = cut.sourceSalaryOfEmployeeHigh(170000);
        //Подготовка ожидаемого результата
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(new Employee("Igori", "Smirnov", "Sanich", 3, 180000));
        expectedResult.add(new Employee("Igorq", "Smirnov", "Sanich", 4, 185000));
        expectedResult.add(new Employee("Igoru", "Smirnov", "Sanich", 4, 187000));
        //Начало теста
        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @Test
    void changeSalaryNotFound() {
        //Подготовка входных данных
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            cut.changeSalary("Jack", "Sparow", 50000);
        });
        //Подготовка ожидаемого результата
        String expectedMessage = "Сотрудник не найден или некоректно введены фамилия или имя";
        //Начало теста
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void changeSalarySuccess() {
        //Подготовка входных данных
        cut.addPerson("Jack", "Sparow", "Krakenovich", 1, 198000);
        String actualMessage = cut.changeSalary("Jack", "Sparow", 210000);
        //Подготовка ожидаемого результата
        String person = "Sparow Jack";
        String expectedMessage = "Установлена заработная плата для " + person + " в размере " + 210000 + " RUB";
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void changeOfficeSuccess() {
        //Подготовка входных данных
        cut.addPerson("Jack", "Sparow", "Krakenovich", 1, 198000);
        String actualMessage = cut.changeOffice("Jack", "Sparow", 7);
        //Подготовка ожидаемого результата
        String person = "Sparow Jack";
        String expectedMessage = "Сотрудник " + person + " переведен в " + 7 + " отдел";
        //Начало теста
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    void changeOfficeNotFound() {
        //Подготовка входных данных
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            cut.changeOffice("Jack", "Sparow", 7);
                });
        //Подготовка ожидаемого результата
        String expectedMessage = "Сотрудник не найден или некоректно введены фамилия или имя";
        //Начало теста
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void printEmployeeWithOffice() {
        //Подготовка входных данных
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 170000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 1, 140000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 2, 130000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 4, 187000);
        List<Employee> actualResult = cut.printEmployeeWithOffice();
        //Подготовка ожидаемого результата
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(new Employee("Igorr", "Smirnov", "Sanich", 1, 140000));
        expectedResult.add(new Employee("Igory", "Smirnov", "Sanich", 2, 130000));
        expectedResult.add(new Employee("Igorw", "Smirnov", "Sanich", 3, 170000));
        expectedResult.add(new Employee("Igori", "Smirnov", "Sanich", 4, 187000));
        //Начало теста
        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @Test
    void printAllInOffice() {
        //Подготовка входных данных
        cut.addPerson("Igorq", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 170000);
        cut.addPerson("Igore", "Smirnov", "Sanich", 1, 160000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 1, 140000);
        cut.addPerson("Igort", "Smirnov", "Sanich", 2, 110000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 2, 130000);
        cut.addPerson("Igoru", "Smirnov", "Sanich", 4, 185000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 4, 187000);
        List<Employee> actualResult = cut.printAllInOffice(2);
        //Подготовка ожидаемого результата
        List<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(new Employee("Igory", "Smirnov", "Sanich", 2, 130000));
        expectedResult.add(new Employee("Igort", "Smirnov", "Sanich", 2, 110000));
        //Начало теста
        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }

    @Test
    void getAll() {
        //Подготовка входных данных
        cut.addPerson("Igorq", "Smirnov", "Sanich", 3, 180000);
        cut.addPerson("Igorw", "Smirnov", "Sanich", 3, 170000);
        cut.addPerson("Igore", "Smirnov", "Sanich", 1, 160000);
        cut.addPerson("Igorr", "Smirnov", "Sanich", 1, 140000);
        cut.addPerson("Igort", "Smirnov", "Sanich", 2, 110000);
        cut.addPerson("Igory", "Smirnov", "Sanich", 2, 130000);
        cut.addPerson("Igoru", "Smirnov", "Sanich", 4, 185000);
        cut.addPerson("Igori", "Smirnov", "Sanich", 4, 187000);
        Collection<Employee> actualResult = cut.getAll();
        //Подготовка ожидаемого результата
        Collection<Employee> expectedResult = new ArrayList<>();
        expectedResult.add(new Employee("Igorw", "Smirnov", "Sanich", 3, 180000));
        expectedResult.add(new Employee("Igory", "Smirnov", "Sanich", 3, 170000));
        expectedResult.add(new Employee("Igori", "Smirnov", "Sanich", 1, 160000));
        expectedResult.add(new Employee("Igorr", "Smirnov", "Sanich", 1, 140000));
        expectedResult.add(new Employee("Igorq", "Smirnov", "Sanich", 2, 110000));
        expectedResult.add(new Employee("Igort", "Smirnov", "Sanich", 2, 130000));
        expectedResult.add(new Employee("Igore", "Smirnov", "Sanich", 4, 185000));
        expectedResult.add(new Employee("Igoru", "Smirnov", "Sanich", 4, 187000));
        //Начало теста
        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());
    }
}