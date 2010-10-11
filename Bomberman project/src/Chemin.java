import ucigame.Image;


public class Chemin extends Case{

	public Chemin(Image i) {
		super(i);
	}

	public boolean est_destructible() {
		return true;
	}
	
	public boolean est_traversable() {
		return true;
	}

}
