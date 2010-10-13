import ucigame.Image;
import ucigame.Sprite;

public class Joueur extends Sprite {

	private Direction dir;
	private int numero;

	public Joueur(Direction dir, int numero, int hauteur, int largeur, Image i) {
		super(i, 28, 44);
		this.dir = dir;
		this.numero = numero;
		this.position(largeur * 64 , hauteur*48 );
	}

	public int getHauteur() {
		return (this.yPixel()+22)/48;
	}

	public int getLargeur() {
		return (this.xPixel()+14)/64;
	}

}
