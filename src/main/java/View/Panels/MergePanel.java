package View.Panels;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.MyMerger;
import View.Buttons.InputButton;
import View.Buttons.OutputButton;
import View.Buttons.ProcessButton;

@SuppressWarnings("serial")
public class MergePanel extends JPanel implements MyPanel {
	
	private MyMerger merger;
	private JButton inputButton;
	private JButton outputButton;
	private JButton processButton;
	private JComboBox<String> options;
	
	public MergePanel() {
		this.setLayout(new GridLayout(3, 2, 30, 30));
		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		merger = new MyMerger();
		options = new JComboBox<String>(new String[] {"PDFs", "Images"});
		options.setSelectedIndex(0);
		inputButton = new InputButton("Choose Files", merger, null, true);
		outputButton = new OutputButton("Choose Folder", merger);
		processButton = new ProcessButton(this, "Merge Files", merger);
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(new JLabel("File Type:"));
		this.add(options);
		this.add(inputButton);
		this.add(outputButton);
		this.add(processButton);
	}

	public Object getParam() {
		if(options.getSelectedIndex() == 0) return true;
		return false;
	}
	
}
