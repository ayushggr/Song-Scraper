package tech.codingclub.database;

import java.sql.SQLException;

public class DatabaseUtil {
    private static DatabaseConnectionPool databaseConnectionPool;

    static {
        try {
            databaseConnectionPool = new DatabaseConnectionPool(
                    "jdbc:postgresql://127.0.0.1:5432/fetchedsongs", "postgres",
                    "postgres", 350, 350);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionPool getDataseBaseConnectionPool() {
        return databaseConnectionPool;
    }
}