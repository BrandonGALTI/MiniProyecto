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
import javax.swing.JPasswordField;

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
import java.awt.SystemColor;

public class InicioDeSesion {

	public JFrame frmIniciarSesion;
	private JTextField textFieldCorreo;
	private JPasswordField textFieldContrasenia;
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
		frmIniciarSesion.getContentPane().setBackground(SystemColor.info);
		frmIniciarSesion.setTitle("Log in");
		frmIniciarSesion.setBounds(100, 100, 263, 242);
		frmIniciarSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIniciarSesion.getContentPane().setLayout(null);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setToolTipText("Ingrese correo");
		textFieldCorreo.setForeground(Color.RED);
		textFieldCorreo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		textFieldCorreo.setBounds(48, 36, 160, 21);
		frmIniciarSesion.getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldContrasenia = new JPasswordField();
		textFieldContrasenia.setToolTipText("Ingrese constraseña");
		textFieldContrasenia.setForeground(Color.RED);
		textFieldContrasenia.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		textFieldContrasenia.setBounds(48, 90, 160, 21);
		frmIniciarSesion.getContentPane().add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(101, 23, 41, 14);
		frmIniciarSesion.getContentPane().add(lblCorreo);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasenia.setBounds(85, 76, 70, 14);
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
				@SuppressWarnings("deprecation")
				String contrasenia = textFieldContrasenia.getText();
				boolean logged=DAOPersonas.logIn(correo, contrasenia);
				if(logged) {
					DAOPersonas.logUser(correo);
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
		btnEnviar.setBounds(68, 131, 121, 21);
		frmIniciarSesion.getContentPane().add(btnEnviar);
		
		btnAtras = new JButton("Cerrar");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIniciarSesion.dispose();
			}
		});
		btnAtras.setBounds(68, 174, 121, 21);
		frmIniciarSesion.getContentPane().add(btnAtras);
	
	}
}