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
		
		
		chckbxControlInventario.setBounds(10, 110, 217, 21);
		getContentPane().add(chckbxControlInventario);
		
		
		chckbxCompras.setBounds(10, 133, 217, 21);
		getContentPane().add(chckbxCompras);
		
		txtfDescripcion = new JTextField();
		scrollPane.setViewportView(txtfDescripcion);
		txtfDescripcion.setColumns(10);
		
		LinkedList<Rol> ListaRoles = DAORol.findAll();
		for (Rol rol : ListaRoles) {
			cbRoles.addItem((Rol)rol);
		}
		
		cbRoles.setBounds(10, 10, 204, 21);
		getContentPane().add(cbRoles);
		chckbxVentas.setBounds(10, 156, 217, 21);
		getContentPane().add(chckbxVentas);
		
		chckbxCuentasCorrientes.setBounds(10, 179, 147, 21);
		getContentPane().add(chckbxCuentasCorrientes);
		
		txtfNombreRol = new JTextField();
		txtfNombreRol.setBounds(10, 46, 204, 19);
		getContentPane().add(txtfNombreRol);
		txtfNombreRol.setColumns(10);
		
		chckbxSueldos.setBounds(10, 202, 159, 21);
		getContentPane().add(chckbxSueldos);
		
		chckbxUsuarios.setBounds(10, 225, 141, 21);
		getContentPane().add(chckbxUsuarios);
		checkBoxSelected(false);
		
		cbRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxSetSelected();
			}
		});
		
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean[] Existen= {false,false,false,false,false,false};
				Rol rol = (Rol)cbRoles.getSelectedItem();
				LinkedList<RolFuncion> RFs = DAORFuncion.selctRAsigned(rol.getId_rol());
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
		Rol rol = ((Rol)cbRoles.getSelectedItem());
		txtfNombreRol.setText(rol.getNombre_Rol());
		txtfDescripcion.setText(rol.getDescrpicion_Rol());
		
		
		checkBoxSetSelected();
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
