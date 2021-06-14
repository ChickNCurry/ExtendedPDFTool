package View;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.MyHelper;

@SuppressWarnings("serial")
public class PswdDialog extends JDialog {
	
	final static private Dimension DIMENSION = new Dimension(300, 300);
	private MyHelper helper;
	private File input;
	private JLabel pswdLabel;
	private JTextField pswdField;
	private JButton okButton;
	private JButton cancelButton;
	
	public PswdDialog(MyHelper helper, File input) {
		this.setSize(DIMENSION);
		this.helper = helper;
		this.input = input;
		JPanel pan = new JPanel();
		pswdLabel = new JLabel("Enter password:");
		pan.add(pswdLabel);
		pswdField = new JPasswordField(10);
		pan.add(pswdField);
		okButton = new JButton("OK");
		initPswdButton();
		cancelButton = new JButton("Cancel");
		initCancelButton();
		pan.add(okButton);
		pan.add(cancelButton);
		this.add(pan);
		this.setVisible(true);
	}
	
	private void initPswdButton() {
		okButton.addActionListener(e -> {  
			try {
				helper.setEncInput(input, pswdField.getText());
				dispose();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Wrong password");
			}
		}); 
	}
	
	private void initCancelButton() {
		cancelButton.addActionListener(e -> {  
			dispose(); 
		}); 
	}
	
}
