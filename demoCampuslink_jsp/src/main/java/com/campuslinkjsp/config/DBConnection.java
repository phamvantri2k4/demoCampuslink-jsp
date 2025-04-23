package com.campuslinkjsp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());
    private static final String URL = "jdbc:mysql://localhost:3306/qltruonghoc";
    private static final String USER = "root";
    private static final String PASSWORD = "251204";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Kết nối tới cơ sở dữ liệu thành công.");
            return conn;
        } catch (ClassNotFoundException e) {
            logger.severe("Không tìm thấy MySQL JDBC Driver: " + e.getMessage());
            throw new SQLException("Không tìm thấy MySQL JDBC Driver", e);
        } catch (SQLException e) {
            logger.severe("Lỗi khi kết nối tới cơ sở dữ liệu: " + e.getMessage());
            throw e;
        }
    }
}