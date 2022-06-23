package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    public static List<Map<String, String>> getListOfMapFromResultSet(String query) {
        String dbUrl = ConfigReader.getPropertyValue("dbURL");
        String username = ConfigReader.getPropertyValue("dbUsername");
        String password = ConfigReader.getPropertyValue("dbPassword");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData resultSetMetaData = null;
        List<Map<String, String>> tableData = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
                    String nameOfColumn = resultSetMetaData.getColumnLabel(i);
                    String columnValue = resultSet.getString(nameOfColumn);
                    row.put(nameOfColumn, columnValue);
                }
                tableData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return tableData;
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

