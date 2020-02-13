package entitatsHib;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class log {

	//comentario
	@Id
	@Column(name = "log_id")
	private int _id;
	@Column(name = "log_texte")
	private String _texto;
	@Column(name = "log_data")
	private Date _fecha;
	
	public log(){ }
	
	public log(int id, String texto, Date fecha){
		this.set_id(id);
		this.set_texto(texto);
		this.set_fecha(fecha);
	}

	//GETTERS AND SETTERS
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_texto() {
		return _texto;
	}

	public void set_texto(String _texto) {
		this._texto = _texto;
	}

	public Date get_fecha() {
		return _fecha;
	}

	public void set_fecha(Date _fecha) {
		this._fecha = _fecha;
	}

}
