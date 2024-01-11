package lab6666;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class frame666 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Work_with_files file = new Work_with_files();
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JCheckBox chckbxNewCheckBox;
	private list_of_objects cookers = new list_of_objects();
	private JLabel lblNewLabel_4;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_5;
	private JTextField textField_1;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_7;
	private JCheckBox chckbxNewCheckBox_1;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame666 frame = new frame666();
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
	public frame666() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Enter input file name");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 23, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
				file.SetInputName(str);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 20, SpringLayout.WEST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Enter file type");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton = new JButton(".txt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file.SetTxtFormat();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Is your file archived?");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 17, SpringLayout.SOUTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 37, SpringLayout.SOUTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_2, 19, SpringLayout.EAST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("That's all about input file");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, -91, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblNewLabel_3);
		
		chckbxNewCheckBox = new JCheckBox("Yes");
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 9, SpringLayout.SOUTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 0, SpringLayout.WEST, lblNewLabel);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cookers.Read(file);
			}
			
		});
		contentPane.add(chckbxNewCheckBox);
		
		lblNewLabel_4 = new JLabel("Choose the way of comparison");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 6, SpringLayout.SOUTH, chckbxNewCheckBox);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_4);
		
		btnNewButton_1 = new JButton("by cost");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 6, SpringLayout.SOUTH, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, lblNewLabel);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cookers.Sort_by_cost();
			}
		});
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("by volume");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cookers.Sort_by_volume();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_2, 6, SpringLayout.EAST, btnNewButton_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_2, 0, SpringLayout.SOUTH, btnNewButton_1);
		contentPane.add(btnNewButton_2);
		
		lblNewLabel_5 = new JLabel("Enter output file name");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5, 162, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_5, 0, SpringLayout.SOUTH, lblNewLabel);
		contentPane.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField_1.getText();
				file.SetOutputName(str);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, lblNewLabel_5);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Choose type of file");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_6, 0, SpringLayout.WEST, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_6, 0, SpringLayout.SOUTH, lblNewLabel_1);
		contentPane.add(lblNewLabel_6);
		
		btnNewButton_3 = new JButton(".txt");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file.SetOutputTxtFormat();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_3, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_3, 0, SpringLayout.WEST, lblNewLabel_5);
		contentPane.add(btnNewButton_3);
		
		lblNewLabel_7 = new JLabel("That's all about your output file");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_7, 0, SpringLayout.NORTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_7, 0, SpringLayout.WEST, lblNewLabel_5);
		contentPane.add(lblNewLabel_7);
		
		chckbxNewCheckBox_1 = new JCheckBox("Yes");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file.Write(cookers);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxNewCheckBox_1, 0, SpringLayout.WEST, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, chckbxNewCheckBox_1, 0, SpringLayout.SOUTH, chckbxNewCheckBox);
		contentPane.add(chckbxNewCheckBox_1);
		
		btnNewButton_4 = new JButton(".xml");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file.SetOutputXMLFormat();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_4, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_4, 6, SpringLayout.EAST, btnNewButton_3);
		contentPane.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton(".xml");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file.SetXMLFormat();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_5, 6, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_5, 6, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_5);
	}

}
