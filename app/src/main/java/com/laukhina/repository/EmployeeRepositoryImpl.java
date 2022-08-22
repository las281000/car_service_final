package com.laukhina.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laukhina.entity.Employee;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final Connection connection;

    private final static String SAVE_QUERRY = "INSERT INTO employee(name, surname, phone) VALUES(?, ?, ?)";
    private final static String FIND_BY_ID_QUERRY = "SELECT * FROM employee WHERE id = ?";
    private final static String FIND_BY_PHONE_QUERRY = "SELECT * FROM employee WHERE phone = ?";
    private final static String GET_ALL_QUERRY = "SELECT * FROM employee";

    @Override
    public Employee mapResult(ResultSet result) {
        try {
            return Employee.builder()
                    .id(result.getLong("id"))
                    .name(result.getString("name"))
                    .surname(result.getString("surname"))
                    .phone(result.getString("phone"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee save(Employee employee) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            // подготавливаем запрос к использованию в базе
            querry = connection.prepareStatement(SAVE_QUERRY);

            // заполняем пораметры модели
            querry.setString(1, employee.getName());
            querry.setString(2, employee.getSurname());
            querry.setString(3, employee.getPhone());

            querry.executeUpdate();// выполняем запрос
            result = querry.getGeneratedKeys(); // получаем ключи, сгенерированные при выполнении запроса

            if (result.next()) {
                long id = result.getLong(1); // вставленный ключ (id модели)
                employee.setId(id);
                return employee;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee findById(long id) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            querry = connection.prepareStatement(FIND_BY_ID_QUERRY); // подготавливаем запрос к использованию в базе
            querry.setLong(1, id); // заполняем id модели

            result = querry.executeQuery();// выполняем запрос

            if (result.next()) {
                return mapResult(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee findByPhone(String phone) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            querry = connection.prepareStatement(FIND_BY_PHONE_QUERRY); // подготавливаем запрос к использованию в базе
            querry.setString(1, phone); // заполняем name модели

            result = querry.executeQuery();// выполняем запрос

            if (result.next()) {
                return mapResult(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        PreparedStatement querry = null;
        ResultSet result = null;

        List<Employee> list = new ArrayList<>();
        try {
            querry = connection.prepareStatement(GET_ALL_QUERRY); // подготавливаем запрос к использованию в базe
            result = querry.executeQuery();// выполняем запрос

            while (result.next()) {
                list.add(mapResult(result));
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
