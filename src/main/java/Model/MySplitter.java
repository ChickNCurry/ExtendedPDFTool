package Model;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import View.PswdDialog;

public class MySplitter implements MyHelper {
	
	private Splitter helper;
	private PDDocument document;
	private String outputPath;
	
	public MySplitter() {
		helper = new Splitter();
	}
	
	public void setInput(File input) throws IOException {
		if(input.isDirectory()) return;
		this.outputPath = input.getParent();
		try {
			document = Loader.loadPDF(input);
		} catch (IOException e) {
			@SuppressWarnings("unused")
			PswdDialog dialog = new PswdDialog(this, input);
		}
	}
	
	public void setEncInput(File input, String pswd) throws IOException {
		document = Loader.loadPDF(input, pswd);
	}
	
	public void setOutput(File output) {
		this.outputPath = output.getAbsolutePath();
	}
	
	public void processInput(Object size) throws IOException {
		if((int) size <= 0) return;
		helper.setSplitAtPage((int) size);
		if(document == null || outputPath == null) return;
		List<PDDocument> pages = helper.split(document);  
		for (PDDocument page : pages) {
			page.save(outputPath + "/" + pages.indexOf(page) + ".pdf");
		}
		document.close();
	}
}
