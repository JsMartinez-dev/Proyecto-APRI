
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
    
    static final String BD_URL = "jdbc:postgresql://localhost:5432/SIME";
    private static final String BD_USER = "postgres";
    private static final String BD_PASSWORD = "123456";
    static Connection conn = null;
    
    public static Connection getConexion() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
             conn = DriverManager.getConnection(BD_URL, BD_USER, BD_PASSWORD);
            System.out.println("SE CONECTO A LA BD EXITOSAMENTE:)");
            return conn;
        }catch(ClassNotFoundException e){
            System.out.println("Driver no encontrado: "+e.getMessage());
            throw new SQLException("No se pudo cargar el Driver de postgresql");
        }

    }
    public static void cerrarConexion() throws SQLException{
        if(conn!=null){
            if(!conn.isClosed()){
                conn.close();
            }
        }
    }
    
    
    
}
