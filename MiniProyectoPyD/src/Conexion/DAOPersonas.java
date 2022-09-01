package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Modelo.Personas;

public class DAOPersonas {
	
	
	private static final String ALL_PERSONAS = "SELECT * FROM PERSONAS";
	
	private static final String INSERT_PERSONA = "INSERT INTO PERSONAS(ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2) values (?,?,?,?,?,?)";
	
	private static final String MAX_ID_PERSONA = "SELECT MAX(ID_PERSONA) AS MAX FROM PERSONAS";
	
	private static final String BUSCAR_PERSONA = "SELECT * FROM PERSONAS WHERE APELLIDO1=? AND NOMBRE1=?";
	
	private static final String UPDATE_PERSONA = "UPDATE PERSONAS SET DOCUMENTO=?, APELLIDO1=?, APELLIDO2=?, NOMBRE1=?, NOMBRE2=? WHERE ID_PERSONA=?";
	
	private static final String LOGUEO = "SELECT CLAVE FROM PERSONA where mail=?";
	
public static LinkedList<String> findAll(){
		
		LinkedList<String> personas = new LinkedList<>();
		
		try {
			
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(ALL_PERSONAS);
			
			ResultSet resultado = sentencia.executeQuery();
			
			while ( resultado.next()) {
				
				String persona= resultado.getString("APELLIDO1")+" | "+resultado.getString("NOMBRE1")+" | "+resultado.getString("DOCUMENTO");
				personas.add(persona);
			}
			return personas;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static int getMaxID() {
		
		int id= 0;
		try {
			
		
		PreparedStatement sentencia = DataManager.getConnection().prepareStatement(MAX_ID_PERSONA);
		ResultSet resultado = sentencia.executeQuery();
		while(resultado.next()) {
			id = resultado.getInt("MAX")+1;
		
		}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return id;
		
		
	}
	public static boolean insert(Personas p) {
	
	try {
		PreparedStatement sentencia = DataManager.getConnection().prepareStatement(INSERT_PERSONA);
	
		sentencia.setInt(1, DAOPersonas.getMaxID());
		sentencia.setString(2, p.getDocumento());
		sentencia.setString(3, p.getApellido1());
		sentencia.setString(4, p.getApellido2());
		sentencia.setString(5, p.getNombre1());
		sentencia.setString(6, p.getNombre2());
		

		int Retorno = sentencia.executeUpdate();
		
		return Retorno>0;
		
	}catch(SQLException e) {
		e.printStackTrace();
		return false;
	}
}
	public static String findPersona(String APELLIDO1, String NOMBRE1) {
		String busqueda = "";
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(BUSCAR_PERSONA);
			
			sentencia.setString(1, APELLIDO1);
			sentencia.setString(2, NOMBRE1);	
			ResultSet persona = sentencia.executeQuery();
			
			while (persona.next()) {
				busqueda = persona.getString("documento")+" "+persona.getString("nombre1")+" "+persona.getString("apellido1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return busqueda;
	}
	
	public static boolean edit(Personas persona) {
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(UPDATE_PERSONA);
			
			sentencia.setString(1, persona.getDocumento());
			sentencia.setString(2, persona.getApellido1());
			sentencia.setString(3, persona.getApellido2());
			sentencia.setString(4, persona.getNombre1());
			sentencia.setString(5, persona.getNombre2());
			
			
			int registrosEditados = sentencia.executeUpdate();
			return registrosEditados > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static String findContra(String CORREO) {
		String password = "";
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(LOGUEO);
			
			sentencia.setString(1, CORREO);
			ResultSet clave = sentencia.executeQuery();
			
			while (clave.next()) {
				password = clave.getString("CLAVE");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return password;
	}
		
		
		
	}
	
	
	
	
	
	
	
	

