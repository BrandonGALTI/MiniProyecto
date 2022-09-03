package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Modelo.Rol;

public class DAORol {
	private static final String ALL_ROLES = "SELECT * FROM ROL ORDER BY ID_ROL";
	private static final String INSERT_ROL = "INSERT INTO ROL(ID_ROL,NOMBRE_ROL,DESCRIPCION) VALUES (?,?,?)";
	private static final String DROP_ROL = "DELETE ROL WHERE ID_ROL = ?";
	private static final String UPDATE_ROL = "UPDATE ROL SET NOMBRE_ROL=?,DESCRIPCION=? WHERE ID_ROL=?";
	private static final String MAX_ID_ROL = "SELECT MAX(ID_ROL) AS MAX FROM ROL";
	private static final String BUSCAR_ROL = "SELECT * FROM ROL WHERE ID_ROL=?";

	public static boolean update(int id,String nombre,String descripcion) {
		boolean flag = false;
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(UPDATE_ROL);	
			sentencia.setInt(3, id);
			sentencia.setString(1, nombre);
			sentencia.setString(2, descripcion);
			sentencia.executeUpdate();
			flag=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static Rol findId(int id) {
		Rol rol = new Rol();
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(BUSCAR_ROL);
			sentencia.setInt(1, id);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				rol = new Rol(resultado.getInt("ID_ROL"),resultado.getString("NOMBRE_ROL"),resultado.getString("DESCRIPCION"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rol;
	}
	
	
	public static LinkedList<Rol> findAll() {
		LinkedList<Rol> roles = new LinkedList<Rol>();
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(ALL_ROLES);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				Rol rol = new Rol(resultado.getInt("ID_ROL"),resultado.getString("NOMBRE_ROL"),resultado.getString("DESCRIPCION"));
				roles.add(rol);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	public static boolean insert(String nombre,String descripcion) {
		boolean operacion =false;
		try {
		PreparedStatement sentencia = DataManager.getConnection().prepareStatement(INSERT_ROL);	
		sentencia.setInt(1, getMaxID());
		sentencia.setString(2, nombre);
		sentencia.setString(3, descripcion);
		sentencia.executeUpdate();
		operacion= true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return operacion;
	}
	
	public static boolean drop(int id) {
		boolean operacion =false;
		try {
			DAOPersonas.updateRol(id);
			DAORFuncion.dropAllRAsigned(id);
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(DROP_ROL);	
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
			operacion= true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return operacion;
	}
	
	public static int getMaxID() {
		int id= 0;
		try {
		PreparedStatement sentencia = DataManager.getConnection().prepareStatement(MAX_ID_ROL);
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
