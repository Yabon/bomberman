import ucigame.Image;

public class Flamme extends Case{

	public Flamme(Image i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	public boolean est_destructible() {
		return false;
	}

	public boolean est_traversable() {
		return false;
	}

	@Override
	boolean est_joueur() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
