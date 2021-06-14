package View.Panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.MyCompressor;
import View.Buttons.InputButton;
import View.Buttons.OutputButton;
import View.Buttons.ProcessButton;

@SuppressWarnings("serial")
public class CompressPanel extends JPanel implements MyPanel {

	private MyCompressor compressor;
	private JButton inputButton;
	private JButton outputButton;
	private JButton processButton;
	private JSpinner outputDPI;
	
	public CompressPanel() {
		this.setLayout(new GridLayout(3, 2, 30, 30));
		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		compressor = new MyCompressor();
		outputDPI = new JSpinner(new SpinnerNumberModel(100, 0, 100, 10));
		inputButton = new InputButton("Choose PDF", compressor, new FileNameExtensionFilter("Documents", "pdf"), false);
		outputButton = new OutputButton("Choose Folder", compressor);
		processButton = new ProcessButton(this, "Compress PDF", compressor);
		this.add(inputButton);
		this.add(outputButton);
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(new JLabel("Choose DPI:"));
		this.add(outputDPI);
		this.add(processButton);
	}

	public Object getParam() {
		int out = (int) outputDPI.getValue();
		return (float) out;
	}

}
