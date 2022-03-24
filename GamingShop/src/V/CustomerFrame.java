package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_surname;
	private JTextField textField_phone;

	ArrayList<CustomerDB> list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1023, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 636, 515);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//When click at ech row , display data into left-hand form
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				System.out.println(index);
				
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = table.getValueAt(index, 1).toString();
				String surname = table.getValueAt(index, 2).toString();
				String phone = table.getValueAt(index, 3).toString();
				
				textField_id.setText(String.valueOf(id));
				textField_name.setText(name);
				textField_surname.setText(surname);
				textField_phone.setText(phone);
				
			}
		});
		
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(657, 8, 32, 21);
		contentPane.add(lblNewLabel);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBackground(Color.ORANGE);
		textField_id.setBounds(746, 9, 157, 19);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(656, 50, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(746, 49, 157, 19);
		contentPane.add(textField_name);
		
		JLabel lblNewLabel_1_1 = new JLabel("surname");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(656, 99, 62, 13);
		contentPane.add(lblNewLabel_1_1);
		
		textField_surname = new JTextField();
		textField_surname.setColumns(10);
		textField_surname.setBounds(746, 98, 157, 19);
		contentPane.add(textField_surname);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("phone");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(656, 148, 45, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(746, 145, 157, 19);
		contentPane.add(textField_phone);
		
		JButton btn_SAVE = new JButton("SAVE NEW CUSTOMER");
		btn_SAVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				CustomerDB x = new CustomerDB(0,textField_name.getText().trim(),textField_surname.getText().trim(),textField_phone.getText().trim());
				CustomerManager.saveNewCustomer(x);
				load();
				
				JOptionPane.showMessageDialog(CustomerFrame.this, "Save new customer");
				
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
			}
		});
		btn_SAVE.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btn_SAVE.setBounds(656, 206, 175, 33);
		contentPane.add(btn_SAVE);
		
		JButton btn_EDIT = new JButton("EDIT");
		btn_EDIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustomerDB x = new CustomerDB(Integer.parseInt(textField_id.getText()),textField_name.getText().trim(),textField_surname.getText().trim(),textField_phone.getText().trim());
				CustomerManager.editCustomer(x);
				load();
						
				JOptionPane.showMessageDialog(CustomerFrame.this, "Edit"+textField_id.getText()+" already");
				
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
			}
		});
		btn_EDIT.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btn_EDIT.setBounds(656, 249, 175, 33);
		contentPane.add(btn_EDIT);
		
		JButton btn_DELETE = new JButton("DELETE");
		btn_DELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(CustomerFrame.this, "Do you really want to delete ?","DELETE ?",JOptionPane.YES_NO_CANCEL_OPTION))
				{
					CustomerDB x = new CustomerDB(Integer.parseInt(textField_id.getText()),textField_name.getText().trim(),textField_surname.getText().trim(),textField_phone.getText().trim());
					CustomerManager.deleteCustomer(x);
					load();
							
					JOptionPane.showMessageDialog(CustomerFrame.this, "Delete customer id = "+x.id+" already");
					
					textField_id.setText("");
					textField_name.setText("");
					textField_surname.setText("");
					textField_phone.setText("");
				}
			}
		});
		btn_DELETE.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btn_DELETE.setBounds(656, 292, 175, 33);
		contentPane.add(btn_DELETE);
		
		load();
		
	}
	
	//upload database to table 
	public void load()
	{
		list = CustomerManager.getAllCustomer();
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("surname");
		model.addColumn("phone");
		for(CustomerDB c : list)
		{
			model.addRow(new Object[] {c.id,c.name,c.surname,c.phone});
			
		}
		
		table.setModel(model);
	}
}
