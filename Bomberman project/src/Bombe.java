import ucigame.*;


public class Bombe extends Case {

	int x,y;
	int tailleFlamme;
	Plateau plateau;
	long dateExplosion;
	boolean isBurst;
	
	public Bombe(int x, int y, int tailleFlamme, Image i,Plateau p){
		super(i);
		this.x = x;
		this.y = y;
		this.tailleFlamme = tailleFlamme;
		this.position(y*64, x*48);
		this.plateau = p;
		dateExplosion = System.currentTimeMillis()+(long)5000;
		isBurst = false;
	}
	
	public boolean est_traversable() {
		return false;
	}

	boolean est_destructible() {
		return true;
	}
	
	public void burst(){
		plateau.createFlamme(this.x+1, this.y, this.tailleFlamme, Joueur.Direction.E);
		plateau.createFlamme(this.x-1, this.y, this.tailleFlamme, Joueur.Direction.O);
		plateau.createFlamme(this.x, this.y+1, this.tailleFlamme, Joueur.Direction.S);
		plateau.createFlamme(this.x, this.y-1, this.tailleFlamme, Joueur.Direction.N);
		isBurst = true;
	}
	
	public boolean isBurst(){
		return isBurst;
	}
	
	public boolean readyToExplode(){
		return dateExplosion < System.currentTimeMillis();
	}
		
}
