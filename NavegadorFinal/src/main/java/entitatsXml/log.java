package entitatsXml;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "log")
public class log {
	private int log_id;

	@XmlElement(name = "idi_id")
	int getidi_id() {
		return this.log_id;
	}
	private String log_texte;

	@XmlElement(name = "log_texte")
	String get_log_texte() {
		return this.log_texte;
	}
	private Date log_data;

	@XmlElement(name = "log_data")
	Date get_log_data() {
		return this.log_data;
	}
}
