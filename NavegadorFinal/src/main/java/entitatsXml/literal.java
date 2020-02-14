 package entitatsXml;

 import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

 @XmlRootElement(name = "literal")
 public class literal {
 public String _id;
 public String _lites;
 public String _litcat;
 public String _liten;

 public literal() {
 super();
 }

 @XmlAttribute(name = "id")
 public String getId() {
 return _id;
 }

 public void setId(String id) {
 this._id = id;
 }

 @XmlElement(name = "es")
 public String getlites() {
 return _lites;
 }

 public void setlites(String es) {
 this._lites = es;
 }

 @XmlElement(name = "en")
 public String getliten() {
 return _liten;
 }

 public void setliten(String en) {
 this._liten = en;
 }

 @XmlElement(name = "cat")
 public String getlitcat() {
 return _litcat;
 }

 public void setlitcat(String cat) {
 this._litcat = cat;
 }

 }
