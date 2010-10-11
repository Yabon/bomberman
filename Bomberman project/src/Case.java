import ucigame.Image;
import ucigame.Sprite;


public  abstract class Case extends Sprite{
	
	public Case(Image i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	abstract boolean est_destructible();
	
	abstract boolean est_traversable();
	
}
