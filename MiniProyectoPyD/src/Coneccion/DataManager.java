package Coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManager {
	public static Connection databaseConnection;
	static String usuario="MiniProyecto";
	static String clave="MiniProyecto";
	static String base="jdbc:oracle:thin:@localhost:1521:xe";

	static {
		try {
			databaseConnection = DriverManager.getConnection(base,usuario,clave);
			System.out.println("Conexi�n instanciada con �xito");
		}catch(SQLException e) {
			System.out.println("Error en la conexi�n");
		}
		
	}
	
	public static Connection getConnection() {
		return databaseConnection;
	}
	}
