import ucigame.*;

public class Joueur extends Ucigame implements Case{

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
	
	private void move(){
		if(keyboard.isDown(keyboard.Z)){
			dir = Direction.N;
			hauteur++;
		}else if(keyboard.isDown(keyboard.S)){
			dir = Direction.S;
			hauteur--;
		}else if(keyboard.isDown(keyboard.Q)){
			dir = Direction.O;
			largeur--;
		}else if(keyboard.isDown(keyboard.D)){
			dir = Direction.E;
			largeur++;
		}
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
