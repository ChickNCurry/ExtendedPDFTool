package Model;

import java.io.File;
import java.io.IOException;

public interface MyHelper {
	
	public void setInput(File file) throws IOException;
	
	public void setEncInput(File file, String pswd) throws IOException;
	
	public void setOutput(File output);
	
	public void processInput(Object param) throws Exception;
	
}
