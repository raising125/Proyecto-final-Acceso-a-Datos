package literals;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.sun.xml.txw2.Document;

public class literalDOM {
	File file;
	public void loadfile_XML(String filename) {
		 file = new File(filename+".xml");
		
	}
	public void parsefile_XML() {
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  org.w3c.dom.Document doc = dBuilder.parse(file);
			} catch(Exception e) {
			  e.printStackTrace();
			}
		
	}
	public void name() {
		
	}
}
