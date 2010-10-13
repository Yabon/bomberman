import ucigame.*;


public class Bombe extends Case {

	private int x,y;
	private int tailleFlamme;
	private Plateau plateau;
	private long dateExplosion;
	private boolean isBurst;
	
	public Bombe(int x, int y, int tailleFlamme, Image i,Plateau p){
		super(i, x, y);
		this.x = x;
		this.y = y;
		this.tailleFlamme = tailleFlamme;
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
		long d = dateExplosion+Flamme.duree;
		plateau.createFlamme(this.x+1, this.y, this.tailleFlamme, Direction.E, d);
		plateau.createFlamme(this.x-1, this.y, this.tailleFlamme, Direction.O, d);
		plateau.createFlamme(this.x, this.y+1, this.tailleFlamme, Direction.S, d);
		plateau.createFlamme(this.x, this.y-1, this.tailleFlamme, Direction.N, d);
		plateau.createFlamme(this.x, this.y, 1, null, d);
		isBurst = true;
	}
	
	public boolean isBurst(){
		return isBurst;
	}
	
	public boolean readyToExplode(){
		return dateExplosion < System.currentTimeMillis();
	}
	
	public boolean samePlace(Bombe b){
		return b.x==this.x && b.y==this.y;
	}
		
}
