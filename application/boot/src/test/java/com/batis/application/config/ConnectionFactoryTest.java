package com.batis.application.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class ConnectionFactoryTest {
    private Connection connection;

    @Test
    void testGetDatabaseConnection() {
    }

    @BeforeEach
    void setUp() throws SQLException {
//        connection = ConnectionFactory.getDatabaseConnection();
    }

    @AfterEach
    void tearDown() throws SQLException {
//        connection.close();
    }
}