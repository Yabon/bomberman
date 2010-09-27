import java.util.List;
import java.util.ArrayList;
import ucigame.*;

public class Plateau extends Ucigame {
	
	/**
	 * On utilisera la première dimension pour la verticalité et la deuxième pour l'horizontalité
	 */
	private Case[][] grilleJeu;
	List<Bombe> bombes;
	public int ratio = 100;//Entre 0 et 100 represente le pourcentage de murs déstructibles
	Joueur joueur1 = new Joueur(Joueur.Direction.N,1,1,1);
	Sprite spriteJoueur1;
	
	private Plateau(int h, int l){
		grilleJeu = new Case[h][l];
		//generation du millieu du plateau
		for(int hauteur = 1 ; hauteur < grilleJeu.length-1; hauteur++){
			for(int largeur = 1 ; largeur < grilleJeu[0].length-1; largeur++){
				if(Math.random()*100>ratio){
					grilleJeu[hauteur][largeur] = new Chemin();	
				}else{
					grilleJeu[hauteur][largeur] = new Mur_Destructible();	
				}
			}
		}
		//Generation du tour indéstructible
		for(int hauteur = 0 ; hauteur < grilleJeu.length; hauteur++){
			grilleJeu[hauteur][0] = new Mur_Indestructible();
			grilleJeu[hauteur][14] = new Mur_Indestructible();
		}
		for(int largeur = 0 ; largeur < grilleJeu[0].length; largeur++){
			grilleJeu[0][largeur] = new Mur_Indestructible();
			grilleJeu[12][largeur] = new Mur_Indestructible();
		}
		
		//Generation des cases indéstructibles au millieu du terrain
		for(int hauteur = 2 ; hauteur < grilleJeu.length-1; hauteur+=2){
			for(int largeur = 2 ; largeur < grilleJeu[0].length-1; largeur+=2){
				grilleJeu[hauteur][largeur] = new Mur_Indestructible();
			}
		}
		bombes = new ArrayList<Bombe>(1024); // taille de la liste a l'origine pour éviter la réallocation
	}
	
	public Plateau(){
		grilleJeu = new Case[13][15];
		// à implémenter : génération d'un plateau aléatoire
		genererTerrain();
		//placer les joueurs a implémenter
		
		bombes = new ArrayList<Bombe>(1024); // taille de la liste a l'origine pour éviter la réallocation
	}
	
	public Case getCase(int posX, int posY){
		return grilleJeu[posY][posX];
	}
	
	private void genererTerrain(){

	
		//generation du millieu du plateau
		for(int hauteur = 1 ; hauteur < grilleJeu.length-1; hauteur++){
			for(int largeur = 1 ; largeur < grilleJeu[0].length-1; largeur++){
				if(Math.random()*100>ratio){
					grilleJeu[hauteur][largeur] = new Chemin();	
				}else{
					grilleJeu[hauteur][largeur] = new Mur_Destructible();	
				}
			}
		}
		//Generation du tour indéstructible
		for(int hauteur = 0 ; hauteur < grilleJeu.length; hauteur++){
			grilleJeu[hauteur][0] = new Mur_Indestructible();
			grilleJeu[hauteur][14] = new Mur_Indestructible();
		}
		for(int largeur = 0 ; largeur < grilleJeu[0].length; largeur++){
			grilleJeu[0][largeur] = new Mur_Indestructible();
			grilleJeu[12][largeur] = new Mur_Indestructible();
		}
		
		//Generation des cases indéstructibles au millieu du terrain
		for(int hauteur = 2 ; hauteur < grilleJeu.length-1; hauteur+=2){
			for(int largeur = 2 ; largeur < grilleJeu[0].length-1; largeur+=2){
				grilleJeu[hauteur][largeur] = new Mur_Indestructible();
			}
		}
		
		//Generation du depart des joueurs
		
										/* ## */
		grilleJeu[1][2] = new Chemin(); /* #  */
		grilleJeu[2][1] = new Chemin();
		//grilleJeu[1][1] = new Chemin();
		grilleJeu[1][1] = joueur1;
		
		grilleJeu[11][13] = new Chemin();/*  # */
		grilleJeu[11][12] = new Chemin();/* ## */
		grilleJeu[10][13] = new Chemin();
		
		grilleJeu[1][13] = new Chemin();/* ## */
		grilleJeu[1][12] = new Chemin();/*  # */
		grilleJeu[2][13] = new Chemin();
		
		grilleJeu[11][1] = new Chemin();/* #  */
		grilleJeu[11][2] = new Chemin();/* ## */
		grilleJeu[10][1] = new Chemin();
		
	}

	public void afficherASCII(){
		for(int hauteur = 0 ; hauteur < grilleJeu.length; hauteur++){
			for(int largeur = 0 ; largeur < grilleJeu[0].length; largeur++){
				if(hauteur == joueur1.hauteur && largeur == joueur1.largeur){
					System.out.println(joueur1.toString());					
				}else if(grilleJeu[hauteur][largeur].est_traversable()){
					System.out.print("  ");
				}else if (grilleJeu[hauteur][largeur].est_destructible()){
					System.out.print("dd");
				}else{
					System.out.print("[]");
				}
			}
			System.out.println("");
		}
	}
	
	public void setup(){
		window.size(250, 250);
        window.title("IBomberMan");
        window.showFPS();
        canvas.background(255, 200, 200);

        spriteJoueur1 = makeSprite(getImage("../images/joueur/joueur_bleu/bbas1.gif", 255, 255, 255));
        
        spriteJoueur1.position(joueur1.getHauteur(), joueur1.getLargeur());

        framerate(30);

	}
	
	public void move(){
		if(keyboard.isDown(keyboard.Z)){
			spriteJoueur1.nextY(spriteJoueur1.y()-10);
			joueur1.move(Joueur.Direction.N, grilleJeu[joueur1.getHauteur()+1][joueur1.getLargeur()].est_traversable());
		}else if(keyboard.isDown(keyboard.S)){
			spriteJoueur1.nextY(spriteJoueur1.y()+10);
			joueur1.move(Joueur.Direction.S, grilleJeu[joueur1.getHauteur()-1][joueur1.getLargeur()].est_traversable());
		}else if(keyboard.isDown(keyboard.Q)){
			spriteJoueur1.nextX(spriteJoueur1.x()-10);
			joueur1.move(Joueur.Direction.O, grilleJeu[joueur1.getHauteur()][joueur1.getLargeur()-1].est_traversable());
		}else if(keyboard.isDown(keyboard.D)){
			spriteJoueur1.nextX(spriteJoueur1.x()+10);
			joueur1.move(Joueur.Direction.E, grilleJeu[joueur1.getHauteur()][joueur1.getLargeur()+1].est_traversable());
		}
	}
	
	public void draw(){
		canvas.clear();

        this.move();

        spriteJoueur1.draw();
	}
	
	public static void main (String args[]){
		Plateau p = new Plateau(20, 20);
		p.afficherASCII();
	}
	
}



