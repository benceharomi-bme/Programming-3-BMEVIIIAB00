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
		JFrame frame = new JFrame("SwingLab");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 110);
		GridLayout l = new GridLayout();
		l.setRows(2);
		l.setColumns(1);
		frame.setLayout(l);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		frame.add(panel1);
		Object chars[] = new Object[26];
		for(int i = 0; i < 26; i++) {
			chars[i] = (char) (i + 'A');
		}
		box = new JComboBox(chars);
		panel1.add(box);
		field1 = new JTextField(20);
//5. feladat
//		field1.addKeyListener(new InputFieldKeyListener();
		field1.getDocument().addDocumentListener(new InputFieldKeyListener());
		panel1.add(field1);
		button = new JButton();
		button.setText("Code!");
		button.addActionListener(new OkButtonListener());
		panel1.add(button);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.addFocusListener(new MyFocusListener());
		frame.add(panel2);
		JLabel label = new JLabel("Output:");
		panel2.add(label,BorderLayout.WEST);
		field2 = new JTextField(20);
		field2.setEditable(true);
		panel2.add(field2);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public class OkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(decode == true) {
				String input = field2.getText();
				char offset = (char) box.getSelectedItem();
				String output = CaesarCode.caesarDeCode(input,offset);
				field2.setText(output);	
			}
			else {
				String input = field1.getText();
				char offset = (char) box.getSelectedItem();
				String output = CaesarCode.caesarCode(input,offset);
				field2.setText(output);	
			}
		}	
		
	}
/*
 * 5. feladat megoldasa
	public class InputFieldKeyListener extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			String input = field1.getText();
			char offset = (char) box.getSelectedItem();
			String output = CaesarCode.caesarCode(input,offset);
			field2.setText(output);	
			
		}
		
	}
*/
	public class InputFieldKeyListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			String input = field1.getText();
			char offset = (char) box.getSelectedItem();
			String output = CaesarCode.caesarCode(input,offset);
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
			decode = true;
		}

		@Override
		public void focusLost(FocusEvent e) {
			decode = false;
			
		}
		
	}
}
