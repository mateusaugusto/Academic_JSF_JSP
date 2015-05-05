package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class ConexaoJDBC {

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/gestaoacademica", "root","");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
