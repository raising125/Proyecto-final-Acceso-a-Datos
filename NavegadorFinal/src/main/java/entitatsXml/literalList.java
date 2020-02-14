package entitatsXml;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "literalList")
public class literalList {
	private literal [] literalist;
	   
	   @XmlElementWrapper
	   @XmlElement(name="literalist")
	   literal [] getUnArray() {
	      return this.literalist;
	   }
}
