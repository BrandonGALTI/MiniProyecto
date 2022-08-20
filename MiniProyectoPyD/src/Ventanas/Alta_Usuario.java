package Ventanas;

import java.awt.EventQueue; 
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import Coneccion.DataManager;
import Usuarios.EnumRol;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alta_Usuario extends JInternalFrame {
	private JTextField txtfDocumento;
	private JTextField txtfNombre1;
	private JTextField txtfNombre2;
	private JLabel lblApellidos;
	private JTextField txtfApellido1;
	private JTextField txtfApellido2;
	private JTextField txtfFecNac;
	private JTextField txtfClave;
	private JLabel lblClave;
	private JTextField txtfMail;
	private JLabel lblMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Alta_Usuario frame = new Alta_Usuario();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Alta_Usuario() {
		setTitle("Alta de usuario\r\n");
		setBorder(null);
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBounds(100, 100, 455, 300);
		getContentPane().setLayout(null);
		
		txtfDocumento = new JTextField();
		txtfDocumento.setBounds(119, 12, 142, 19);
		getContentPane().add(txtfDocumento);
		txtfDocumento.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(10, 15, 75, 16);
		getContentPane().add(lblDocumento);
		
		txtfNombre1 = new JTextField();
		txtfNombre1.setColumns(10);
		txtfNombre1.setBounds(119, 41, 142, 19);
		getContentPane().add(txtfNombre1);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(10, 44, 75, 16);
		getContentPane().add(lblNombre);
		
		txtfNombre2 = new JTextField();
		txtfNombre2.setColumns(10);
		txtfNombre2.setBounds(271, 41, 142, 19);
		getContentPane().add(txtfNombre2);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setBounds(10, 73, 75, 16);
		getContentPane().add(lblApellidos);
		
		txtfApellido1 = new JTextField();
		txtfApellido1.setColumns(10);
		txtfApellido1.setBounds(119, 70, 142, 19);
		getContentPane().add(txtfApellido1);
		
		txtfApellido2 = new JTextField();
		txtfApellido2.setColumns(10);
		txtfApellido2.setBounds(271, 70, 142, 19);
		getContentPane().add(txtfApellido2);
		
		JLabel lblFecNac = new JLabel("Fecha de nacimiento");
		lblFecNac.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecNac.setBounds(10, 102, 119, 16);
		getContentPane().add(lblFecNac);
		
		txtfFecNac = new JTextField();
		txtfFecNac.setToolTipText("dd/MM/yyyy");
		txtfFecNac.setColumns(10);
		txtfFecNac.setBounds(193, 101, 142, 19);
		getContentPane().add(txtfFecNac);
		
		txtfClave = new JTextField();
		txtfClave.setToolTipText("");
		txtfClave.setColumns(10);
		txtfClave.setBounds(119, 132, 294, 19);
		getContentPane().add(txtfClave);
		
		lblClave = new JLabel("Contrase\u00F1a");
		lblClave.setHorizontalAlignment(SwingConstants.LEFT);
		lblClave.setBounds(10, 133, 75, 16);
		getContentPane().add(lblClave);
		
		txtfMail = new JTextField();
		txtfMail.setToolTipText("");
		txtfMail.setColumns(10);
		txtfMail.setBounds(119, 161, 294, 19);
		getContentPane().add(txtfMail);
		
		lblMail = new JLabel("Email");
		lblMail.setHorizontalAlignment(SwingConstants.LEFT);
		lblMail.setBounds(10, 162, 75, 16);
		getContentPane().add(lblMail);
		
		JComboBox cbRol = new JComboBox();
		cbRol.setToolTipText("");
		cbRol.setBounds(119, 190, 294, 21);
		getContentPane().add(cbRol);
		EnumRol[] roles = EnumRol.values();
		for (int j = 0; j < roles.length; j++) {
			cbRol.addItem(roles[j].name());
		}
		
		
		

		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 194, 45, 13);
		getContentPane().add(lblRol);
		
		JButton btnAltaUsuario = new JButton("Dar de alta");
		btnAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String INSERT_PERSONA = "INSERT INTO PERSONA (id_persona, documento, apellido1, apellido2, nombre1, nombre2, fec_nac, clave, id_rol, mail) values (?,?,?,?,?,?,?,?,?,?)";
				
				try {
					String Max_id = "SELECT MAX(ID_PERSONA) FROM PERSONA";
					PreparedStatement sentencia = DataManager.databaseConnection.prepareStatement(Max_id);
					
					String d1 = "23/02/1997";
					SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
					Date date1 = f1.parse(d1);  
					java.sql.Date date2 = new java.sql.Date(((Date) date1).getTime());
					ResultSet resultado = sentencia.executeQuery();	
					int result2 = 0;
					while(resultado.next()) {
						result2=resultado.getInt(1);
					}
					PreparedStatement statement = DataManager.databaseConnection.prepareStatement(INSERT_PERSONA);
					statement.setInt(1, (result2+1));
					statement.setString(2,txtfDocumento.getText());
					statement.setString(3,txtfApellido1.getText());
					statement.setString(4,txtfApellido2.getText());
					statement.setString(5,txtfNombre1.getText());
					statement.setString(6,txtfNombre2.getText());
					statement.setDate(7, (java.sql.Date) date2);
					statement.setString(8,txtfClave.getText());
					statement.setInt(9,(cbRol.getSelectedIndex()+1));
					statement.setString(10,txtfMail.getText());
					statement.executeUpdate();
				} catch (SQLException | ParseException r) {
					r.printStackTrace();
				}
				
			}
		});
		btnAltaUsuario.setBounds(119, 235, 119, 21);
		getContentPane().add(btnAltaUsuario);
		
		JButton btnCerrarAltaUsuario = new JButton("Cerrar");
		btnCerrarAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrarAltaUsuario.setBounds(328, 235, 85, 21);
		getContentPane().add(btnCerrarAltaUsuario);

	}
}
