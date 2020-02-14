package entitatsXml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import literals.literalXml;

@XmlRootElement(name = "literals")
public class literal {
	private List<literalXml> _literal;

	public literal() {
		super();
	}

	@XmlElement(name = "literal")
	public List<literalXml> getliterals() {
		if (_literal == null) {
			_literal = new ArrayList<literalXml>();
		}
		return _literal;
	}

	public void setEmployee(List<literalXml> literalList) {
		this._literal = literalList;
	}

}
