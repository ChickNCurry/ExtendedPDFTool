package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import View.PswdDialog;

public class MyCompressor implements MyHelper {
	
	private PDDocument document;
	private String outputPath;
	private String name;
	
	public void setInput(File input) throws IOException {
		if(input.isDirectory()) return;
		this.outputPath = input.getParent();
		name = input.getName();
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

	public void processInput(Object dpi) throws IOException {
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		int numberOfPages = document.getNumberOfPages();
		PDPage page = null;
		for (int i = 0; i < numberOfPages; i++) {
			page = new PDPage(PDRectangle.LETTER);
			BufferedImage bim = pdfRenderer.renderImageWithDPI(i, (float) dpi, ImageType.RGB);
			PDImageXObject pdImage = JPEGFactory.createFromImage(document, bim);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			float newHeight = PDRectangle.LETTER.getHeight();
			float newWidth = PDRectangle.LETTER.getWidth();
			contentStream.drawImage(pdImage, 0, 0, newWidth, newHeight);
			contentStream.close();
			document.addPage(page);
		}	
		document.setAllSecurityToBeRemoved(true);
		document.save(outputPath + "/"+ "COMP_" + name);
		document.close();
	}

}
