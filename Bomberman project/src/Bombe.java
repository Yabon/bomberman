import ucigame.*;


public class Bombe extends Case {

	private int largeur, hauteur;
	private int tailleFlamme;
	private Plateau plateau;
	private long dateExplosion;
	private boolean isBurst;
	private static final long latenceExplosion = 5000;
	
	public Bombe(int largeur, int hauteur, int tailleFlamme, Image i,Plateau p){
		super(i, largeur, hauteur);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.tailleFlamme = tailleFlamme;
		this.plateau = p;
		dateExplosion = System.currentTimeMillis()+latenceExplosion;
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
		plateau.createFlamme(this.largeur+1, this.hauteur, this.tailleFlamme, Direction.E, d);
		plateau.createFlamme(this.largeur-1, this.hauteur, this.tailleFlamme, Direction.O, d);
		plateau.createFlamme(this.largeur, this.hauteur+1, this.tailleFlamme, Direction.S, d);
		plateau.createFlamme(this.largeur, this.hauteur-1, this.tailleFlamme, Direction.N, d);
		plateau.createFlamme(this.largeur, this.hauteur, 1, null, d);
		isBurst = true;
	}
	
	public boolean isBurst(){
		return isBurst;
	}
	
	public boolean readyToExplode(){
		return dateExplosion < System.currentTimeMillis();
	}
	
	public boolean samePlace(Bombe b){
		return b.largeur==this.largeur && b.hauteur==this.hauteur;
	}
		
}
