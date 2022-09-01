package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexion.DataManager;
import Modelo.Personas;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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

public class VentanaSesionado extends JFrame {

	private JPanel contentPane;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 362);
		
		Personas Sesion= Personas.getInstancia();
		int SesionType = Sesion.getID_ROL();
		
		
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPerfil = new JMenu("Perfil");
		menuBar.add(mnPerfil);
		
		JPanel panel = new JPanel();
		mnPerfil.add(panel);
		panel.setBackground(SystemColor.activeCaption);
		
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Baja usuario");
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		mnUsuarios.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Modificacion usuario");
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
		mnUsuarios.add(mntmNewMenuItem_1);
		
		JMenuItem mntmRoles = new JMenuItem("Administrar Roles");
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mmVentas.setEnabled(false);
		mnInventario.setEnabled(false);
		mnCompras.setEnabled(false);
		mnCuentasCorrientes.setEnabled(false);
		mmSueldos.setEnabled(false);
		mnUsuarios.setEnabled(false);
		String GetFunctions = "SELECT * FROM ROL_FUNCION WHERE ID_ROL="+ (SesionType);
		PreparedStatement GetFunctionsPrepared;
		try {
			GetFunctionsPrepared = DataManager.databaseConnection.prepareStatement(GetFunctions);
			ResultSet GetFunctionsResult = GetFunctionsPrepared.executeQuery();
			
			while(GetFunctionsResult.next()) {
				int ActivateFunction = GetFunctionsResult.getInt("ID_FUNCIONALIDAD");
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

	}
}
