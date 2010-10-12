import ucigame.Image;

public class Flamme extends Case{

	int tailleRestante;
	Plateau plateau;
	Joueur.Direction direction;
	long dateFin;
	
	public Flamme(int x, int y, Image i, int t, Plateau p, Joueur.Direction d) {
		super(i);
		tailleRestante = t;
		this.plateau = p;
		direction = d;
		this.position(y*64, x*48);
		dateFin = System.currentTimeMillis()+500;
	}
	
	public void spread(){
		if(tailleRestante > 0){
			switch(direction){
			case N :
				plateau.createFlamme(this.xPixel()/48, (this.yPixel()/64)-1, tailleRestante-1, direction);
				break;
			case S :
				plateau.createFlamme(this.xPixel()/48, (this.yPixel()/64)+1, tailleRestante-1, direction);
				break;
			case E :
				plateau.createFlamme((this.xPixel()/48)+1, (this.yPixel()/64), tailleRestante-1, direction);
				break;
			case O :
				plateau.createFlamme((this.xPixel()/48)-1, (this.yPixel()/64), tailleRestante-1, direction);
				break;
			}
		}
	}

	public boolean est_destructible() {
		return false;
	}

	public boolean est_traversable() {
		return true;
	}
	
	public boolean isBlown(){
		return dateFin < System.currentTimeMillis();
	}

}
