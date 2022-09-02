package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import Modelo.Funcionalidad;
import Modelo.RolFuncion;
public class DAORFuncion {
	

	private static final String MAX_ID_RF = "SELECT MAX(ID_ROL_FUNCION) AS MAX FROM ROL_FUNCION";
	private static final String INSERT_RF = "INSERT INTO ROL_FUNCION(ID_ROL_FUNCION,ID_ROL,ID_FUNCIONALIDAD) VALUES(?,?,?)";
	private static final String DROP_RF  = "DELETE ROL_FUNCION WHERE ID_ROL_FUNCION = ?";
	private static final String DROP_RFxROL = "DELETE ROL_FUNCION WHERE ID_ROL = ?";
	private static final String SELECT_RFxROL= "SELECT * FROM ROL_FUNCION WHERE ID_ROL= ?";
	private static final String SELECT_RFxROLFUN= "SELECT ID_ROL_FUNCION FROM ROL_FUNCION WHERE ID_ROL= ? AND ID_FUNCIONALIDAD = ?";

	
	
	public static boolean dropAllRAsigned(int id_rol) {
		boolean flag = false;
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(DROP_RFxROL);	
			sentencia.setInt(1, id_rol);
			sentencia.executeUpdate();
			flag= true;
		}catch(SQLException e) {e.printStackTrace();}
		return flag;
	}
	
	public static boolean delteRF(int id_rol, int id_funcion){
		boolean flag = false;
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(SELECT_RFxROLFUN);
			sentencia.setInt(1, id_rol);
			sentencia.setInt(2, id_funcion);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				int r = resultado.getInt("ID_ROL_FUNCION");
				drop(r);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static LinkedList<RolFuncion> selctRAsigned(int id_rol){
		LinkedList<RolFuncion> RFs = new LinkedList<RolFuncion>();
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(SELECT_RFxROL);
			sentencia.setInt(1, id_rol);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				RolFuncion rf = new RolFuncion(resultado.getInt("ID_ROL_FUNCION"),resultado.getInt("ID_ROL"),resultado.getInt("ID_FUNCIONALIDAD"));
				RFs.add(rf);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return RFs;
	}
	
	public static boolean drop(int id_rf) {
		boolean flag = false;
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(DROP_RF);	
			sentencia.setInt(1, id_rf);
			sentencia.executeUpdate();
			flag= true;
		}catch(SQLException e) {e.printStackTrace();}
		return flag;
	}
	
	public static boolean insert(int id_rol, int id_funcion) {
		boolean flag = false;
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(INSERT_RF);	
			sentencia.setInt(1, getMaxID());
			sentencia.setInt(2, id_rol);
			sentencia.setInt(3, id_funcion);
			sentencia.executeUpdate();
			flag= true;
		}catch(SQLException e) {e.printStackTrace();}
		return flag;
	}

	public static int getMaxID() {
		int id= 0;
		try {
		PreparedStatement sentencia = DataManager.getConnection().prepareStatement(MAX_ID_RF);
		ResultSet resultado = sentencia.executeQuery();
		while(resultado.next()) {
			id = resultado.getInt("MAX")+1;		
		}
		}catch(SQLException e) {
			e.printStackTrace();			
		}
		return id;	
	}
}
