package literals;

public class gestioLiteral {
	private String option= "hibernate";
	private literalHib hib;
	private literalMongoDB mongo;
	public gestioLiteral() {
		switch(option) {
		case "hibernate":
			this.hib=new literalHib();
			break;
		
		case "mongo":
			this.mongo= new literalMongoDB();
			break;
			
		default:
			this.hib=new literalHib();
			break;
		}
	}
	
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
