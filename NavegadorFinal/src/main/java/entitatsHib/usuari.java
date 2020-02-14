package entitatsHib;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuari")
public class usuari {

	@Id
	@Column(name = "usuari_id")
	private int _id;
	@Column(name = "usuari_username")
	private String _username;
	@Column(name = "usuari_password")
	private String _password;
	
	public usuari(){ }
	
	public usuari(int id, String user, String pass){
		this.set_id(id);
		this.set_username(user);
		this.set_password(pass);
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	//GETTERS AND SETTERS
	
}

