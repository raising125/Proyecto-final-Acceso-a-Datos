package entitatsXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuario")
public class usuari {
	private int usuari_id;

	@XmlElement(name = "usuari_id")
	int getusuari_id() {
		return this.usuari_id;
	}
	
	private String usuari_username;

	@XmlElement(name = "usuari_username")
	String getusuari_username() {
		return this.usuari_username;
	}
	
	private String usuari_password;

	@XmlElement(name = "usuari_password")
	String getusuari_password() {
		return this.usuari_password;
	}
}
