import java.util.Date;

import ucigame.*;


public class Bombe extends Case {

	int x,y;
	int tailleFlamme;
	Plateau p;
	long dateExplosion = System.currentTimeMillis()+5000;
	
	public Bombe(int x, int y, int tailleFlamme, Image i,Plateau p){
		super(i);
		this.x = x;
		this.y = y;
		this.tailleFlamme = tailleFlamme;
		this.position(y*64, x*48);
		this.p = p;
	}
	
	public boolean est_traversable() {
		return false;
	}

	public boolean est_joueur() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
		
			
		
		
	

	boolean est_destructible() {
		
		return false;
	}
	
	boolean exploser(){
		return false;
	}
	
	
	
	//new Thread(Bombe).start();
		
}
