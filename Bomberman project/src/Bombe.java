import java.util.Date;


public class Bombe implements Case {

	int tailleFlamme;
	long datePose = System.currentTimeMillis();
	long dateExplosion = datePose+5000;
	
	
	public boolean est_destructible() {
			return true;
	}
		
	public boolean est_traversable() {
			return false;
	}

	@Override
	public boolean est_joueur() {
		// TODO Auto-generated method stub
		return false;
	}
	
		
}
