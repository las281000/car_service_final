package com.laukhina.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.laukhina.entity.Customer;
import lombok.RequiredArgsConstructor;

/*
 * Репозиторий предназначен для операций с БД с сущностью Клиента
 */
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final Connection connection;

    private final static String SAVE_QUERRY = "INSERT INTO customer(name, surname, phone) VALUES(?, ?, ?)";
    private final static String FIND_BY_ID_QUERRY = "SELECT * FROM customer WHERE id = ?";
    private final static String FIND_BY_PHONE_QUERRY = "SELECT * FROM customer WHERE phone = ?";

    @Override
    public Customer mapResult(ResultSet result) {
        try {
            return Customer.builder()
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
    public Customer save(Customer customer) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            // подготавливаем запрос к использованию в базе
            querry = connection.prepareStatement(SAVE_QUERRY);

            // заполняем пораметры модели
            querry.setString(1, customer.getName());
            querry.setString(2, customer.getSurname());
            querry.setString(3, customer.getPhone());

            querry.executeUpdate();// выполняем запрос
            result = querry.getGeneratedKeys(); // получаем ключи, сгенерированные при выполнении запроса

            if (result.next()) {
                long id = result.getLong(1); // вставленный ключ (id модели)
                customer.setId(id);
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer findById(long id) {
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

    public Customer findByPhone(String phone) {
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
}
