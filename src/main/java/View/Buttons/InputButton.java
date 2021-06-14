package View.Buttons;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.MyHelper;

@SuppressWarnings("serial")
public class InputButton extends JButton {
	
	private JFileChooser inputChooser;
	
	public InputButton(String text, MyHelper helper, FileNameExtensionFilter filter, boolean onlyFolders) {
		this.setText(text);
		inputChooser = new JFileChooser();
		
		if(filter != null) inputChooser.setFileFilter(filter);
		if(onlyFolders) inputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		inputChooser.setCurrentDirectory(new java.io.File("C:\\Users\\maxib\\Desktop"));
		
		this.addActionListener(e -> {
			if(inputChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	            try {
					helper.setInput(inputChooser.getSelectedFile());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        } 
		});
	}
	
}
