package entitatsXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "idioma")
public class idioma {

	private int idi_id;

	@XmlElement(name = "idi_id")
	int getidi_id() {
		return this.idi_id;
	}

	private String _idioma;

	@XmlElement(name = "_idioma")
	String get_idioma() {
		return this._idioma;
	}
	
	
}
