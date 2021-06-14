package View.Buttons;

import java.awt.Color;

import javax.swing.JButton;

import Model.MyHelper;
import View.Panels.MyPanel;

@SuppressWarnings("serial")
public class ProcessButton extends JButton {
	
	public ProcessButton(MyPanel panel, String text, MyHelper helper) {
		this.setText(text);
		
		this.addActionListener(e -> {  
			this.setBackground(Color.RED);
			new Thread(() -> {
				try {
					helper.processInput(panel.getParam());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				this.setBackground(null);;
			}).start();
		}); 
	}
	
}
