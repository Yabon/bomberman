public class Joueur implements Case{

	public static enum Direction {N,S,E,O};
	Direction dir;
	int numero;
	int hauteur,largeur;
	
	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}
	
	public void move(Direction d, boolean b){
		this.dir = d;
		switch(d){
		case N:
			if(b)hauteur++;
			break;
		case S:
			if(b)hauteur--;
			break;
		case O:
			if(b)largeur--;
			break;
		case E:
			if(b)largeur++;
			break;
		}
	}

	public Joueur(Direction dir, int numero, int hauteur, int largeur){
		this.dir = dir;
		this.numero = numero;
	}
	
	@Override
	public boolean est_destructible() {
		return true;
	}

	@Override
	public boolean est_traversable() {
		return false;
	}
	
	public String toString(){
		
		switch (dir) {

		case N :
			return "NN";

		case O :
			return "OO";

		case E :
			return "EE";

		case S :
			return "SS";
		}
		
		return " ";
	}

	@Override
	public boolean est_joueur() {
		return true;
	}

}
