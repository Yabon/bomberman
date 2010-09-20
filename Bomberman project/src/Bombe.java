import java.util.Date;


public class Bombe implements Case {

	int tailleFlamme;
	Date datePose;
	
	
	
	public boolean est_destructible() {
			return true;
	}
		
	public boolean est_traversable() {
			return false;
	}
		
}
