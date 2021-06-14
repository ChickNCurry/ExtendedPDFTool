package View.Panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.MyEncryptor;
import View.Buttons.InputButton;
import View.Buttons.OutputButton;
import View.Buttons.ProcessButton;

@SuppressWarnings("serial")
public class EncryptPanel extends JPanel implements MyPanel {
	
	private MyEncryptor encryptor;
	private JButton inputButton;
	private JButton outputButton;
	private JButton processButton;
	private JTextField pswdField;
	
	public EncryptPanel() {
		this.setLayout(new GridLayout(3, 2, 30, 30));
		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		encryptor = new MyEncryptor();
		inputButton = new InputButton("Choose PDF", encryptor, new FileNameExtensionFilter("Documents", "pdf"), false);
		outputButton = new OutputButton("Choose Folder", encryptor);
		pswdField = new JPasswordField(10);
		processButton = new ProcessButton(this, "Encrypt PDF", encryptor);
		this.add(inputButton);
		this.add(outputButton);
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(new JLabel("Choose Password:"));
		this.add(pswdField);
		this.add(processButton);
	}

	public String getParam() {
		return pswdField.getText();
	}
	
}
