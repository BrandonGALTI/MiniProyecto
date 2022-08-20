package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import Coneccion.DataManager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class InicioDeSesion {

	public JFrame frmIniciarSesion;
	private JTextField textFieldCorreo;
	private JTextField textFieldContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public InicioDeSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIniciarSesion = new JFrame();
		frmIniciarSesion.getContentPane().setBackground(Color.PINK);
		frmIniciarSesion.setTitle("Gestion de usuarios");
		frmIniciarSesion.setBounds(100, 100, 450, 300);
		frmIniciarSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIniciarSesion.getContentPane().setLayout(null);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setToolTipText("Ingrese correo");
		textFieldCorreo.setForeground(Color.RED);
		textFieldCorreo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		textFieldCorreo.setBounds(161, 48, 160, 30);
		frmIniciarSesion.getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldContrasenia = new JTextField();
		textFieldContrasenia.setToolTipText("Ingrese constraseña");
		textFieldContrasenia.setForeground(Color.RED);
		textFieldContrasenia.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		textFieldContrasenia.setBounds(161, 102, 160, 30);
		frmIniciarSesion.getContentPane().add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(110, 56, 41, 14);
		frmIniciarSesion.getContentPane().add(lblCorreo);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasenia.setBounds(81, 110, 70, 14);
		frmIniciarSesion.getContentPane().add(lblContrasenia);
		
		JButton btnEnviar = new JButton("Entrar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String correo = textFieldCorreo.getText();
				String contrasenia = textFieldContrasenia.getText();

				String consulta = "SELECT * FROM PERSONA where mail='" + correo + "'";
				
				try {
					Statement sentencia = DataManager.databaseConnection.createStatement();
					ResultSet personasRS = sentencia.executeQuery(consulta);
						while(personasRS.next()) {
							if(personasRS.getString("CLAVE").equals(contrasenia)) {
		  						System.out.println(personasRS.getString("MAIL") + " / " + personasRS.getString("CLAVE"));
		  						JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente");
		  				//		JOptionPane.showMessageDialog(null, "Usuario: " + personasRS.getString("ID_PERSONA") +" "+personasRS.getString("DOCUMENTO") +" "+ personasRS.getString("NOMBRE1") +" "+ personasRS.getString("NOMBRE2") +" "+ personasRS.getString("APELLIDO1") +" "+ personasRS.getString("APELLIDO2") +" "+ personasRS.getString("FEC_NAC") +" "+ personasRS.getString("CLAVE") +" "+ personasRS.getString("ID_ROL") +" "+ personasRS.getString("MAIL"));
		  						
		  						EventQueue.invokeLater(new Runnable() {
		  							public void run() {
		  									VentanaSesionado frame = new VentanaSesionado();
		  									frame.setVisible(true);
		  							}
		  						});
		  						frmIniciarSesion.dispose();
							}else {
								JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
							}
						}
				}catch(SQLException r) {
					System.out.println("No");
				}
				textFieldCorreo.setText("");
				textFieldContrasenia.setText("");
				
			}
		});
		btnEnviar.setBounds(199, 170, 89, 23);
		frmIniciarSesion.getContentPane().add(btnEnviar);
	
	}
}