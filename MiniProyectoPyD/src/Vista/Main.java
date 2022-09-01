package Vista;

import java.awt.EventQueue; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.JFrame;

import Conexion.DataManager;

public class Main {

	public static void main(String[] args) {
		
//		EventQueue.invokeLater(new Runnable() {
//				public void run() {
//						VentanaSesionado frame = new VentanaSesionado();
//						frame.setVisible(true);
//				}
//			});
//		Connection conexion = DataManager.getConnection();
//		String consulta = "SELECT * FROM PERSONA";
//		
//		try {
//			Statement sentencia = conexion.createStatement();
//			ResultSet personasRS = sentencia.executeQuery(consulta);
//			while(personasRS.next()) {
//					System.out.println("ID:"+personasRS.getString("ID_PERSONA") + " DOCUMENTO:" + personasRS.getString("DOCUMENTO")+ " NOMBRES:" + personasRS.getString("NOMBRE1")+ " " + personasRS.getString("NOMBRE2")+ " APELLIDOS:" + personasRS.getString("APELLIDO1")+ " " + personasRS.getString("APELLIDO2")+ " FECHA DE NACIMIENTO:" + personasRS.getString("FEC_NAC")+ " CLAVE:" + personasRS.getString("CLAVE")+ " ID_ROL:" + personasRS.getString("ID_ROL")+ " EMAIL:" + personasRS.getString("MAIL"));
//
//			}
//		}catch(SQLException e) {
//			System.out.println("Ocurrió un problema al ejecutar la consulta");
//		}
//		
		
		//*Esto está comentado pues es parte del programa principal que en el que se está trabajando*
//		
//		JFrame frmIniciarSesion;
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InicioDeSesion window = new InicioDeSesion();
//					window.frmIniciarSesion.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
	}

}
