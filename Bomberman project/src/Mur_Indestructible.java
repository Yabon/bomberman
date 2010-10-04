import ucigame.Image;

public class Mur_Indestructible extends Case{

	public Mur_Indestructible(Image i) {
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
