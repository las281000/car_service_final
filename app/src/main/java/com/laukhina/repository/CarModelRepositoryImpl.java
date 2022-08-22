package com.laukhina.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.laukhina.entity.CarModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarModelRepositoryImpl implements CarModelRepository {
    private final Connection connection;

    private final static String SAVE_QUERRY = "INSERT INTO car_model(name, vendor, country) VALUES(?, ?, ?)";
    private final static String FIND_BY_ID_QUERRY = "SELECT * FROM car_model WHERE id = ?";
    private final static String FIND_BY_NAME_QUERRY = "SELECT * FROM car_model WHERE name = ?";

    @Override
    public CarModel mapResult(ResultSet result) {
        try {
            return CarModel.builder()
                    .id(result.getLong("id"))
                    .name(result.getString("name"))
                    .vendor(result.getString("vendor"))
                    .country(result.getString("country"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CarModel save(CarModel model) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            // подготавливаем запрос к использованию в базе
            querry = connection.prepareStatement(SAVE_QUERRY);

            // заполняем пораметры модели
            querry.setString(1, model.getName());
            querry.setString(2, model.getVendor());
            querry.setString(3, model.getCountry());

            querry.executeUpdate();// выполняем запрос
            result = querry.getGeneratedKeys(); // получаем ключи, сгенерированные при выполнении запроса

            if (result.next()) {
                long id = result.getLong(1); // вставленный ключ (id модели)
                model.setId(id);
                return model;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CarModel findById(long id) {
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
    

    @Override
    public CarModel findByName(String name) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            querry = connection.prepareStatement(FIND_BY_NAME_QUERRY); // подготавливаем запрос к использованию в базе
            querry.setString(1, name); // заполняем name модели

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
