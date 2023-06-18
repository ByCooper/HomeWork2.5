package ru.byCooper.employeeProject;

import org.springframework.stereotype.Service;
import ru.byCooper.employeeProject.exception.EmployeeAlreadyAddedException;
import ru.byCooper.employeeProject.exception.EmployeeNotFoundException;
import ru.byCooper.employeeProject.exception.EmployeeStorageIsFullException;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final int maxEmployee = 8;
    private final Map<String, Employee> employee = new HashMap<>();

    @Override
    public Employee addPerson(String name, String lastName, String middleName, int office, int salary) {
        //Добавление сотрудника
        Employee person = new Employee(name, lastName, middleName, office, salary);
        if (employee.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Нет свободных позиций");
        } else if (employee.containsKey(person.getLastName() + " " + person.getFirstName())) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже существует");
        } else {
            employee.put(person.getLastName() + " " + person.getFirstName(), person);
            return employee.get(person.getLastName() + " " + person.getFirstName());
        }
    }

    @Override
    public String deletePerson(String name, String lastName) {
        //Удаление сотрудника
        String person = lastName + " " + name;
        if (!employee.containsKey(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            employee.remove(person);
            return "Сотрудник " + person + " удален";
        }
    }

    @Override
    public Employee searchPerson(String name, String lastName) {
        //Поиск сотрудника
        String person = lastName + " " + name;
        if (!employee.containsKey(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return employee.get(person);
        }
    }

    @Override
    public Collection<Employee> showAllPersons() {
        //Отображение всех сотрудников
        return employee.values();
    }

    @Override
    public String allSalary() {
        //Отображение общей суммы зарплат по организации
        int sum = 0;
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            sum += item.getValue().getSalary();
        }
        return "Ежемесячные расходы на зарплату сотрудников предприятия составляет " + sum + " RUB";
    }

    @Override
    public String minSalary() {
        //Отображение минимальной зарплаты по организации
        Set<Integer> salary = new TreeSet<>();
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            salary.add(item.getValue().getSalary());
        }
        List<Integer> min = new ArrayList<>(salary);
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getSalary() == min.get(0)) {
                return "Минимальная зарплата по организации: " + min.get(0) + " RUB" + System.lineSeparator() + item.getValue().toString();
            }
        }
        return null;
    }

    @Override
    public String maxSalary() {
        //Отображение максимальной зарплаты по организации
        Set<Integer> salary = new TreeSet<>();
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            salary.add(item.getValue().getSalary());
        }
        List<Integer> max = new ArrayList<>(salary);
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getSalary() == max.get(max.size() - 1)) {
                return "Максимальная зарплата по организации: " + (max.get(max.size() - 1)) + " RUB" + System.lineSeparator()+ item.getValue();
            }
        }
        return null;
    }

    @Override
    public String averageSalary() {
        //Отображение средней зарплаты по организации
        int sum = 0;
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            sum += item.getValue().getSalary();
        }
        double average = (double) sum / employee.size();
        return "Среднее занчение зарплат равно " + new DecimalFormat("###,###.##").format(average) + " RUB";
    }

    @Override
    public String indexSalary(int percent) {
        //Индексация зарплаты по всей организации на %
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            item.getValue().setSalary(item.getValue().getSalary() + (item.getValue().getSalary() * percent) / 100);
        }
        return "Повышение зарплаты на " + percent + " % произведено";
    }

    @Override
    public String getMinimalSalaryInOffice(int office) {
        //Минимальная зарплата по отделу
        Set<Integer> salaryOffice = new TreeSet<>();
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getOffice() == office) {
                salaryOffice.add(item.getValue().getSalary());
            }
        }
        List<Integer> min = new ArrayList<>(salaryOffice);
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getSalary() == min.get(0)) {
                return "Минимальная зарплата в " + office + " отделе: " + min.get(0) + " RUB" + item.getValue().toString();
            }
        }
        return null;
    }

    @Override
    public String getMaximalSalaryInOffice(int office) {
        //Максимальная зарплата по отделу
        Set<Integer> salaryOffice = new TreeSet<>();
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getOffice() == office) {
                salaryOffice.add(item.getValue().getSalary());
            }
        }
        List<Integer> max = new ArrayList<>(salaryOffice);
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getSalary() == max.get(max.size() - 1)) {
               return "Максимальная зарплата в " + office + " отделе: " + max.get(max.size() - 1) + " RUB" + item.getValue().toString();
            }
        }
        return null;
    }

    @Override
    public String getAllSalaryOffice(int office) {
        //Общая сумма зарплат за отдел
        int sum = 0;
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getOffice() == office) {
                sum += item.getValue().getSalary();
            }
        }
        return "Сумма затрат на зарплату по отделу № " + office + " составляет " + sum + " RUB";
    }

    @Override
    public String getAverageSalaryOffice(int office) {
        //Средняя зарплата по отделу
        int sum = 0;
        int k = 0;
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getOffice() == office) {
                sum += item.getValue().getSalary();
                ++k;
            }
        }
        double averageSalaryOffice = (double) sum / k;
        return "Средняя зарплата по отделу № " + office + " составляет " + new DecimalFormat("###,###.##").format(averageSalaryOffice) + " RUB";
    }

    @Override
    public String getIndexSalaryOffice(int office, int percent) {
        //Индексация зарплаты в отделе
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getOffice() == office) {
                item.getValue().setSalary(item.getValue().getSalary() + (item.getValue().getSalary() * percent) / 100);
            }
        }
        return "Зарплата сотрудников " + office + " отдела, после индексации на " + percent + " %";
    }

    @Override
    public List<Employee> getAllEmployeeOffice(int office) {
        //Отображение сотрудников отдела
        System.out.println("Сотрудники " + office + " отдела:");
        return printAllInOffice(office);
    }

    @Override
    public List<Employee> sourceSalaryOfEmployeeLow(int salary) {
        //Отображение сотрудников, чья зарплата меньше --- рублей
        System.out.println("Сотрудники, чья зарплата меньше " + salary + " рублей");
        List<Employee> persons = new ArrayList<>();
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getSalary() < salary) {
                persons.add(item.getValue());
            }
        }
        return persons;
    }

    @Override
    public List<Employee> sourceSalaryOfEmployeeHigh(int salary) {
        //Отображение сотрудников, чья зарплата больше --- рублей
        System.out.println("Сотрудники, чья зарплата больше " + salary + " рублей");
        List<Employee> persons = new ArrayList<>();
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getSalary() > salary) {
                persons.add(item.getValue());
            }
        }
        return persons;
    }

    @Override
    public String changeSalary(String name, String lastName, int salary) {
        //Изменение зарплаты отдельного сотрудника
        String person = lastName + " " + name;
        if (!employee.containsKey(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден или некоректно введены фамилия или имя");
        }
        employee.get(person).setSalary(salary);
        return "Установлена заработная плата для " + person + " в размере " + salary + " RUB";
    }

    @Override
    public String changeOffice(String name, String lastName, int office) {
        //Изменение отдела отдельного сотрудника
        String person = lastName + " " + name;
        if (!employee.containsKey(person)) {
            throw new EmployeeNotFoundException("Сотрудник не найден или некоректно введены фамилия или имя");
        }
        employee.get(person).setOffice(office);
        return "Сотрудник " + person + " переведен в " + office + " отдел";
    }

    @Override
    public List<Employee> printEmployeeWithOffice() {
        //Получение сотрудников по отделам
        List<Employee> persons = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            for (Map.Entry<String, Employee> item : employee.entrySet()) {
                if (item.getValue().getOffice() == i) {
                    persons.add(item.getValue());
                }
            }
        }
        return persons;
    }
    @Override
    public List<Employee> printAllInOffice(int office) {
        List<Employee> persons = new ArrayList<>();
        for (Map.Entry<String, Employee> item : employee.entrySet()) {
            if (item.getValue().getOffice() == office) {
                persons.add(item.getValue());
            }
        }
        return persons;
    }
}
