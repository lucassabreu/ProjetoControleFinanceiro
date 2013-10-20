package controlefinanceiro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    protected static Connection conn            = null;

    public static final int     SQL_SERVER      = 0;
    public static final int     MY_SQL          = 1;

    public static int           connectionType = ConnectionUtil.MY_SQL;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                if (connectionType == SQL_SERVER) {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager
                                    .getConnection("jdbc:sqlserver://localhost:1433;databaseName=controlefinanc", "bob", "bob");
                } else if (connectionType == MY_SQL) {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager
                                    .getConnection("jdbc:mysql://localhost/controlefinanc", "root", "");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;
    }
}
