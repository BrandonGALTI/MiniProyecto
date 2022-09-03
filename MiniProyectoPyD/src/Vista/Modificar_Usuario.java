package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Modificar_Usuario extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Usuario frame = new Modificar_Usuario();
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
	public Modificar_Usuario() {
		setBounds(100, 100, 450, 469);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 438, 430);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(119, 219, 142, 19);
		panel.add(textField);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(119, 306, 142, 19);
		panel.add(dateChooser);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(10, 222, 75, 16);
		panel.add(lblDocumento);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 248, 142, 19);
		panel.add(textField_1);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(10, 251, 75, 16);
		panel.add(lblNombre);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(271, 248, 142, 19);
		panel.add(textField_2);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setBounds(10, 280, 75, 16);
		panel.add(lblApellidos);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 277, 142, 19);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(271, 277, 142, 19);
		panel.add(textField_4);
		
		JLabel lblFecNac = new JLabel("Fecha de nacimiento");
		lblFecNac.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecNac.setBounds(10, 309, 119, 16);
		panel.add(lblFecNac);
		
		textField_5 = new JTextField();
		textField_5.setToolTipText("");
		textField_5.setColumns(10);
		textField_5.setBounds(119, 339, 294, 19);
		panel.add(textField_5);
		
		JLabel lblClave = new JLabel("Contrase\u00F1a");
		lblClave.setHorizontalAlignment(SwingConstants.LEFT);
		lblClave.setBounds(10, 340, 75, 16);
		panel.add(lblClave);
		
		textField_6 = new JTextField();
		textField_6.setToolTipText("");
		textField_6.setColumns(10);
		textField_6.setBounds(119, 368, 294, 19);
		panel.add(textField_6);
		
		JLabel lblMail = new JLabel("Email");
		lblMail.setHorizontalAlignment(SwingConstants.LEFT);
		lblMail.setBounds(10, 369, 75, 16);
		panel.add(lblMail);
		
		JComboBox cbRol = new JComboBox();
		cbRol.setToolTipText("");
		cbRol.setBounds(119, 397, 294, 21);
		panel.add(cbRol);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 401, 45, 13);
		panel.add(lblRol);

	}
}
