import ucigame.Image;
import ucigame.Sprite;


public  abstract class Case extends Sprite{
	
	public Case(Image i, int largeur, int hauteur) {
		super(i);
		this.position(hauteur * 64, largeur * 48);
	}

	abstract boolean est_destructible();
	
	abstract boolean est_traversable();
	
}
