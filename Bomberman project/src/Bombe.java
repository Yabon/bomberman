import java.util.Date;

import ucigame.*;


public class Bombe extends Case implements Runnable {

	int x,y;
	int tailleFlamme;
	long dateExplosion = System.currentTimeMillis()+5000;
	
	public Bombe(int x, int y, int tailleFlamme, Image i){
		super(i);
		this.x = x;
		this.y = y;
		this.tailleFlamme = tailleFlamme;
	}
	
	public boolean est_traversable() {
			return false;
	}

	public boolean est_joueur() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void run() {
		while(dateExplosion > System.currentTimeMillis() ){
			
		}
		//PlateauGraphique.explosion(tailleFlamme);
	}

	@Override
	boolean est_destructible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	//new Thread(Bombe).start();
		
}
