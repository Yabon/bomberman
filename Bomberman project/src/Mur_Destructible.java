import ucigame.Image;

public class Mur_Destructible extends Case{

	public Mur_Destructible(Image i, int x, int y) {
		super(i, x, y);
	}

	public boolean est_destructible() {
		return true;
	}

	public boolean est_traversable() {
		return false;
	}

}
