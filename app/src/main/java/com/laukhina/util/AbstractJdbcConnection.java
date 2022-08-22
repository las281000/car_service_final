package com.laukhina.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractJdbcConnection {
    protected Connection connection;

    public PreparedStatement prepareRequest(String sql) {
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return request;
    }

    public void closeRequest(PreparedStatement request) {
        if (request != null) {
            try {
                request.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}