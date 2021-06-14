package View;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import View.Panels.CompressPanel;
import View.Panels.ConvertPanel;
import View.Panels.EncryptPanel;
import View.Panels.FixPanel;
import View.Panels.MergePanel;
import View.Panels.SplitPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	final static private Dimension DIMENSION = new Dimension(600, 300);
	final static private String TITLE = "pdfTool";
	private JTabbedPane tabbedPane;
	private JPanel convertPanel;
	private JPanel mergePanel;
	private JPanel splitPanel;
	private JPanel compressPanel;
	private JPanel encryptPanel;
	private JPanel fixPanel;
	
	public MainFrame() {
		tabbedPane = new JTabbedPane();
		initFrame();
		initTabs();
		this.add(tabbedPane);
		this.setVisible(true);
	}
	
	private void initFrame() {
		this.setSize(DIMENSION);
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initTabs() {
		convertPanel = new ConvertPanel();
		mergePanel = new MergePanel();
		splitPanel = new SplitPanel();
		compressPanel = new CompressPanel();
		encryptPanel = new EncryptPanel();
		fixPanel = new FixPanel();
		tabbedPane.addTab("Convert PDF", convertPanel);
		tabbedPane.addTab("Split PDF", splitPanel);
		tabbedPane.addTab("Merge to PDF", mergePanel);
		tabbedPane.addTab("Compress PDF", compressPanel);
		tabbedPane.addTab("Encrypt PDF", encryptPanel);
		tabbedPane.addTab("Fix PDF", fixPanel);
	}
	
}
