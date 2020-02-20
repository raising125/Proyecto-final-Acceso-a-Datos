package literals;

public class gestioLiteral {
	private String option= "hibernate";
	private literalHib hib = new literalHib();
	private literalMongoDB mongo = new literalMongoDB();
	public gestioLiteral() {}
	
	public String get_literal(String idi, String err) {
		switch(option) {
		case "hibernate":
			return hib.get_literal(idi, err);
		
		case "mongo":
			return mongo.get_literal(idi, err);
			
		default:
			return hib.get_literal(idi, err);
		}
	}
}
