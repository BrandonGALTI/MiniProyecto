package Vista;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;  
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexion.DAORFuncion;
import Conexion.DataManager;
import Modelo.Personas;
import Modelo.RolFuncion;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Toolkit;

public class VentanaSesionado extends JFrame {

	private static JPanel contentPane= new JPanel();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSesionado frame = new VentanaSesionado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaSesionado() {
		setBackground(SystemColor.activeCaption);
		setForeground(SystemColor.activeCaption);
		setTitle("Impro7 operation center");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 362);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(586, 10, -574, 283);
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.add(scrollPane);
		
	Personas Sesion= Personas.getInstancia();
		int SesionType = Sesion.getID_ROL();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPerfil = new JMenu("Perfil");
		menuBar.add(mnPerfil);
		
		JPanel panel = new JPanel();
		mnPerfil.add(panel);
		panel.setBackground(new Color(221, 160, 221));
		
		JLabel lblUser = new JLabel("");
		panel.add(lblUser);
		lblUser.setVerticalAlignment(SwingConstants.TOP);
		lblUser.setText(Sesion.getNombre1()+ " " + Sesion.getApellido1() + "(" + Sesion.getDocumento() + ")");
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAñadirUsuario = new JMenuItem("Alta usuario");
		mntmAñadirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alta_Usuario urraca = new Alta_Usuario();
				contentPane.add(urraca);
				urraca.setVisible(true);
				urraca.setSize(contentPane.getWidth(), contentPane.getHeight());
			}
		});
		mntmAñadirUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		mnUsuarios.add(mntmAñadirUsuario);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Alta rol");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alta_rol frame = new Alta_rol();
				frame.setVisible(true);
				contentPane.add(frame);
				frame.setSize(contentPane.getWidth(), contentPane.getHeight());
			}
		});
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		mnUsuarios.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Modificacion usuario");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaUsuarios frame = new VistaUsuarios();
				frame.setVisible(true);
				contentPane.add(frame);
				frame.setSize(contentPane.getWidth(), contentPane.getHeight());
			}
		});
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
		mnUsuarios.add(mntmNewMenuItem_1);
		
		JMenuItem mntmRoles = new JMenuItem("Modificacion roles");
		mntmRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ModificacionRoles frame = new ModificacionRoles();
					contentPane.add(frame);
					frame.setVisible(true);
					frame.setSize(contentPane.getWidth(), contentPane.getHeight());
			}
		});
		mnUsuarios.add(mntmRoles);
		
		JMenu mmVentas = new JMenu("Ventas");
		menuBar.add(mmVentas);
		
		
		JMenu mnCompras = new JMenu("Compras");
		menuBar.add(mnCompras);
		
		
		JMenu mnInventario = new JMenu("Inventario");
		menuBar.add(mnInventario);
	
		JMenu mnCuentasCorrientes = new JMenu("CuentasCorrientes");
		menuBar.add(mnCuentasCorrientes);

		
		JMenu mmSueldos = new JMenu("Sueldos");
		menuBar.add(mmSueldos);
//		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		
		
		mmVentas.setEnabled(false);
		mnInventario.setEnabled(false);
		mnCompras.setEnabled(false);
		mnCuentasCorrientes.setEnabled(false);
		mmSueldos.setEnabled(false);
		mnUsuarios.setEnabled(false);
		LinkedList<RolFuncion> RFs = DAORFuncion.selctRAsigned(SesionType);
		for (RolFuncion rolFuncion : RFs) {
			int ActivateFunction = rolFuncion.getId_funcion();
			switch(ActivateFunction) {
			case 1:
				mnInventario.setEnabled(true);
				break;
			case 2:
				mmVentas.setEnabled(true);
				break;
			case 3:
				mnCompras.setEnabled(true);
				break;
			case 4:
				mnCuentasCorrientes.setEnabled(true);
				break;
			case 5:
				mmSueldos.setEnabled(true);
				break;
			case 6:
				mnUsuarios.setEnabled(true);
				break;
			}
		}
		
	}
}
