package com.laukhina.service;

import java.util.List;
import java.util.Random;

import com.laukhina.entity.Employee;
import com.laukhina.repository.EmployeeRepository;
import com.laukhina.request.EmployeeRequest;

public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee getOrCreate(EmployeeRequest request) {
        Employee employee = repository.findByPhone(request.getPhone());

        if (employee == null) { // если нет номера в базе, значит клиент пришел впервые
            employee = Employee.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .phone(request.getPhone())
                .build();
            employee = repository.save(employee); // сохраняем в базу
        }        
        return employee; // тут либо только что зарегистрированнй Клиент, либо старый из базы
    }

    //выбор рандомного сотрудника
    public Employee chooseEmployee(){
       List<Employee> employeeList = repository.getAllEmployees();
       int emplIndex =  new Random().nextInt(0, employeeList.size());

       return employeeList.get(emplIndex);
    }

}
