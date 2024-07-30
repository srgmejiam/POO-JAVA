package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String SERVER = "MARVINMEJIA\\SQL2019";
    private final String USER = "sa";
    private final String PASSWORD = "#Mejia#";
    private final String DATABASE = "SISBD";
    
    private final String PORT = "1433";
    private final String URL = "jdbc:sqlserver://" + SERVER + ":" + PORT + ";databaseName=" + DATABASE  + ";trustServerCertificate=true";
    private final Connection Conexion = null;

    public Connection Get() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Conexion;
    }
}
