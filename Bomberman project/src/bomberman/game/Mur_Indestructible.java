package bomberman.game;
import ucigame.Image;

public class Mur_Indestructible extends Case{

	public Mur_Indestructible(Image i, int x, int y) {
		super(i, x, y);
	}

	public boolean est_destructible() {
		return false;
	}
	
	public boolean est_traversable() {
		return false;
	}

}
