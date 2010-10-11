import ucigame.Image;

public class Mur_Destructible extends Case{

	public Mur_Destructible(Image i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	public boolean est_destructible() {
		return true;
	}

	public boolean est_traversable() {
		return false;
	}

}
