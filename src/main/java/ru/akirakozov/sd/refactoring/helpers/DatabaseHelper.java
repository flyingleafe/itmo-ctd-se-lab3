package ru.akirakozov.sd.refactoring.helpers;

import java.sql.*;

/**
 * @author flyingleafe
 */
public class DatabaseHelper {

    public static void dbQuery(String sql, ThrowingAction<ResultSet> action) {
        dbAction(stmt -> {
            ResultSet rs = stmt.executeQuery(sql);
            action.accept(rs);
            rs.close();
        });
    }

    public static void dbUpdate(String sql) {
        dbAction(stmt -> stmt.executeUpdate(sql));
    }

    private DatabaseHelper() {}

    private static void dbAction(ThrowingAction<Statement> action) {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                action.accept(stmt);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
