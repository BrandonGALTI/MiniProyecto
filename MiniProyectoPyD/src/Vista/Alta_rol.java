package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Conexion.DAOPersonas;
import Conexion.DAORol;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

public class Alta_rol extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alta_rol frame = new Alta_rol();
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
	public Alta_rol() {
		try {
			setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Alta de roles");
		setBounds(100, 100, 620, 300);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(196, 57, 196, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(196, 101, 196, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(239, 171, 109, 21);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(textField.getText().equals("") || textField_1.getText().equals(""))) {
					int i = JOptionPane.showConfirmDialog(null, "¿Are you sure about that?");
					if(i==0) {
					DAORol.insert(textField.getText(), textField_1.getText());
					JOptionPane.showMessageDialog(null, "Rol creado con éxito");
					textField.setText("");
					textField_1.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Proceso de creacion de rol cancelado");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debes completar todos los campos para crear un nuevo rol");
				}
			}
		});
		btnNewButton_1.setBounds(239, 130, 109, 21);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Nombre del rol");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(239, 44, 109, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(239, 86, 109, 13);
		getContentPane().add(lblNewLabel_1);

	}
}
