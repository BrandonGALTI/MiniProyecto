package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import Conexion.DAOPersonas;
import Conexion.DataManager;
import Modelo.EnumRol;
import Modelo.Personas;

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
	private JButton btnAtras;

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
		frmIniciarSesion.setTitle("Log in");
		frmIniciarSesion.setBounds(100, 100, 450, 300);
		frmIniciarSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIniciarSesion.getContentPane().setLayout(null);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setToolTipText("Ingrese correo");
		textFieldCorreo.setForeground(Color.RED);
		textFieldCorreo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		textFieldCorreo.setBounds(147, 48, 160, 30);
		frmIniciarSesion.getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldContrasenia = new JTextField();
		textFieldContrasenia.setToolTipText("Ingrese constraseña");
		textFieldContrasenia.setForeground(Color.RED);
		textFieldContrasenia.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		textFieldContrasenia.setBounds(147, 102, 160, 30);
		frmIniciarSesion.getContentPane().add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(96, 56, 41, 14);
		frmIniciarSesion.getContentPane().add(lblCorreo);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasenia.setBounds(67, 110, 70, 14);
		frmIniciarSesion.getContentPane().add(lblContrasenia);
		
		JButton btnEnviar = new JButton("Confirmar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String correo = textFieldCorreo.getText();
				String contrasenia = textFieldContrasenia.getText();
				boolean logged=DAOPersonas.logIn(correo, contrasenia);
				if(logged) {
					DAOPersonas.logUser(correo);
					JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente");
					frmIniciarSesion.dispose();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							VentanaSesionado frame = new VentanaSesionado();
							frame.setVisible(true);
						}
					});
					frmIniciarSesion.dispose();
				}else {
					textFieldCorreo.setText("");
					textFieldContrasenia.setText("");
					JOptionPane.showMessageDialog(null, "Los datos ingresados no corresponden a ningun usuario registrado en el sistema");
				}
			}
		});
		btnEnviar.setBounds(168, 152, 121, 21);
		frmIniciarSesion.getContentPane().add(btnEnviar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIniciarSesion.dispose();
			}
		});
		btnAtras.setBounds(185, 195, 89, 21);
		frmIniciarSesion.getContentPane().add(btnAtras);
	
	}
}