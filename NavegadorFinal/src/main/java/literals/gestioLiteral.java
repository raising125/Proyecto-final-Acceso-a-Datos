package literals;

public class gestioLiteral {
	private String option= "hibernate";
	private literalHib hib = new literalHib();
	private literalMongoDB mongo = new literalMongoDB();
	public gestioLiteral() {}
	
	public void get_literal(String idi, String err) {
		switch(option) {
		case "hibernate":
			hib.get_literal(idi, err);
			break;
		
		case "mongo":
			mongo.get_literal(idi, err);
			break;
			
		default:
			hib.get_literal(idi, err);
			break;
		}
	}
}
