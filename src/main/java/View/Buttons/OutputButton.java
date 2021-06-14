package View.Buttons;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import Model.MyHelper;

@SuppressWarnings("serial")
public class OutputButton extends JButton {
	
	private JFileChooser outputChooser;
	
	public OutputButton(String text, MyHelper helper) {
		this.setText(text);
		outputChooser = new JFileChooser();
		
		outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		this.addActionListener(e -> {  
			if(outputChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	            helper.setOutput(outputChooser.getSelectedFile());
	        }  
		}); 
	}
	
}
