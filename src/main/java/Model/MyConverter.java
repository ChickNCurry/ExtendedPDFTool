package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import View.PswdDialog;

public class MyConverter implements MyHelper {

	private PDDocument doc;
	private String outputPath;
	
	public void setInput(File input) throws IOException {
		if(input.isDirectory()) return;
		this.outputPath = input.getParent();
		doc = new PDDocument();
		try {
			doc = Loader.loadPDF(input);
		} catch (IOException e) {
			@SuppressWarnings("unused")
			PswdDialog dialog = new PswdDialog(this, input);
		}
	}

	public void setEncInput(File input, String pswd) throws IOException {
		doc = Loader.loadPDF(input, pswd);
	}

	public void setOutput(File output) {
		this.outputPath = output.getAbsolutePath();
	}

	public void processInput(Object param) throws IOException {
		PDFRenderer pdfRenderer = new PDFRenderer(doc);
		for (int i = 0; i < doc.getNumberOfPages(); ++i)
		{
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
		    ImageIO.write(bim, "jpg", new File(outputPath + "/" + i + ".jpg"));
		}
		doc.close();
	}

}
