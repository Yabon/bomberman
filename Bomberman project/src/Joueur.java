import ucigame.Image;
import ucigame.Sprite;

public class Joueur extends Sprite {

	public static enum Direction {
		N, S, E, O
	};

	Direction dir;
	int numero;
	int hauteur, largeur;

	public Joueur(Direction dir, int numero, int hauteur, int largeur, Image i) {
		super(i);
		this.dir = dir;
		this.numero = numero;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.position(largeur * 64, hauteur*48);
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

}
