package entitatsHib;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Banana
@Entity
@Table(name = "literal")
public class literal {
	
	@Id
	@Column(name = "lit_id")
	private int _id;
	
	@Column(name = "idi_cod")
	private String _idioma;
	
	@Column(name = "lit_clau")
	private String _tipo_error;
	
	@Column(name = "lit_text")
	private String _error;
	
	public literal(){ }
	
	public literal(int id, String idioma, String tipoError, String error){
		this.set_id(id);
		this.set_idioma(idioma);
		this.set_tipo_error(tipoError);
		this.set_error(error);
	}
	
	//GETTERS AND SETTER
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_idioma() {
		return _idioma;
	}

	public void set_idioma(String _idioma) {
		this._idioma = _idioma;
	}

	public String get_tipo_error() {
		return _tipo_error;
	}

	public void set_tipo_error(String _tipo_error) {
		this._tipo_error = _tipo_error;
	}

	public String get_error() {
		return _error;
	}

	public void set_error(String _error) {
		this._error = _error;
	}
}
