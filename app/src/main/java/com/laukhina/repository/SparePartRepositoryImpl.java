package com.laukhina.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.laukhina.entity.SparePart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SparePartRepositoryImpl implements SparePartRepository {
    private final Connection connection;

    private final static String SAVE_QUERRY = "INSERT INTO spare_part(type, serial_num, manufacturer, car_model_id, is_original) VALUES(?, ?, ?, ?, ?)";
    private final static String FIND_BY_ID_QUERRY = "SELECT * FROM operation WHERE id = ?";

    @Override
    public SparePart mapResult(ResultSet result) {
        try {
            return SparePart.builder()
                    .id(result.getLong("id"))
                    .type(result.getString("type"))
                    .serialNum(result.getString("serial_num"))
                    .manufacturer(result.getString("manufacturer"))
                    .carModelId(result.getLong("car_model_id"))
                    .isOriginal(result.getBoolean("isOriginal"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SparePart save(SparePart part) {
        PreparedStatement querry = null;
        ResultSet result = null;
        try {
            // подготавливаем запрос к использованию в базе
            querry = connection.prepareStatement(SAVE_QUERRY, Statement.RETURN_GENERATED_KEYS);

            // заполняем пораметры модели
            querry.setString(1, part.getType());
            querry.setString(2, part.getSerialNum());
            querry.setString(3, part.getManufacturer());
            querry.setLong(4, part.getCarModelId());
            querry.setBoolean(5, part.isOriginal());

            querry.executeUpdate();// выполняем запрос
            result = querry.getGeneratedKeys(); // получаем ключи, сгенерированные при выполнении запроса

            if (result.next()) {
                long id = result.getLong(1); // вставленный ключ (id модели)
                part.setId(id);
                return part;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SparePart findById(long id) {
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
