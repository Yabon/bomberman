import ucigame.Image;

public class Flamme extends Case{

	private int tailleRestante;
	private Plateau plateau;
	private Direction direction;
	private long dateFin;
	private boolean isSpread;
	public final static long duree = 500;
	
	public Flamme(int x, int y, Image i, int t, Plateau p, Direction d, long dateFin) {
		super(i, x, y);
		tailleRestante = t;
		this.plateau = p;
		direction = d;
		this.dateFin = dateFin;
		isSpread = false;
	}
	
	public void spread(){
		if(tailleRestante > 0){
			switch(direction){
			case N :
				plateau.createFlamme(this.yPixel()/48, (this.xPixel()/64)-1, tailleRestante-1, direction, dateFin);
				break;
			case S :
				plateau.createFlamme(this.yPixel()/48, (this.xPixel()/64)+1, tailleRestante-1, direction, dateFin);
				break;
			case E :
				plateau.createFlamme((this.yPixel()/48)+1, (this.xPixel()/64), tailleRestante-1, direction, dateFin);
				break;
			case O :
				plateau.createFlamme((this.yPixel()/48)-1, (this.xPixel()/64), tailleRestante-1, direction, dateFin);
				break;
			}
		}
		isSpread = true;
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

	public boolean isSpread(){
		return isSpread;
	}
}
