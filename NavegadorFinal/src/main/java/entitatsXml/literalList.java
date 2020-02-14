package entitatsXml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "literals")
public class literalList {
	private List<entitatsXml.literal> _literal;

	public literalList() {
		super();
	}

	@XmlElement(name = "literal")
	public List<literal> getliterals() {
		if (_literal == null) {
			_literal = new ArrayList<literal>();
		}
		return _literal;
	}

	public void setEmployee(List<literal> literalList) {
		this._literal = literalList;
	}

}
