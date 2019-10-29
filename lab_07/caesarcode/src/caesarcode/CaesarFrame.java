package caesarcode;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class CaesarFrame extends JFrame {
	private JTextField field1, field2;
	private JButton button;
	private JComboBox box;
	
	private boolean decode = false;
	
	public CaesarFrame() {
		setTitle("SwingLab");
		setSize(400, 110);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Layout of the frame
		GridLayout l = new GridLayout();
		l.setRows(2);
		l.setColumns(1);
		setLayout(l);
		
		//1st panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		add(panel1);
		Object chars[] = new Object[26];
		for(int i = 0; i < 26; i++) {
			chars[i] = (char) (i + 'A');
		}
		box = new JComboBox(chars);
		panel1.add(box);
		field1 = new JTextField(20);
//		field1.addKeyListener(new InputFieldKeyListener()); //5th task
		field1.getDocument().addDocumentListener(new InputFieldKeyListener());
		field1.addFocusListener(new MyFocusListener());
		panel1.add(field1);
		button = new JButton();
		button.setText("Code!");
		button.addActionListener(new OkButtonListener());
		panel1.add(button);
		
		//2nd panel
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		add(panel2);
		JLabel label = new JLabel("Output:");
		panel2.add(label,BorderLayout.WEST);
		field2 = new JTextField(20);
		field2.setEditable(true);
		field2.addFocusListener(new MyFocusListener());
		panel2.add(field2);
		

	}
	
	private String caesarCode(String input, char offset) {
		input = input.toUpperCase();
		offset = Character.toUpperCase(offset);
		
		int x = (int)offset - (int)'A';
		
		String output = "";
		
		for( int i = 0; i < input.length(); i++) {
			char c = (char) (input.charAt(i) + x);
			if(c > 'Z') {
				output += (char) (input.charAt(i) - (26-x));
			}else {
				output += (char) (input.charAt(i) + x);
			}
			
		}
		return output;
		
	}

	private String caesarDeCode(String input, char offset) {
		input = input.toUpperCase();
		offset = Character.toUpperCase(offset);
		
		int x = (int)offset - (int)'A';
		
		String output = "";
		
		for( int i = 0; i < input.length(); i++) {
			char c = (char) (input.charAt(i) - x);
			if(c < 'A') {
				output += (char) (input.charAt(i) + (26-x));
			}else {
				output += (char) (input.charAt(i) - x);
			}
			
		}
		return output;
		
	}

	public class OkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(decode) {
				String input = field2.getText();
				char offset = (char) box.getSelectedItem();
				String output = caesarDeCode(input,offset);
				field1.setText(output);	
				
			}
			else {
				String input = field1.getText();
				char offset = (char) box.getSelectedItem();
				String output = caesarCode(input,offset);
				field2.setText(output);	
			}	
		}
		
	}
/*
	// 5. feladat megoldasa
	public class InputFieldKeyListener extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			String input = field1.getText();
			char offset = (char) box.getSelectedItem();
			String output = caesarCode(input,offset);
			field2.setText(output);	
				
		}
			
	}
*/

	public class InputFieldKeyListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			String input = field1.getText();
			char offset = (char) box.getSelectedItem();
			String output = caesarCode(input,offset);
			field2.setText(output);	
				
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
				
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
				
		}
			
	}		


	public class MyFocusListener implements FocusListener{
		
		@Override
		public void focusGained(FocusEvent e) {
			if(e.getComponent().equals(field2)){
				decode = true;
			}
			else {
				decode = false;
			}
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
		}
		
	}

}
