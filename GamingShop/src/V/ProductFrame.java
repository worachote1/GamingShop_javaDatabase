package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_price_per_unit;
	private JTextField textField_dest;
	private ImagePanel imagePanel;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductFrame frame = new ProductFrame();
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
	public ProductFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1038, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 1009, 535);
		contentPane.add(contentPane_1);
		contentPane_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 636, 515);
		contentPane_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(table.getSelectedRowCount()<1)
				{
					return;
				}
				int index = table.getSelectedRow();
				//System.out.println("prn => "+index);
				
				//Display image if click row of the table
				BufferedImage img  = list.get(index).product_images;
				if(img != null)
				{
					imagePanel.setImage(img);
				}
				
				//display data if click row of the table
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = table.getValueAt(index, 1).toString();
				double price_per_unit = Double.parseDouble(table.getValueAt(index, 2).toString());
				String dest = table.getValueAt(index, 3).toString();
				
				textField_id.setText(String.valueOf(id));
				textField_name.setText(name);
				textField_price_per_unit.setText(String.valueOf(price_per_unit));
				textField_dest.setText(dest);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("product_id");
		lblNewLabel.setBounds(657, 8, 79, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane_1.add(lblNewLabel);
		
		textField_id = new JTextField();
		textField_id.setBounds(799, 11, 157, 19);
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.ORANGE);
		contentPane_1.add(textField_id);
		
		JLabel lblNewLabel_1 = new JLabel("product_name");
		lblNewLabel_1.setBounds(656, 50, 109, 13);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane_1.add(lblNewLabel_1);
		
		textField_name = new JTextField();
		textField_name.setBounds(799, 49, 157, 19);
		textField_name.setColumns(10);
		contentPane_1.add(textField_name);
		
		JLabel lblNewLabel_1_1 = new JLabel("price per unit");
		lblNewLabel_1_1.setBounds(656, 99, 133, 13);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane_1.add(lblNewLabel_1_1);
		
		textField_price_per_unit = new JTextField();
		textField_price_per_unit.setBounds(799, 98, 157, 19);
		textField_price_per_unit.setColumns(10);
		contentPane_1.add(textField_price_per_unit);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("product_dest.");
		lblNewLabel_1_1_1.setBounds(656, 148, 133, 13);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane_1.add(lblNewLabel_1_1_1);
		
		textField_dest = new JTextField();
		textField_dest.setBounds(799, 147, 157, 19);
		textField_dest.setColumns(10);
		contentPane_1.add(textField_dest);
		
		JButton btn_SAVE = new JButton("SAVE NEW CUSTOMER");
		btn_SAVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductDB x = new ProductDB();
				x.product_id = 0;
				x.product_name = textField_name.getText().trim();
				x.product_description = textField_dest.getText().trim();
				x.price_per_unit = Double.parseDouble(textField_price_per_unit.getText().trim());
				
				x.product_images = (BufferedImage) imagePanel.getImage();
				
				ProductManager.saveProuct(x);
				
				textField_id.setText("");
				textField_name.setText("");
				textField_dest.setText("");
				textField_price_per_unit.setText("");
				
				JOptionPane.showMessageDialog(ProductFrame.this, "Save new product");
				load();
			}
		});
		btn_SAVE.setBounds(781, 406, 175, 33);
		btn_SAVE.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane_1.add(btn_SAVE);
		
		JButton btn_EDIT = new JButton("EDIT");
		btn_EDIT.setBounds(781, 449, 175, 33);
		btn_EDIT.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane_1.add(btn_EDIT);
		
		JButton btn_DELETE = new JButton("DELETE");
		btn_DELETE.setBounds(781, 492, 175, 33);
		btn_DELETE.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane_1.add(btn_DELETE);
		
		imagePanel = new ImagePanel();
		imagePanel.setBounds(689, 176, 297, 220);
		contentPane_1.add(imagePanel);
		
		JButton btnNewButton = new JButton("Brow img");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Create a file chooser
				JFileChooser fc = new JFileChooser();
				 
				fc.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("png","PNG image") );
				fc.addChoosableFileFilter(new OpenFileFilter("svg","Scalable Vector Graphic") );
				
				fc.setAcceptAllFileFilterUsed(false);
				//In response to a button click:
				int returnVal = fc.showOpenDialog(ProductFrame.this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					System.out.println("prn");
					
					File f= fc.getSelectedFile();
					//Open image
					try {
						BufferedImage bimg = ImageIO.read(f);
						imagePanel.setImage(bimg);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(651, 404, 114, 33);
		contentPane_1.add(btnNewButton);
		
		load();
	}

static ArrayList<ProductDB> list;
private static JTable table;
public static void load()
{
	list = ProductManager.getAllProducts();
	
	DefaultTableModel model = new DefaultTableModel();
	
	model.addColumn("product_id");
	model.addColumn("product_name");
	model.addColumn("price_per_unit");
	model.addColumn("product_description");
	
	for(ProductDB c : list)
	{
		model.addRow(new Object[] {c.product_id,c.product_name,c.price_per_unit,c.product_description});
		
	}
	
	table.setModel(model);
	
}

class OpenFileFilter extends FileFilter {

    String description = "";
    String fileExt = "";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public OpenFileFilter(String extension, String typeDescription) {
        fileExt = extension;
        this.description = typeDescription;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    @Override
    public String getDescription() {
        return description;
    }
}
}
