import ucigame.Image;
import ucigame.Sprite;


public  abstract class Case extends Sprite{
	
	public Case(Image i, int x, int y) {
		super(i);
		this.position(y * 64, x * 48);
	}

	abstract boolean est_destructible();
	
	abstract boolean est_traversable();
	
}
