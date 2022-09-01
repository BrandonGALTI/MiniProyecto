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

import Conexion.DataManager;
import Modelo.EnumFuncionalidades;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificacionRoles extends JInternalFrame {
	private JTable table;
	private JTextField txtfNombreRol;
	private JTextField txtfDescripcion;

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
		
		JCheckBox chckbxControlInventario = new JCheckBox("Control de inventario");
		chckbxControlInventario.setBounds(10, 110, 217, 21);
		getContentPane().add(chckbxControlInventario);
		
		JCheckBox chckbxCompras = new JCheckBox("Compras");
		chckbxCompras.setBounds(10, 133, 217, 21);
		getContentPane().add(chckbxCompras);
		
		txtfDescripcion = new JTextField();
		scrollPane.setViewportView(txtfDescripcion);
		txtfDescripcion.setColumns(10);
		
	
		JCheckBox chckbxVentas = new JCheckBox("Ventas");
		chckbxVentas.setBounds(10, 156, 217, 21);
		getContentPane().add(chckbxVentas);
		
		JCheckBox chckbxCuentasCorrientes = new JCheckBox("Cuentas corrientes");
		chckbxCuentasCorrientes.setBounds(10, 179, 147, 21);
		getContentPane().add(chckbxCuentasCorrientes);
		
		txtfNombreRol = new JTextField();
		txtfNombreRol.setBounds(10, 46, 204, 19);
		getContentPane().add(txtfNombreRol);
		txtfNombreRol.setColumns(10);
		
		JCheckBox chckbxSueldos = new JCheckBox("Sueldos");
		chckbxSueldos.setBounds(10, 202, 159, 21);
		getContentPane().add(chckbxSueldos);
		
		JCheckBox chckbxUsuarios = new JCheckBox("Usuarios");
		chckbxUsuarios.setBounds(10, 225, 141, 21);
		getContentPane().add(chckbxUsuarios);
		
		JComboBox cbRoles = new JComboBox();
		cbRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Rol_Funcion = "SELECT * FROM ROL_FUNCION JOIN ROL ON rol.id_rol=rol_funcion.id_rol WHERE NOMBRE_ROL='" + (cbRoles.getSelectedItem())+"'";
				try {
				PreparedStatement RolesFuncion = DataManager.databaseConnection.prepareStatement(Rol_Funcion);
				ResultSet RolesFuncionResult = RolesFuncion.executeQuery();
				chckbxControlInventario.setSelected(false);
				chckbxVentas.setSelected(false);
				chckbxCompras.setSelected(false);
				chckbxCuentasCorrientes.setSelected(false);
				chckbxSueldos.setSelected(false);
				chckbxUsuarios.setSelected(false);
				String Rol_NomDesc = ("SELECT * FROM ROL WHERE NOMBRE_ROL='"+ cbRoles.getSelectedItem()+"'");
				PreparedStatement RolesNomDesc = DataManager.databaseConnection.prepareStatement(Rol_NomDesc);
				ResultSet RolesNomDescResult = RolesNomDesc.executeQuery();
				while(RolesNomDescResult.next()) {
					txtfNombreRol.setText(RolesNomDescResult.getString("NOMBRE_ROL"));
					txtfDescripcion.setText(RolesNomDescResult.getString("DESCRIPCION"));
				}
				while(RolesFuncionResult.next()) {
					int Checks = RolesFuncionResult.getInt("ID_FUNCIONALIDAD");
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
				}catch(SQLException l) {
				}
			}
		});
		cbRoles.setBounds(10, 10, 204, 21);
		getContentPane().add(cbRoles);
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean[] Existen= {false,false,false,false,false,false};
				try {
				String Rol_Funcion = "SELECT * FROM ROL_FUNCION JOIN ROL ON rol.id_rol=rol_funcion.id_rol WHERE NOMBRE_ROL='" + cbRoles.getSelectedItem() +"'";
				PreparedStatement RolesFuncion = DataManager.databaseConnection.prepareStatement(Rol_Funcion);
				ResultSet RolesFuncionResult = RolesFuncion.executeQuery();
				while(RolesFuncionResult.next()) {
					int Checks = RolesFuncionResult.getInt("ID_FUNCIONALIDAD");
					switch(Checks) {
					case 1:
						Existen[0]=true;
						if(!chckbxControlInventario.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Inventario deshabilitado");
						}
						break;
					case 2:
						Existen[1]=true;
						if(!chckbxVentas.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Ventas deshabilitado");
						}
						break;
					case 3:
						Existen[2]=true;
						if(!chckbxCompras.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Compras deshabilitado");
						}
						break;
					case 4:
						Existen[3]=true;
						if(!chckbxCuentasCorrientes.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Cuentas corrientes deshabilitados");
						}
						break;
					case 5:
						Existen[4]=true;
						if(!chckbxSueldos.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Sueldos deshabilitado");
						}
						break;
					case 6:
						Existen[5]=true;
						if(!chckbxUsuarios.isSelected()) {
	  						JOptionPane.showMessageDialog(null, "Usuarios deshabilitado");
						}						break;
					}
				}
				if(Existen[0]==false) {
					if(chckbxControlInventario.isSelected()) {
						JOptionPane.showMessageDialog(null, "Inventario estaba deshabilitado pero ya se habilitó");
						}
					}
				if(Existen[1]==false) {
					if(chckbxVentas.isSelected()) {
						JOptionPane.showMessageDialog(null, "Ventas estaba deshabilitado pero ya se habilitó");
						}
					}
				if(Existen[2]==false) {
					if(chckbxCompras.isSelected()) {
						JOptionPane.showMessageDialog(null, "Compras estaba deshabilitado pero ya se habilitó");
						}
					}
				if(Existen[3]==false) {
					if(chckbxCuentasCorrientes.isSelected()) {
						JOptionPane.showMessageDialog(null, "Cuentas corrientes estaba deshabilitado pero ya se habilitó");
						}
					}
				if(Existen[4]==false) {
					if(chckbxSueldos.isSelected()) {
						JOptionPane.showMessageDialog(null, "Sueldos estaba deshabilitado pero ya se habilitó");
						}
					}
				if(Existen[5]==false) {
					if(chckbxUsuarios.isSelected()) {
						JOptionPane.showMessageDialog(null, "Usuarios estaba deshabilitado pero ya se habilitó");
						}
					}
				}catch(SQLException u) {
					
				}
			}
		});
		btnGuardarCambios.setBounds(176, 225, 141, 21);
		getContentPane().add(btnGuardarCambios);
		
		JLabel lblSeleccionarRol = new JLabel("Seleccionar rol");
		lblSeleccionarRol.setBounds(224, 14, 93, 13);
		getContentPane().add(lblSeleccionarRol);
		
		
	
		
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		scrollPane.setRowHeaderView(lblDescripcin);
		;
		
		LinkedList<String> ListaRoles = new LinkedList<String>();
		String Roles = "SELECT * FROM ROL";
		try {
			PreparedStatement RolesStatement = DataManager.databaseConnection.prepareStatement(Roles);
			ResultSet RolesResultado = RolesStatement.executeQuery();
			while(RolesResultado.next()) {
				ListaRoles.add(RolesResultado.getString("NOMBRE_ROL"));
				cbRoles.addItem(RolesResultado.getString("NOMBRE_ROL"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
