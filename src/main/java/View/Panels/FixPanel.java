package View.Panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.MyFixer;
import View.Buttons.InputButton;
import View.Buttons.OutputButton;
import View.Buttons.ProcessButton;

@SuppressWarnings("serial")
public class FixPanel extends JPanel implements MyPanel {

	private MyFixer fixer;
	private JButton inputButton;
	private JButton outputButton;
	private JButton processButton;
	
	public FixPanel() {
		this.setLayout(new GridLayout(3, 2, 30, 30));
		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		fixer = new MyFixer();
		inputButton = new InputButton("Choose PDF", fixer, new FileNameExtensionFilter("Document", "pdf"), false);
		outputButton = new OutputButton("Choose Folder", fixer);
		processButton = new ProcessButton(this, "Fix PDF", fixer);
		this.add(inputButton);
		this.add(outputButton);
		this.add(processButton);
	}

	public Object getParam() {
		return null;
	}

}
