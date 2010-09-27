import java.util.Date;


public class Bombe implements Runnable{

	int x,y;
	int tailleFlamme;
	long dateExplosion = System.currentTimeMillis()+5000;
	
	public Bombe(int x, int y, int tailleFlamme){
		this.x = x;
		this.y = y;
		this.tailleFlamme = tailleFlamme;
	}
	
	public boolean est_traversable() {
			return false;
	}

	@Override
	public boolean est_joueur() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void run() {
		while(dateExplosion > System.currentTimeMillis() ){
			
		}
		PlateauGraphique.explosion(tailleFlamme);
	}
	
	
	
	//new Thread(Bombe).start();
		
}
