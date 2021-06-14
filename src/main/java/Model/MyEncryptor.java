package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import View.PswdDialog;

public class MyEncryptor implements MyHelper {
	
	private List<PDDocument> docsList;
	private List<String> namesList;
	private String outputPath;
	
	public MyEncryptor() {
		docsList = new ArrayList<PDDocument>();
		namesList = new ArrayList<String>();
	}
	
	public void setInput(File file) throws IOException {
		if(file.isDirectory()) {
			this.outputPath = file.getAbsolutePath();
			File[] files = file.listFiles();
			for(File f : files) {
				try {
					docsList.add(Loader.loadPDF(f));
					namesList.add(f.getName());
				} catch (IOException e) {
					@SuppressWarnings("unused")
					PswdDialog dialog = new PswdDialog(this, f);
				}
			}
		}
		else {
			this.outputPath = file.getParent();
			try {
				docsList.add(Loader.loadPDF(file));
				namesList.add(file.getName());
			} catch (IOException e) {
				@SuppressWarnings("unused")
				PswdDialog dialog = new PswdDialog(this, file);
			}
		}
	}
	
	public void setEncInput(File file, String pswd) throws IOException {
		docsList.add(Loader.loadPDF(file, pswd));
		namesList.add(file.getName());
	}
	
	public void setOutput(File output) {
		this.outputPath = output.getAbsolutePath();	
	}

	@Override
	public void processInput(Object pswd) throws IOException {
		AccessPermission ap = new AccessPermission(); 
		StandardProtectionPolicy spp = new StandardProtectionPolicy((String) pswd, (String) pswd, ap);
		spp.setEncryptionKeyLength(256);
		spp.setPermissions(ap);
		for (PDDocument doc : docsList) {
			doc.protect(spp);
			doc.save(outputPath + "/" + "ENC_" + namesList.get(docsList.indexOf(doc)));
		}	
	}

}
