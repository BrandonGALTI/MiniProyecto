package Vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import Conexion.DAORFuncion;
import Conexion.DAORol;
import Conexion.DataManager;
import Modelo.EnumFuncionalidades;
import Modelo.Rol;
import Modelo.RolFuncion;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class ModificacionRoles extends JInternalFrame {
	private JTable table;
	private JTextField txtfNombreRol;
	private JTextField txtfDescripcion;
	JCheckBox chckbxControlInventario = new JCheckBox("Control de inventario");
	JCheckBox chckbxCompras = new JCheckBox("Compras");
	JCheckBox chckbxVentas = new JCheckBox("Ventas");
	JCheckBox chckbxCuentasCorrientes = new JCheckBox("Cuentas corrientes");
	JCheckBox chckbxSueldos = new JCheckBox("Sueldos");
	JCheckBox chckbxUsuarios = new JCheckBox("Usuarios");
	JComboBox cbRoles = new JComboBox();
	private final JButton btnNewButton = new JButton("Cerrar");
	private final JButton btnGuardarCambios_1 = new JButton("Cancelar");

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionRoles frame = new ModificacionRoles();
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
	public ModificacionRoles() {
		setFrameIcon(null);
		setForeground(SystemColor.windowBorder);
		getContentPane().setBackground(SystemColor.info);
		setTitle("Modificar roles");
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		setBounds(100, 100, 619, 300);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 307, 26);
		getContentPane().add(scrollPane);
		
		JLabel lblNombre = new JLabel("Nombre del rol");
		lblNombre.setBounds(224, 49, 93, 13);
		getContentPane().add(lblNombre);
		
		
		chckbxControlInventario.setBounds(10, 110, 151, 21);
		getContentPane().add(chckbxControlInventario);
		
		
		chckbxCompras.setBounds(10, 133, 151, 21);
		getContentPane().add(chckbxCompras);
		
		txtfDescripcion = new JTextField();
		scrollPane.setViewportView(txtfDescripcion);
		txtfDescripcion.setColumns(10);
		
		
		cbRoles.setBounds(10, 10, 204, 21);
		getContentPane().add(cbRoles);
		chckbxVentas.setBounds(10, 156, 151, 21);
		getContentPane().add(chckbxVentas);
		
		chckbxCuentasCorrientes.setBounds(10, 179, 151, 21);
		getContentPane().add(chckbxCuentasCorrientes);
		
		txtfNombreRol = new JTextField();
		txtfNombreRol.setBounds(10, 46, 204, 19);
		getContentPane().add(txtfNombreRol);
		txtfNombreRol.setColumns(10);
		
		chckbxSueldos.setBounds(10, 202, 151, 21);
		getContentPane().add(chckbxSueldos);
		
		chckbxUsuarios.setBounds(10, 225, 151, 21);
		getContentPane().add(chckbxUsuarios);
		checkBoxSelected(false);
		
		cbRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxSetSelected();
			}
		});
		
		LinkedList<Rol> roles = DAORol.findAll();
		for (Rol rol2 : roles) {
			if(rol2.getId_rol() !=0) {
			cbRoles.addItem((Rol)rol2);
			}
		}
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setForeground(new Color(0, 128, 0));
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seguro = JOptionPane.showConfirmDialog(null, "¿Seguro que desea modificar los datos del rol seleccionado?");
				if(seguro == 0) {
				boolean[] Existen= {false,false,false,false,false,false};
				Rol rol = (Rol)cbRoles.getSelectedItem();
				rol.setDescrpicion_Rol(txtfDescripcion.getText());
				rol.setNombre_Rol(txtfNombreRol.getText());
				cbRoles.setSelectedItem(rol);
				DAORol.update(rol.getId_rol(), txtfNombreRol.getText(),txtfDescripcion.getText());
				LinkedList<RolFuncion> RFs = DAORFuncion.selctRAsigned(rol.getId_rol());
				JOptionPane.showMessageDialog(null, "Rol modificado correctamente");
				roles.clear();
				cbRoles.removeAllItems();
				LinkedList<Rol> roles = DAORol.findAll();
				for (Rol rol2 : roles) {
					if(rol2.getId_rol() !=0) {
					cbRoles.addItem((Rol)rol2);
					}
				}
				cbRoles.setSelectedIndex(roles.indexOf(roles.getLast()));
				for (RolFuncion RF : RFs){
					int Checks = RF.getId_funcion();
					switch(Checks) {
					case 1:
						Existen[0]=true;
						if(!chckbxControlInventario.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Inventario deshabilitado");
	  						DAORFuncion.delteRF(rol.getId_rol(), 1);
						}
						break;
					case 2:
						Existen[1]=true;
						if(!chckbxVentas.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Ventas deshabilitado");
	  						DAORFuncion.delteRF(rol.getId_rol(), 2);
						}
						break;
					case 3:
						Existen[2]=true;
						if(!chckbxCompras.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Compras deshabilitado");
	  						DAORFuncion.delteRF(rol.getId_rol(), 3);
						}
						break;
					case 4:
						Existen[3]=true;
						if(!chckbxCuentasCorrientes.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Cuentas corrientes deshabilitados");
	  						DAORFuncion.delteRF(rol.getId_rol(), 4);
						}
						break;
					case 5:
						Existen[4]=true;
						if(!chckbxSueldos.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Sueldos deshabilitado");
	  						DAORFuncion.delteRF(rol.getId_rol(), 5);
						}
						break;
					case 6:
						Existen[5]=true;
						if(!chckbxUsuarios.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Usuarios deshabilitado");
	  						DAORFuncion.delteRF(rol.getId_rol(), 6);
						}
						break;
					}
					}
					if(Existen[0]==false) {
						if(chckbxControlInventario.isSelected()) {
							JOptionPane.showMessageDialog(null, "Inventario estaba deshabilitado pero ya se habilitó");
							DAORFuncion.insert(rol.getId_rol(), 1);
						}
					}
					if(Existen[1]==false) {
						if(chckbxVentas.isSelected()) {
							JOptionPane.showMessageDialog(null, "Ventas estaba deshabilitado pero ya se habilitó");
							DAORFuncion.insert(rol.getId_rol(), 2);
						}
					}
					if(Existen[2]==false) {
						if(chckbxCompras.isSelected()) {
							JOptionPane.showMessageDialog(null, "Compras estaba deshabilitado pero ya se habilitó");
							DAORFuncion.insert(rol.getId_rol(), 3);
						}
					}
					if(Existen[3]==false) {
						if(chckbxCuentasCorrientes.isSelected()) {
							JOptionPane.showMessageDialog(null, "Cuentas corrientes estaba deshabilitado pero ya se habilitó");
							DAORFuncion.insert(rol.getId_rol(), 4);
						}
					}
					if(Existen[4]==false) {
						if(chckbxSueldos.isSelected()) {
							JOptionPane.showMessageDialog(null, "Sueldos estaba deshabilitado pero ya se habilitó");
							DAORFuncion.insert(rol.getId_rol(), 5);
						}
					}
					if(Existen[5]==false) {
						if(chckbxUsuarios.isSelected()) {
							JOptionPane.showMessageDialog(null, "Usuarios estaba deshabilitado pero ya se habilitó");
							DAORFuncion.insert(rol.getId_rol(), 6);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Modificación de rol cancelada");
				}
			}
		});
		btnGuardarCambios.setBounds(176, 179, 141, 21);
		getContentPane().add(btnGuardarCambios);
		
		JLabel lblSeleccionarRol = new JLabel("Seleccionar rol");
		lblSeleccionarRol.setBounds(224, 14, 93, 13);
		getContentPane().add(lblSeleccionarRol);
		
		
		checkBoxSetSelected();
		
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		scrollPane.setRowHeaderView(lblDescripcin);
		;
		Rol rol = ((Rol)cbRoles.getSelectedItem());
		txtfNombreRol.setText(rol.getNombre_Rol());
		txtfDescripcion.setText(rol.getDescrpicion_Rol());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(455, 225, 142, 21);
		
		getContentPane().add(btnNewButton);
		btnGuardarCambios_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxSetSelected();
			}
		});
		btnGuardarCambios_1.setBounds(176, 225, 141, 21);
		
		getContentPane().add(btnGuardarCambios_1);
		
		JButton btnNewButton_1 = new JButton("Eliminar rol");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rol rol = (Rol)cbRoles.getSelectedItem();
				int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el rol seleccionado?");
				if(i==0) {
					DAORol.drop(rol.getId_rol());
					JOptionPane.showMessageDialog(null, "Rol eliminado correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Proceso de eliminacion cancelado");
				}
			}
		});
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(326, 10, 114, 21);
		getContentPane().add(btnNewButton_1);
		
	}
	private void checkBoxSelected(boolean seleccionado) {
		chckbxControlInventario.setSelected(seleccionado);
		chckbxVentas.setSelected(seleccionado);
		chckbxCompras.setSelected(seleccionado);
		chckbxCuentasCorrientes.setSelected(seleccionado);
		chckbxSueldos.setSelected(seleccionado);
		chckbxUsuarios.setSelected(seleccionado);
	}
	private void checkBoxSetSelected() {
		checkBoxSelected(false);
		Rol rol = ((Rol)cbRoles.getSelectedItem());
		txtfNombreRol.setText(rol.getNombre_Rol());
		txtfDescripcion.setText(rol.getDescrpicion_Rol());
		LinkedList<RolFuncion> RFs = new LinkedList<RolFuncion>();
		RFs= DAORFuncion.selctRAsigned(rol.getId_rol());
		for (RolFuncion rolFuncion : RFs) {
			int Checks = rolFuncion.getId_funcion();
			switch(Checks) {
			case 1:
				chckbxControlInventario.setSelected(true);
				break;
			case 2:
				chckbxVentas.setSelected(true);
				break;
			case 3:
				chckbxCompras.setSelected(true);
				break;
			case 4:
				chckbxCuentasCorrientes.setSelected(true);
				break;
			case 5:
				chckbxSueldos.setSelected(true);
				break;
			case 6:
				chckbxUsuarios.setSelected(true);
				break;
			}
		}
	}
}
