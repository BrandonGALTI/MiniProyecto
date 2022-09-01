package Conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import Modelo.Personas;

public class DAOPersonas {
	
	
	private static final String ALL_PERSONAS = "SELECT * FROM PERSONA";
	private static final String INSERT_PERSONA = "INSERT INTO PERSONA(ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,FEC_NAC=?,CLAVE=?,ID_ROL=?,MAIL=?) values (?,?,?,?,?,?,?,?,?,?)";
	private static final String MAX_ID_PERSONA = "SELECT MAX(ID_PERSONA) AS MAX FROM PERSONAS";
	private static final String BUSCAR_PERSONA = "SELECT * FROM PERSONA WHERE MAIL =?";
	private static final String UPDATE_PERSONA = "UPDATE PERSONA SET DOCUMENTO=?, APELLIDO1=?, APELLIDO2=?, NOMBRE1=?, NOMBRE2=?,FEC_NAC=?,CLAVE=?,ID_ROL=?,MAIL=? WHERE ID_PERSONA=?";
	private static final String LOGUEO = "SELECT CLAVE FROM PERSONA where mail=?";
	
	
	public static void logUser(String correo) {
		Personas.setInstancia(findPersona(correo));
	}
	
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
	
	public static boolean insert(Personas persona) {	
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(INSERT_PERSONA);	
			sentencia.setInt(1, getMaxID());
			sentencia.setString(2, persona.getDocumento());
			sentencia.setString(3, persona.getApellido1());
			sentencia.setString(4, persona.getApellido2());
			sentencia.setString(5, persona.getNombre1());
			sentencia.setString(6, persona.getNombre2());
			sentencia.setDate(7, Date.valueOf(persona.getFechaNac()));
			sentencia.setString(8, persona.getClave());
			sentencia.setInt(9, persona.getID_ROL());
			sentencia.setString(10, persona.getMail());
			int Retorno = sentencia.executeUpdate();		
			return Retorno>0;		
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Personas findPersona(String MAIL) {
		Personas busqueda = new Personas();
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(BUSCAR_PERSONA);			
			sentencia.setString(1, MAIL);
			ResultSet persona = sentencia.executeQuery();			
			while (persona.next()) {
				String documento = persona.getString("DOCUMENTO");
				String apellido1 = persona.getString("APELLIDO1");
				String apellido2 = persona.getString("APELLIDO2");
				String nombre1 = persona.getString("NOMBRE1");
				String nombre2 = persona.getString("NOMBRE2");
				LocalDate fecnac = (persona.getDate("FEC_NAC")).toLocalDate();
				String clave = persona.getString("CLAVE");
				String mail= persona.getString("MAIL");
				int id_rol = persona.getInt("ID_ROL");
				int id_persona = persona.getInt("ID_PERSONA");
				busqueda = new Personas(documento,apellido1,apellido2,nombre1,nombre2,fecnac,clave,mail,id_rol,id_persona);
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
			sentencia.setDate(6, Date.valueOf(persona.getFechaNac()));
			sentencia.setString(7, persona.getClave());
			sentencia.setInt(8, persona.getID_ROL());
			sentencia.setString(9, persona.getMail());
			sentencia.setInt(10, persona.getId_persona());
			int registrosEditados = sentencia.executeUpdate();
			return registrosEditados > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean logIn(String CORREO,String CLAVE) {
		boolean login = false;
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(LOGUEO);
			sentencia.setString(1, CORREO);
			ResultSet clave = sentencia.executeQuery();
			while (clave.next()) {
				if(CLAVE.equals(clave.getString("CLAVE"))) {
					login = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return login;
	}
	
}
	
	
	
	
	
	
	
	

