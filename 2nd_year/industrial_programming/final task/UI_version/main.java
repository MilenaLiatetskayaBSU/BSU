package final_task;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String inputfilename, outputfilename;
	private String type;
	private int answers = 0;
	private int archieved = 0;
	private ArrayList<Double>result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		result = new ArrayList<>();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Enter inputfilename (without it's type)");
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputfilename = textField.getText();
			}
		});
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Choose your file type");
		
		JButton btnNewButton = new JButton(".txt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = ".txt";
				
			}
		});
		
		JButton btnNewButton_1 = new JButton(".json");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = ".json";
			}
		});
		
		JButton btnNewButton_2 = new JButton(".xml");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = ".xml";
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Additional information");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("File is archieved");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				archieved = 1;
			}
		});
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("File is encrypted");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answers = 1;
			}
		});
		
		JButton btnNewButton_3 = new JButton("Finish with input file settings");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(archieved == 1)
				{
					archiving arch = new archiving();
					inputfilename = arch.read(inputfilename);
				}
				
				if(type.equals(".txt"))
			     {
					Work_with_txt file = new Work_with_txt();
					result = file.Read(inputfilename+type, answers);
			     }
				
				if(type.equals(".json"))
				{
					Work_with_json file = new Work_with_json();
					result = file.Read(inputfilename+type, answers);
				}
				
				if(type.equals(".xml"))
				{
					Work_with_xml file = new Work_with_xml();
					result = file.Read( inputfilename+type, answers);
				}
				
				main2 a = new main2();
				a.Start2(result);
			}
			
			
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxNewCheckBox_1)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField, Alignment.LEADING)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addComponent(lblNewLabel_1)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton)
								.addGap(18)
								.addComponent(btnNewButton_1)
								.addGap(18)
								.addComponent(btnNewButton_2))
							.addComponent(lblNewLabel_2)
							.addComponent(chckbxNewCheckBox)
							.addComponent(btnNewButton_3)))
					.addContainerGap(535, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxNewCheckBox)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxNewCheckBox_1)
					.addGap(56)
					.addComponent(btnNewButton_3)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
