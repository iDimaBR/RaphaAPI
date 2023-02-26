package com.github.idimabr.storage;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class SQLExecutor  {

    private final Connection connection;

    @SneakyThrows
    public List<List<Object>> select(String sql, Object... args) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                final List<List<Object>> list = new ArrayList<>();
                int columns = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    final List<Object> cols = new ArrayList<>();
                    for (int i = 1; i <= columns; i++) {
                        cols.add(rs.getObject(i));
                    }
                    list.add(cols);
                }
                return list;
            }
        }
    }

    @SneakyThrows
    public <T> List<T> select(Class<T> clazz, String sql, Object... args) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                final List<T> list = new ArrayList<>();
                final ResultSetMetaData meta = rs.getMetaData();
                final List<String> columns = new ArrayList<>();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    columns.add(meta.getColumnLabel(i));
                }
                while (rs.next()) {
                    list.add(instanceOf(clazz, columns, rs));
                }
                return list;
            }
        }
    }

    @SneakyThrows
    public int update(String sql, Object... args) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        }
    }

    @SneakyThrows
    public int delete(String sql, Object... args) throws SQLException {
        return update(sql, args);
    }

    private <T> T instanceOf(Class<T> clazz, List<String> columns, ResultSet rs) {
        try {
            T bean = clazz.newInstance();
            int index = 0;
            for (String column : columns) {
                index++;
                Field f = clazz.getField(column);
                f.set(bean, rs.getObject(index, f.getType()));
            }
            return bean;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SQLException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}