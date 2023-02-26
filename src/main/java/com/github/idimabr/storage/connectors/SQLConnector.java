package com.github.idimabr.storage.connectors;

import com.github.idimabr.storage.SQLExecutor;
import com.github.idimabr.storage.type.ConnectionType;
import com.github.idimabr.utils.ConfigUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLConnector implements IConnector {

    private HikariDataSource dataSource;
    private ConnectionType type;


    public SQLConnector(String file) {
        final HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:" + file + ".db");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.dataSource = new HikariDataSource(config);
        this.type = ConnectionType.SQLITE;
    }

    public SQLConnector(String ipAddress, String database, String username, String password) {
        final HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://" + ipAddress + ":3306/" + database);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setUsername(username);
        config.setPassword(password);
        config.setAutoCommit(true);
        this.dataSource = new HikariDataSource(config);
        this.type = ConnectionType.MYSQL;
    }

    @Override
    public ConnectionType getType() {
        return type;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public SQLExecutor executor() throws SQLException {
        return new SQLExecutor(getConnection());
    }

    public void close() {
        this.dataSource.close();
    }
}
