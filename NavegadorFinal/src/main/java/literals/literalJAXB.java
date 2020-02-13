package literals;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class literalJAXB {
private List<literalXml> _literals;
private boolean _carregat;
private File _fitxerLiterals;
private String _idioma;

public literalJAXB(File fitxerLiterals, String idioma) {
try {
this._carregat = false;
this._fitxerLiterals = fitxerLiterals;
this._idioma = idioma;
if (this._fitxerLiterals.exists()) {
carregarFitxerLiterals();
this._carregat = true;
}
} catch (Exception e) {
}
}


private void carregarFitxerLiterals() throws JAXBException {
_literals = unMarshalingLiteralXml(this._fitxerLiterals);
}

/*
* Passar d'xml a objecte
*/
public static List<literalXml> unMarshalingLiteralXml(File fitxer) throws JAXBException
{

JAXBContext jContext = JAXBContext.newInstance(literal.class);
Unmarshaller jUnmarshaller = jContext.createUnmarshaller();
literal unmarshaledList = (literal) jUnmarshaller.unmarshal(fitxer);
return unmarshaledList.getliterals();
}

/*
* Consultar la informaicó de la llista d'objectes.
*/
public String obtenirLiteral(String literal) {
try {
if (this._carregat) {
List<literalXml> auxLits = _literals.stream() // convert list to stream
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