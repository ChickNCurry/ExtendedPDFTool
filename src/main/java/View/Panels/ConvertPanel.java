package View.Panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.MyConverter;
import View.Buttons.InputButton;
import View.Buttons.OutputButton;
import View.Buttons.ProcessButton;

@SuppressWarnings("serial")
public class ConvertPanel extends JPanel implements MyPanel {

	private MyConverter converter;
	private JButton inputButton;
	private JButton outputButton;
	private JButton processButton;
	
	public ConvertPanel() {
		this.setLayout(new GridLayout(3, 2, 30, 30));
		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		converter = new MyConverter();
		inputButton = new InputButton("Choose PDF", converter, new FileNameExtensionFilter("Document", "pdf"), false);
		outputButton = new OutputButton("Choose Folder", converter);
		processButton = new ProcessButton(this, "Convert PDF", converter);
		this.add(inputButton);
		this.add(outputButton);
		this.add(processButton);
	}

	public Object getParam() {
		return null;
	}

}
