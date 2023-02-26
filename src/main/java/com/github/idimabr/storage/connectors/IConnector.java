package com.github.idimabr.storage.connectors;

import com.github.idimabr.storage.type.ConnectionType;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnector {

    public ConnectionType getType();

    public Connection getConnection() throws SQLException;

}