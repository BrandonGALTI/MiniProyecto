package Main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JFrame;

import Coneccion.DataManager;
import Ventanas.InicioDeSesion;

public class Main {

	public static void main(String[] args) {
		
		JFrame frmIniciarSesion;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioDeSesion window = new InicioDeSesion();
					window.frmIniciarSesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		String INSERT_PERSONA = "INSERT INTO PERSONA (id_persona, documento, apellido1, apellido2, nombre1, nombre2, fec_nac, clave, id_rol, mail) values (?,?,?,?,?,?,?,?,?,?)";
//		try {
//			PreparedStatement statement = DataManager.databaseConnection.prepareStatement(INSERT_PERSONA);
//			statement.setInt(1, 0);
//			statement.setString(2,"1");
//			statement.setString(3,"1");
//			statement.setString(4,"1");
//			statement.setString(5,"1");
//			statement.setString(6,"1");
//			statement.setDate(7,Date.valueOf());
//			statement.setString(8,"1");
//			statement.setInt(9,1);
//			statement.setString(10,"1");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
	}

}
