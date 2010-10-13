import ucigame.Image;
import ucigame.Sprite;

public class Joueur extends Sprite {

	Direction dir;
	int numero;

	public Joueur(Direction dir, int numero, int hauteur, int largeur, Image i) {
		super(i, 28, 44);
		this.dir = dir;
		this.numero = numero;
		this.position(largeur * 64 , hauteur*48 );
	}

	public int getHauteur() {
		return this.yPixel();
	
	}

	public int getLargeur() {
		return this.xPixel();
	}
	
	public boolean exploser(){
		
		return false;
	}

}
