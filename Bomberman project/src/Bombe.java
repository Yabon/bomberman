import ucigame.*;


public class Bombe extends Case {

	private int largeur, hauteur;
	private int tailleFlamme;
	private Plateau plateau;
	private long dateExplosion;
	private boolean isBurst;
	private static final long latenceExplosion = 5000;
	
	public Bombe(int largeur, int hauteur, int tailleFlamme, Image image,Plateau plateau){
		super(image, largeur, hauteur);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.tailleFlamme = tailleFlamme;
		this.plateau = plateau;
		this.dateExplosion = System.currentTimeMillis()+latenceExplosion;
		this.isBurst = false;
	}
	
	public boolean est_traversable() {
		return false;
	}

	boolean est_destructible() {
		return true;
	}
	
	public void burst(){
		long dateFinFlamme = dateExplosion+Flamme.duree;
		plateau.createFlamme(this.largeur+1, this.hauteur, this.tailleFlamme, Direction.E, dateFinFlamme);
		plateau.createFlamme(this.largeur-1, this.hauteur, this.tailleFlamme, Direction.O, dateFinFlamme);
		plateau.createFlamme(this.largeur, this.hauteur+1, this.tailleFlamme, Direction.S, dateFinFlamme);
		plateau.createFlamme(this.largeur, this.hauteur-1, this.tailleFlamme, Direction.N, dateFinFlamme);
		plateau.createFlamme(this.largeur, this.hauteur, 1, null, dateFinFlamme);
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
	
	public boolean samePlace(Flamme f){
		return f.getLargeur()==this.largeur && f.getHauteur()==this.hauteur;
	}
		
}
