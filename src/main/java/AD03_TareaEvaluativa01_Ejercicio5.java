import org.vibur.dbcp.ViburDBCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class AD03_TareaEvaluativa01_Ejercicio5 {
    
    // Punto de entrada principal
    public static void main(String[] args) {
        
        // Intenta establecer una conexión utilizando el DataSource configurado
        try (ViburDBCPDataSource ds = DataSource()) {
            
            // Intenta obtener una conexión del pool de conexiones
            try (Connection conn = ds.getConnection()) {
                
                // Verifica si la conexión es válida
                if (conn != null && conn.isValid(0)) {
                    System.out.println("Conexión establecida correctamente.");
                } else {
                    System.out.println("No se pudo establecer la conexión.");
                }
            
            } catch (SQLException es) {
                muestraErrorSQL(es);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
    
    /**
     * Configura y devuelve un objeto ViburDBCPDataSource para conectar con HSQLDB
     * Utiliza un pool de conexiones para gestionar las conexiones a la base de datos
     * @return ViburDBCPDataSource Fuente de datos configurada
     */
    private static ViburDBCPDataSource DataSource() {
        
        // Configuración para la conexión a la base de datos
        String dbUrl = "jdbc:hsqldb:file:mydatabase;shutdown=true";  // Path de la BD
        String dbUser = "SA";  // Usuario por defecto en HSQLDB
        String dbPassword = "";  // Contraseña por defecto
        
        // Crear la fuente de datos utilizando el poll de conexiones Vibur
        ViburDBCPDataSource dataSource = new ViburDBCPDataSource();
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setPoolMaxSize(5); // Tamaño del pool de conexiones
        dataSource.start(); // Inicia el pool de conexiones
        return dataSource;
    }
    
    /**
     * Muestra los detalles de un error SQL en la consola
     * Imprime información detallada del error para ayudar a depurar
     * @param es Objeto SQLException con los detalles del error
     */
    public static void muestraErrorSQL(SQLException es) {
        System.err.println("SQL ERROR mensaje: " + es.getMessage());
        System.err.println("SQL Estado: " + es.getSQLState());
        System.err.println("SQL código específico: " + es.getErrorCode());
    }
    
}
