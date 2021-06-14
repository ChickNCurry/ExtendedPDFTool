package Model;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class MyMerger implements MyHelper {
	
	private PDFMergerUtility helper;
	private List<File> list;
	private String outputPath;
	
	public MyMerger() {
		helper = new PDFMergerUtility();
		list = new ArrayList<File>();
	}
	
	public void setInput(File input) {
		if(!input.isDirectory()) return;
		this.outputPath = input.getAbsolutePath();
		list = Arrays.asList(input.listFiles());
	}
	
	public void setEncInput(File file, String pswd) {
		
	}

	public void setOutput(File output) {
		this.outputPath = output.getAbsolutePath();
	}
	
	public void processInput(Object mergePDFs) throws IOException, DocumentException {
		if(list.isEmpty()) return;
		if((boolean) mergePDFs) {
			helper.setDestinationFileName(outputPath + "/output.pdf");
			for (File file : list) {
				helper.addSource(file);
			}
			helper.mergeDocuments(null);
		}
		else {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(outputPath + "/output.pdf"));
			doc.open();
			for (File file : list) {
				doc.newPage();
				Image image = Image.getInstance(file.getAbsolutePath());
				image.setAbsolutePosition(0, 0);
				image.setBorderWidth(0);
				image.scaleAbsoluteHeight(PageSize.A4.getHeight());
				image.scaleAbsoluteWidth(PageSize.A4.getWidth());
				doc.add(image);
			}
			doc.close();
		}
	}
	
}
