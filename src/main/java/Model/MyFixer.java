package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.Loader;

//import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import View.PswdDialog;

public class MyFixer implements MyHelper {
	
	private PDDocument indocument;
	private PDDocument outdocument;
	private String outputPath;
	private List<BufferedImage> list;
	
	public MyFixer() {
		list = new ArrayList<BufferedImage>();
	}
	
	public void setInput(File input) throws IOException {
		if(input.isDirectory()) return;
		this.outputPath = input.getAbsolutePath();
		outdocument = new PDDocument();
		try {
			indocument = Loader.loadPDF(input);
		} catch (IOException e) {
			@SuppressWarnings("unused")
			PswdDialog dialog = new PswdDialog(this, input);
		}
	}

	public void setEncInput(File input, String pswd) throws IOException {
		indocument = Loader.loadPDF(input, pswd);
	}

	public void setOutput(File output) {
		this.outputPath = output.getAbsolutePath();
	}

	public void processInput(Object param) throws IOException {
		PDFRenderer pdfRenderer = new PDFRenderer(indocument);
		for (int i = 0; i < indocument.getNumberOfPages(); ++i)
		{
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
		    list.add(bim);
			
			PDPage page = new PDPage();

	        PDImageXObject pdImage = LosslessFactory.createFromImage(outdocument, bim);
	        
	        PDPageContentStream contents = new PDPageContentStream(outdocument, page);
	        contents.drawImage(pdImage, 0, 0, page.getCropBox().getWidth(), page.getCropBox().getHeight());
	        contents.close();
	        
	        outdocument.addPage(page);
		}
		indocument.close();
        outdocument.save(outputPath);
        outdocument.close();
	}

}
