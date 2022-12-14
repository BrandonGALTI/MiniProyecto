package Vista;

import java.awt.EventQueue;   
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import Conexion.DAOPersonas;
import Conexion.DAORol;
import Conexion.DataManager;
import Modelo.EnumRol;
import Modelo.Personas;
import Modelo.Rol;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.*;
import java.awt.SystemColor;
import java.awt.Color;


public class Alta_Usuario extends JInternalFrame {
	private JTextField txtfDocumento;
	private JTextField txtfNombre1;
	private JTextField txtfNombre2;
	private JLabel lblApellidos;
	private JTextField txtfApellido1;
	private JTextField txtfApellido2;
	private JTextField txtfClave;
	private JLabel lblClave;
	private JTextField txtfMail;
	private JLabel lblMail;
	private JComboBox cbRol = new JComboBox();
	JDateChooser dateChooser;

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
		setFrameIcon(null);
		getContentPane().setBackground(SystemColor.info);
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
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(139, 99, 142, 19);
		getContentPane().add(dateChooser);
		
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
		
		cbRol.setToolTipText("");
		cbRol.setBounds(119, 190, 294, 21);
		getContentPane().add(cbRol);
		for (Rol r : DAORol.findAll()) {
			cbRol.addItem(r);
		}
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 194, 45, 13);
		getContentPane().add(lblRol);
		
		JButton btnAltaUsuario = new JButton("Dar de alta");
		btnAltaUsuario.setForeground(new Color(0, 128, 0));
		btnAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!((txtfDocumento.getText()==null) || (txtfApellido1.getText()==null) || (txtfApellido2.getText()==null) || (txtfNombre1.getText()==null) || (txtfNombre2.getText()==null) || (dateChooser.getDate()==null) || (txtfClave.getText()==null) || (txtfMail.getText()==null))){
					if(VistaUsuarios.validarDocumento(txtfDocumento.getText())) {
						LocalDate fecha = VistaUsuarios.dateToLocal(dateChooser.getDate());
						Personas p = new Personas(txtfDocumento.getText(),txtfApellido1.getText(),txtfApellido2.getText(),txtfNombre1.getText(),txtfNombre2.getText(),fecha,txtfClave.getText(),txtfMail.getText(),((Rol)cbRol.getSelectedItem()).getId_rol());
						DAOPersonas.insert(p);
						clearTxts();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Complete todos los campos para continuar");
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
	
	private void clearTxts() {
		txtfDocumento.setText(null);
		txtfNombre1.setText(null);
		txtfNombre2.setText(null);
		txtfApellido2.setText(null);
		txtfApellido1.setText(null);
		txtfClave.setText(null);
		txtfMail.setText(null);
		cbRol.setSelectedIndex(0);
		dateChooser.setDate(null);
	}
	
	public static void disposexd() {
		
	}
}

