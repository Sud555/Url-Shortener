package com.example.urlshortener.dao;

import java.sql.*;

public class UrlDao {
    private final Connection conn;

    public UrlDao(Connection conn) {
        this.conn = conn;
    }

    public void save(String originalUrl, String shortCode) throws SQLException {
        String sql = "INSERT INTO urls (original_url, short_code) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, originalUrl);
            stmt.setString(2, shortCode);
            stmt.executeUpdate();
        }
    }

    public String findOriginalByShort(String shortCode) throws SQLException {
        String sql = "SELECT original_url FROM urls WHERE short_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shortCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("original_url");
            }
        }
        return null;
    }
}
