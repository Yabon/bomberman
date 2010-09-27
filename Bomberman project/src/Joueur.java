public class Joueur implements Case{

	public static enum Direction {N,S,E,O};
	Direction dir;
	int numero;
	int hauteur,largeur;
	
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
