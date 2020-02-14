package literals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class literalSAX {
	private List<entitatsXml.literal> _literals;
	private boolean _carregat;
	private File _fitxerLiterals;
	private String _idioma;
	private String _content;

	public literalSAX(File fitxerLiterals, String idioma) {
		try {
			this._carregat = false;
			this._fitxerLiterals = fitxerLiterals;
			this._idioma = idioma;
			if (this._fitxerLiterals.exists()) {
				carregarFitxerLiterals();
			}
		} catch (Exception e) {
		}
	}

	/*
	 * iniciar la lectura de literals.
	 */
	private void carregarFitxerLiterals() throws SAXException, IOException, ParserConfigurationException {
		try {
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse("literals.xml", handler);

			_literals = handler.literals;
			this._carregat = true;
		} catch (Exception e) {
		}
	}

	/*
	 * Lectura del xml per events.
	 */
	class SAXHandler extends DefaultHandler {

		List<entitatsXml.literal> literals = new ArrayList<>();
		entitatsXml.literal lit = null;

		@Override
		// Triggered when the start of tag is found.
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {

			switch (qName) {
			// Create a new Employee object when the start tag is found
			case "literal":
				lit = new entitatsXml.literal();
				lit.setId(attributes.getValue("id"));
				break;
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch (qName) {
			// Add the literal to list once end tag is found
			case "literal":
				literals.add(lit);
				break;
			// For all other end tags the literal has to be updated.
			case "es":
				lit.setlites(_content);
				break;
			case "cat":
				lit.setlitcat(_content);
				break;
			case "en":
				lit.setliten(_content);
				break;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			_content = String.copyValueOf(ch, start, length).trim();
		}
	}

	/*
	 * Consultar la informaicó de la llista d'objectes.
	 */
	public String obtenirLiteral(String literal) {
		try {
			if (this._carregat) {
				List<entitatsXml.literal> auxLits = _literals.stream() // convert list to stream
						.filter(lit -> literal.equals(lit.getId())) // we dont like mkyong
						.collect(Collectors.toList());
				if (auxLits != null && auxLits.size() > 0) {
					switch (_idioma) {
					case "es":
						return auxLits.get(0).getlites();
					case "cat":
						return auxLits.get(0).getlitcat();
					case "en":
						return auxLits.get(0).getliten();
					default:
						return literal;
					}
				} else {
					return literal;
				}

			} else {
				return literal;
			}
		} catch (Exception e) {
			return literal;
		}
	}
}
