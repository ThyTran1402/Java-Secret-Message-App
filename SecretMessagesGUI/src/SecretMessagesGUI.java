import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	
	public String encode(String message, int keyVal) {
		String output = "";
		//return output;
		char key = (char) keyVal;
		for(int i = 0; i < message.length(); i++) {
			char input = message.charAt(i);
			if(input >= 'A' && input <= 'Z') {
				input += key;
				if(input > 'Z')
					input -= 26;
				if(input < 'A')
					input += 26;
			}
			else if (input >= 'a' && input <= 'z') {
				input += key;
				if(input > 'z')
					input -= 26;
				if(input < 'a')
					input += 26;
			}
			else if (input >= '0' && input <= '9') {
				input += (keyVal % 10);
				if(input > '9')
					input -= 10;
				if(input < '0')
					input += 10;
			}
			output += input;
		}
		return output;
	}
	
	public SecretMessagesGUI() {
		getContentPane().setBackground(new Color(176, 196, 222));
		setAlwaysOnTop(true);
		setTitle("Thy's Secret Message App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//Add the scroll bar to handle longer message
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 566, 140);
		getContentPane().add(scrollPane);
		
		JTextArea txtIn_1 = new JTextArea();	//Delete "JTextArea" from start of line
		scrollPane.setViewportView(txtIn_1);
		txtIn_1.setWrapStyleWord(true);
		txtIn_1.setLineWrap(true);
		txtIn_1.setFont(new Font("Arial", Font.PLAIN, 20));
		
		//Add the scroll bar to handle longer message
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 212, 566, 140);
		getContentPane().add(scrollPane_1);
		
		JTextArea txtOut_1 = new JTextArea();	//Delete "JTextArea" from start of line
		scrollPane_1.setViewportView(txtOut_1);
		txtOut_1.setWrapStyleWord(true);
		txtOut_1.setLineWrap(true);
		txtOut_1.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int value = Integer.parseInt(txtKey.getText());
				slider.setValue(value);
			}
		});
		txtKey.setText("14");
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setBounds(258, 176, 44, 14);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKey.setBounds(202, 176, 46, 14);
		getContentPane().add(lblKey);
		
		JButton btnNewButton = new JButton("Encode/Decode");
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argO) {
				try {
					//Get the input from the txtIn
					String message = txtIn_1.getText();
					//Get the key value from the txtKey
					int key = Integer.parseInt(txtKey.getText());
					//Encode the message by using key
					String output = encode(message, key);
					//Show the output message in txtOut
					txtOut_1.setText(output);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Please enter a whole number value for the encryption key.");
					txtKey.requestFocus();
					txtKey.selectAll();
					
				}
				
			}
		});
		//btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(312, 172, 144, 23);
		getContentPane().add(btnNewButton);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Return an integer-the current position value of the slider
				txtKey.setText("" + slider.getValue() );
				String message = txtIn_1.getText();
				//Get the current position value of the slider
				int key = slider.getValue();
				String output = encode(message, key);
				txtOut_1.setText(output);
			}
		});
		slider.setBackground(new Color(240, 248, 255));
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setValue(14);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(14);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBounds(20, 156, 200, 45);
		getContentPane().add(slider);
		
		//Create a button to move the encoded message up and decode it automatically
		//Add an event handler for the button
		JButton btnNewButton_1 = new JButton("Move Up ^");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get the text from the txtOut text area
				String text = txtOut_1.getText();
				//Set the text from the txtOut area as the text of the txtIn text area
				txtIn_1.setText(txtOut_1.getText());
				//Change the value of the slider to its opposite
				slider.setValue(-slider.getValue());
			}
		});
		btnNewButton_1.setBounds(475, 172, 89, 23);
		getContentPane().add(btnNewButton_1);
	}

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600, 400));
		theApp.setVisible(true);
	}
}
