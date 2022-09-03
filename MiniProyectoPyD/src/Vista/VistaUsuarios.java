package Vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conexion.DAOPersonas;
import Conexion.DAORol;
import Modelo.Personas;
import Modelo.Rol;

import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.awt.Color;


public class VistaUsuarios extends JInternalFrame {
	private JTable table= new JTable();
	private Personas selectedPersona = new Personas();
	private JTextField txtfDocumento;
	private JTextField txtfNombre1;
	private JTextField txtfNombre2;
	private JTextField txtfApellido1;
	private JTextField txtfApellido2;
	private JTextField txtfClave;
	private JTextField txtfEmail;
	private JDateChooser dateChooser = new JDateChooser();
	private JComboBox cbRol = new JComboBox();
	private LinkedList<Integer> indexes = new LinkedList<Integer>();
	LinkedList<Personas> People;
	LinkedList<String> Cedulas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaUsuarios frame = new VistaUsuarios();
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
	public VistaUsuarios() {
		setTitle("Modificacion/Baja de usuarios");
		

			
			DefaultTableModel modelo= new DefaultTableModel();
			//crea un array que contiene los nombre de las columnas
			final String[] columnNames = {"Nombre","Apellido","Cedula"};
			// insertamos las columnas
			for(int column = 0; column < columnNames.length; column++){
				//agrega las columnas a la tabla
				modelo.addColumn(columnNames[column]);
			}
			// Se crea un array que será una de las filas de la tabla. 
			Object [] fila = new Object[columnNames.length]; 
			//Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
			People = DAOPersonas.findAll();
			Cedulas = new LinkedList<String>();
			for (Personas persona : People) {
				String nombre = persona.getNombre1();
				String apellido= persona.getApellido1();
				String cedula = persona.getDocumento();
				fila[0] = nombre;
				fila[1] = apellido;
				fila[2] = cedula;
				modelo.addRow(fila); 
				Cedulas.add(cedula);
				table.setModel(modelo);
			}
			getContentPane().setLayout(null);
			table = new JTable(modelo);
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = table.getSelectedRow();
					selectedPersona = DAOPersonas.findPersona(DAOPersonas.findMail(Cedulas.get(row)));
					cargarDatos(selectedPersona);
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setSurrendersFocusOnKeystroke(true);
			table.setFillsViewportHeight(true);
			table.setBounds(0, 0, 100, 100);
		//	getContentPane().add(table);
			table.setPreferredScrollableViewportSize(new Dimension(100, 100));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(9, 10, 252, 138);
			getContentPane().add(scrollPane);
			scrollPane.setViewportView(table);
			
			JButton btnNewButton = new JButton("Confirmar cambios");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!((txtfDocumento.getText().equals("")) || (txtfApellido1.getText().equals("")) || (txtfApellido2.getText().equals("")) || (txtfNombre1.getText().equals("")) || (txtfNombre2.getText().equals("")) || (dateChooser.getDate()==null) || (txtfClave.getText().equals("")) || (txtfEmail.getText().equals("")))){
						if(validarDocumento(txtfDocumento.getText())) {
							Personas update = selectedPersona;
							int i =JOptionPane.showConfirmDialog(null, "¿Seguro que quiere modifcar los datos del usuario " + update.getNombre1() + " " + update.getApellido1() + "("+ update.getDocumento()+")?");
							if(i == 0) {
							update.setApellido1(txtfApellido1.getText());
							update.setApellido2(txtfApellido2.getText());
							update.setNombre1(txtfNombre1.getText());
							update.setNombre2(txtfNombre2.getText());
							update.setDocumento(txtfDocumento.getText());
							update.setClave(txtfClave.getText());
							update.setID_ROL(((Rol)cbRol.getSelectedItem()).getId_rol());
							update.setFechaNac(dateToLocal(dateChooser.getDate()));
							update.setMail(txtfEmail.getText());
							DAOPersonas.edit(update);
							JOptionPane.showMessageDialog(null, "Cambios confirmados");
		//					clearTxts();
							}else {
								JOptionPane.showMessageDialog(null, "Proceso cancelado");
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Complete todos los campos para continuar");
					}
				}
			});
			btnNewButton.setBounds(304, 224, 142, 21);
			getContentPane().add(btnNewButton);
			
			txtfDocumento = new JTextField();
			txtfDocumento.setColumns(10);
			txtfDocumento.setBounds(380, 10, 142, 19);
			getContentPane().add(txtfDocumento);
			
			
			dateChooser.setBounds(380, 39, 142, 19);
			getContentPane().add(dateChooser);
			
			JLabel lblDocumento = new JLabel("Documento");
			lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDocumento.setBounds(271, 13, 99, 16);
			getContentPane().add(lblDocumento);
			
			txtfNombre1 = new JTextField();
			txtfNombre1.setColumns(10);
			txtfNombre1.setBounds(304, 159, 142, 19);
			getContentPane().add(txtfNombre1);
			
			JLabel lblNombre = new JLabel("Nombres");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(229, 160, 65, 16);
			getContentPane().add(lblNombre);
			
			txtfNombre2 = new JTextField();
			txtfNombre2.setColumns(10);
			txtfNombre2.setBounds(456, 159, 142, 19);
			getContentPane().add(txtfNombre2);
			
			JLabel lblApellidos = new JLabel("Apellidos");
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidos.setBounds(229, 191, 65, 16);
			getContentPane().add(lblApellidos);
			
			txtfApellido1 = new JTextField();
			txtfApellido1.setColumns(10);
			txtfApellido1.setBounds(304, 188, 142, 19);
			getContentPane().add(txtfApellido1);
			
			txtfApellido2 = new JTextField();
			txtfApellido2.setColumns(10);
			txtfApellido2.setBounds(456, 188, 142, 19);
			getContentPane().add(txtfApellido2);
			
			JLabel lblFecNac = new JLabel("Fecha de nacimiento");
			lblFecNac.setHorizontalAlignment(SwingConstants.CENTER);
			lblFecNac.setBounds(281, 42, 99, 16);
			getContentPane().add(lblFecNac);
			
			txtfClave = new JTextField();
			txtfClave.setToolTipText("");
			txtfClave.setColumns(10);
			txtfClave.setBounds(380, 68, 218, 19);
			getContentPane().add(txtfClave);
			
			JLabel lblClave = new JLabel("Contrase\u00F1a");
			lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
			lblClave.setBounds(271, 69, 99, 16);
			getContentPane().add(lblClave);
			
			txtfEmail = new JTextField();
			txtfEmail.setToolTipText("");
			txtfEmail.setColumns(10);
			txtfEmail.setBounds(380, 97, 218, 19);
			getContentPane().add(txtfEmail);
			
			JLabel lblMail = new JLabel("Email");
			lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMail.setBounds(271, 98, 99, 16);
			getContentPane().add(lblMail);
			
			
			cbRol.setToolTipText("");
			cbRol.setBounds(304, 126, 294, 21);
			getContentPane().add(cbRol);
			
			for (Rol r : DAORol.findAll()) {
				cbRol.addItem(r);
				indexes.add(r.getId_rol());
			}
			
			JLabel lblRol = new JLabel("Rol");
			lblRol.setHorizontalAlignment(SwingConstants.CENTER);
			lblRol.setBounds(271, 124, 30, 24);
			getContentPane().add(lblRol);
			
			JButton btnNewButton_1 = new JButton("Cancelar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!(selectedPersona.getApellido1().equals("")||selectedPersona.getApellido2().equals("")||selectedPersona.getNombre1().equals("")||selectedPersona.getNombre2().equals("")||selectedPersona.getDocumento().equals(null)||selectedPersona.getClave().equals("")||selectedPersona.getFechaNac().equals(null)||selectedPersona.getMail().equals(""))) {
						cargarDatos(selectedPersona);
					}
				}
			});
			btnNewButton_1.setBounds(456, 224, 142, 21);
			getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_1_1 = new JButton("Cerrar");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_1_1.setBounds(9, 224, 162, 21);
			getContentPane().add(btnNewButton_1_1);
			
			JButton btnNewButton_1_2 = new JButton("Eliminar usuario");
			btnNewButton_1_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					
					int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el rol seleccionado?");
					if(i==0) {
						DAOPersonas.drop(selectedPersona.getId_persona());
						JOptionPane.showMessageDialog(null, "Rol eliminado correctamente");
						refreshModel();
						clearTxts();
						selectedPersona = new Personas();
					}else {
						JOptionPane.showMessageDialog(null, "Proceso de eliminacion cancelado");
					}
				}
			});
			btnNewButton_1_2.setForeground(Color.RED);
			btnNewButton_1_2.setBackground(Color.WHITE);
			btnNewButton_1_2.setBounds(71, 158, 127, 21);
			getContentPane().add(btnNewButton_1_2);
			
		}
	private void cargarDatos(Personas p) {
		txtfEmail.setText(p.getMail());
		txtfNombre1.setText(p.getNombre1());
		txtfNombre2.setText(p.getNombre2());
		txtfApellido1.setText(p.getApellido1());
		txtfApellido2.setText(p.getApellido2());
		txtfDocumento.setText(p.getDocumento());
		txtfClave.setText(p.getClave());
		ZoneId defaultZoneId = ZoneId.systemDefault();
	    LocalDate localDate = p.getFechaNac();
	    Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		dateChooser.setDate(date);
		if(indexes.contains(p.getID_ROL())) {
			cbRol.setSelectedIndex(indexes.indexOf(p.getID_ROL()));
		}else {
			
		}
	}
	public static Date localToDate(LocalDate r) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
	    LocalDate localDate = r;
	    Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
	    return date;
	}
	public static LocalDate dateToLocal(Date r) {
		Instant instant = r.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate fecha = zone.toLocalDate();
		return fecha;
	}
	private void clearTxts() {
		txtfDocumento.setText(null);
		txtfNombre1.setText(null);
		txtfNombre2.setText(null);
		txtfApellido2.setText(null);
		txtfApellido1.setText(null);
		txtfClave.setText(null);
		txtfEmail.setText(null);
		cbRol.setSelectedIndex(0);
		dateChooser.setDate(null);
	}
	public static boolean validarDocumento(String a) {
		boolean b = false;
		if(a.length()==8 || a.length()==7) {
			try {
			int prueba= Integer.parseInt(a);
			b= true;
			}catch(NumberFormatException r) {
				JOptionPane.showMessageDialog(null, "El documento debe estar compuesto únicamente \npor números, sin espacios ni guiones");
			}
		}else {
			if(a.length()>8) {
				JOptionPane.showMessageDialog(null, "El documento ingresado no debe contener mas de ocho caracteres");
			}else if(a.length()<7) {
				JOptionPane.showMessageDialog(null, "El documento ingresado debe contener mas de seis caracteres");
			}
		}
		return b;
	}
	
	private void refreshModel() {
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Nombre","Apellido","Cedula"};
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		Object [] fila = new Object[columnNames.length]; 
		People = DAOPersonas.findAll();
		Cedulas = new LinkedList<String>();
		for (Personas persona : People) {
			String nombre = persona.getNombre1();
			String apellido= persona.getApellido1();
			String cedula = persona.getDocumento();
			fila[0] = nombre;
			fila[1] = apellido;
			fila[2] = cedula;
			modelo.addRow(fila); 
			Cedulas.add(cedula);
		}
		table.setModel(modelo);
	}
	
}
