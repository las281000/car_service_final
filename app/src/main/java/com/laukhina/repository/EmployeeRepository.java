package com.laukhina.repository;

import java.util.List;

import com.laukhina.entity.Employee;

/*
 * Инстерфейс репозитория для сущности Сотрудник (Employee)
 * Здесь представлены методы, специфичные именно для Сотрудников
 */
public interface EmployeeRepository extends Repository<Employee> {

    //поиск сотрубника по его телефону
    public Employee findByPhone(String phone);

    //получение списка всех сотрудников
    public List<Employee> getAllEmployees();
}
