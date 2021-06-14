package View.Panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.MySplitter;
import View.Buttons.InputButton;
import View.Buttons.OutputButton;
import View.Buttons.ProcessButton;

@SuppressWarnings("serial")
public class SplitPanel extends JPanel implements MyPanel {
	
	private MySplitter splitter;
	private JButton inputButton;
	private JButton outputButton;
	private JSpinner outputSize;
	private JButton processButton;
	
	public SplitPanel() {
		this.setLayout(new GridLayout(3, 2, 30, 30));
		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		splitter = new MySplitter();
		outputSize = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		inputButton = new InputButton("Choose PDF", splitter, new FileNameExtensionFilter("Documents", "pdf"), false);
		outputButton = new OutputButton("Choose Folder", splitter);
		processButton = new ProcessButton(this, "Split PDF", splitter);
		this.add(inputButton);
		this.add(outputButton);
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(new JLabel("Pages per File:"));
		this.add(outputSize);
		this.add(processButton);
	}

	public Object getParam() {
		return outputSize.getValue();
	}
	
}
