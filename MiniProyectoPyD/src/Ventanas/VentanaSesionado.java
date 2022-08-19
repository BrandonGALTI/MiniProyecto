package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 512, 362);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPerfil = new JMenu("Perfil");
		menuBar.add(mnPerfil);
		
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
		

	}
}
