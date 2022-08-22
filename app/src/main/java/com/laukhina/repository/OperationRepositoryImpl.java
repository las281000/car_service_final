package com.laukhina.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.laukhina.entity.Operation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OperationRepositoryImpl implements OperationRepository {

    private final Connection connection;

    private final static String SAVE_QUERRY = "INSERT INTO operation(employee_id, car_id, part_id, date, price, description) VALUES(?, ?, ?, ?, ?, ?)";
    private final static String FIND_BY_ID_QUERRY = "SELECT * FROM operation WHERE id = ?";

    @Override
    public Operation mapResult(ResultSet result) {
        try {
            return Operation.builder()
                    .id(result.getLong("id"))
                    .employeeId(result.getLong("employee_id"))
                    .carId(result.getLong("car_id"))
                    .partId(result.getLong("part_id"))
                    .date(result.getTimestamp("date").toInstant())
                    .price(result.getInt("price"))
                    .description(result.getString("description"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Operation save(Operation operation) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            // подготавливаем запрос к использованию в базе
            querry = connection.prepareStatement(SAVE_QUERRY, Statement.RETURN_GENERATED_KEYS);

            // заполняем пораметры модели
            querry.setLong(1, operation.getEmployeeId());
            querry.setLong(2, operation.getCarId());
            querry.setLong(3, operation.getPartId());
            querry.setTimestamp(4, Timestamp.from(operation.getDate()));
            querry.setInt(5, operation.getPrice());
            querry.setString(6, operation.getDescription());

            querry.executeUpdate();// выполняем запрос
            result = querry.getGeneratedKeys(); // получаем ключи, сгенерированные при выполнении запроса

            if (result.next()) {
                long id = result.getLong(1); // вставленный ключ (id модели)
                operation.setId(id);
                return operation;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Operation findById(long id) {
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
}
