package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Modelo.Funcionalidad;

public class DAOFuncionalidad {

	private static final String ALL_FUNCIONALIDADES = "SELECT * FROM FUNCIONALIDAD";
	
	public static LinkedList<Funcionalidad> findAll(){
		LinkedList<Funcionalidad> funcionalidades = new LinkedList<Funcionalidad>();
		try {
			PreparedStatement sentencia = DataManager.getConnection().prepareStatement(ALL_FUNCIONALIDADES);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				Funcionalidad f = new Funcionalidad(resultado.getString("NOMBRE_FUNCIONALIDAD"),resultado.getString("DESCRIPCION"));
				funcionalidades.add(f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return funcionalidades;
	}
}
