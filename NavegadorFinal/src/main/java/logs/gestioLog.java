package logs;

public class gestioLog {
	private logHib hib= new logHib();
	
	public gestioLog() {}
	public void log(String s) {
		hib.simplelog(s);
	}
	public void clear() {
		hib.clean();
	}

}
