import ucigame.Image;
import ucigame.Sprite;

public class Joueur extends Sprite {

	private int numero;

	public Joueur(int numero, int hauteur, int largeur, Image i) {
		super(i, 28, 44);
		this.numero = numero;
		this.position(largeur * 64 , hauteur*48 );
	}

	public int getHauteur() {
		return (this.yPixel()+22)/Plateau.hauteurImage;
	}

	public int getLargeur() {
		return (this.xPixel()+14)/Plateau.largeurImage;
	}
	
	public int getNumero(){
		return numero;
	}

}
