package com.laukhina.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.laukhina.entity.Car;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {
    private final Connection connection;

    private final String SAVE_QUERRY = "INSERT INTO car(owner_id, model_id, num, vin, engine_capacity) VALUES(?, ?, ?, ?, ?)";
    private final String FIND_BY_ID_QUERRY = "SELECT * FROM car WHERE id = ?";
    private final String FIND_BY_NUM_QUERRY = "SELECT * FROM car WHERE num = ?";
    private final String FIND_BY_VIN_QUERRY = "SELECT * FROM car WHERE vin = ?";

    @Override
    public Car mapResult(ResultSet result) {
        try {
            return Car.builder()
                    .id(result.getLong("id"))
                    .ownerId(result.getLong("owner_id"))
                    .modelId(result.getLong("model_id"))
                    .num(result.getString("num"))
                    .vin(result.getString("vin"))
                    .engineCapacity(result.getInt("engine_capacity"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Car save(Car car) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            // подготавливаем запрос к использованию в базе
            querry = connection.prepareStatement(SAVE_QUERRY);

            // заполняем пораметры модели
            querry.setLong(1, car.getOwnerId());
            querry.setLong(2, car.getModelId());
            querry.setString(3, car.getNum());
            querry.setString(4, car.getVin());
            querry.setInt(5, car.getEngineCapacity());

            querry.executeUpdate();// выполняем запрос
            result = querry.getGeneratedKeys(); // получаем ключи, сгенерированные при выполнении запроса

            if (result.next()) {
                long id = result.getLong(1); // вставленный ключ (id модели)
                car.setId(id);
                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car findById(long id) {
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

    public Car findByNum(String num) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            querry = connection.prepareStatement(FIND_BY_NUM_QUERRY); // подготавливаем запрос к использованию в базе
            querry.setString(1, num); // заполняем name модели

            result = querry.executeQuery();// выполняем запрос

            if (result.next()) {
                return mapResult(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Car findByVin(String vin) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            querry = connection.prepareStatement(FIND_BY_VIN_QUERRY); // подготавливаем запрос к использованию в базе
            querry.setString(1, vin); // заполняем name модели

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
