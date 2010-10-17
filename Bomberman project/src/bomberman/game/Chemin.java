package bomberman.game;
import ucigame.Image;


public class Chemin extends Case{

	public Chemin(Image i, int x, int y) {
		super(i, x, y);
	}

	public boolean est_destructible() {
		return true;
	}
	
	public boolean est_traversable() {
		return true;
	}

}
