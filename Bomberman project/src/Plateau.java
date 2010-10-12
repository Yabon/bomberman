import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import bomberman.editeur.BoutonBomberman;
import ucigame.*;

public class Plateau extends Ucigame {

	private static final long serialVersionUID = -2152091601478495650L;
	/**
	 * On utilisera la première dimension pour la verticalité et la deuxième
	 * pour l'horizontalité
	 */
	private Case[][] grilleJeu;
	private Sound fondSonore = getSound("../sound/Hand in Hand.mp3");
	private Sound explodeSound = getSound("../sound/explosion.mp3");
	private Sound blowSound = getSound("../sound/souffleFlammes.mp3");
	private List<Bombe> bombes;
	private List<Flamme> flammes;
	public int ratio = 0;// Entre 0 et 100 represente le pourcentage de murs
	// destructibles
	private Joueur joueur1;

	public Plateau() {

		grilleJeu = new Case[13][15];
		// à implémenter : génération d'un plateau aléatoire
		genererTerrain();
		// placer les joueurs a implémenter

		bombes = new LinkedList<Bombe>(); // taille de la liste a l'origine
		// pour éviter la réallocation

		flammes = new ArrayList<Flamme>(1024);
	}

	public Case getCase(int posX, int posY) {
		return grilleJeu[posY][posX];
	}

	private void genererTerrain() {

		// generation du millieu du plateau
		for (int hauteur = 1; hauteur < grilleJeu.length - 1; hauteur++) {
			for (int largeur = 1; largeur < grilleJeu[0].length - 1; largeur++) {
				if (Math.random() * 100 > ratio) {
					grilleJeu[hauteur][largeur] = new Chemin(getImage(
							"../images/bloc/pave/karodark.gif", 255, 255, 255));
				} else {
					grilleJeu[hauteur][largeur] = new Mur_Destructible(
							getImage("../images/bloc/pave/cityfree.gif", 255,
									255, 255));
				}
			}
		}
		// Generation du tour indéstructible
		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			grilleJeu[hauteur][0] = new Mur_Indestructible(getImage(
					"../images/bloc/brique/brick.gif", 255, 255, 255));
			grilleJeu[hauteur][14] = new Mur_Indestructible(getImage(
					"../images/bloc/brique/brick.gif", 255, 255, 255));
		}
		for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
			grilleJeu[0][largeur] = new Mur_Indestructible(getImage(
					"../images/bloc/brique/brick.gif", 255, 255, 255));
			grilleJeu[12][largeur] = new Mur_Indestructible(getImage(
					"../images/bloc/brique/brick.gif", 255, 255, 255));
		}

		// Generation des cases indéstructibles au millieu du terrain
		for (int hauteur = 2; hauteur < grilleJeu.length - 1; hauteur += 2) {
			for (int largeur = 2; largeur < grilleJeu[0].length - 1; largeur += 2) {
				grilleJeu[hauteur][largeur] = new Mur_Indestructible(getImage(
						"../images/bloc/brique/brick.gif", 255, 255, 255));
			}
		}

		// Generation du depart des joueurs

		
		grilleJeu[1][2] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255)); 
		grilleJeu[2][1] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));
		grilleJeu[1][1] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));

		

		grilleJeu[11][13] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));/* # */
		grilleJeu[11][12] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));/* ## */
		grilleJeu[10][13] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));

		grilleJeu[1][13] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));/* ## */
		grilleJeu[1][12] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));/* # */
		grilleJeu[2][13] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));

		grilleJeu[11][1] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));/* # */
		grilleJeu[11][2] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));/* ## */
		grilleJeu[10][1] = new Chemin(getImage(
				"../images/bloc/pave/karodark.gif", 255, 255, 255));

	}

	public void setup() {
		/* AJOUT TEST */
		// génération d'un plateau aléatoire
		fondSonore.loop();
		//chargerSauvegarde();
		genererTerrain();
		genererJoueurs();


		
		bombes = new LinkedList<Bombe>();
		/* FIN AJOUT TEST */

		resize(15 * 64, 13 * 48);
		window.size(15 * 64, 13 * 48);
		window.title("IBomberMan");
		window.showFPS();
		canvas.background(255, 255, 255);

		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
					grilleJeu[hauteur][largeur].position(largeur * 64, hauteur * 48);
			}
		}

		framerate(30);

	}

	private void genererJoueurs() {
		Image bomber = getImage("../images/joueur/Bomberblanc2.gif", 255, 255, 255);
		joueur1 = new Joueur(Joueur.Direction.S, 1, 1 ,1 , bomber);
		joueur1.addFrames(bomber, 
				175,182,        //Bas1
				262,182,		//Bas2
				350,182,		//Bas3
				0,182,			//Bas4
				87,182,			//Bas5
				175,94,         //Droite6
				262,94,			//Droite7
				350,94,			//Droite8
				0,94,			//Droite9
				87,94,			//Droite10		
				175,269,        //Haut11
				262,269,		//Haut12
				350,269,		//Haut13
				0,269,			//Haut14
				87,269,			//Haut15
				175,349,        //Gauche16
				262,349,		//Gauche17
				350,349,		//Gauche18
				0,349,			//Gauche19
				87,349);		//Gauche20	
		joueur1.defineSequence("Bas", 2,3,2,1,5,4,5,1);
		joueur1.defineSequence("Droite", 7,8,9,10,6);
		joueur1.defineSequence("Haut", 12,13,12,11,15,14,15,11);
		joueur1.defineSequence("Gauche", 17,18,19,20,16);
		joueur1.framerate(15);
	}

	public void chargerSauvegarde(){
		FileInputStream file;
		try {
			file = new FileInputStream("../sauvegarde.save");
			ObjectInputStream output = new ObjectInputStream(file);
			BoutonBomberman[][] boutons = (BoutonBomberman[][]) output.readObject();
			
			for(int largeur = 0 ; largeur < 15 ; largeur++){
				for(int hauteur = 0 ; hauteur < 13 ; hauteur++){
					if(boutons[hauteur][largeur].type.compareTo("mur")==0){
						grilleJeu[hauteur][largeur] = new Mur_Indestructible(getImage(
								"../images/bloc/brique/brick.gif", 255, 255, 255));
					}else if (boutons[hauteur][largeur].type.compareTo("chemin")==0){
						grilleJeu[hauteur][largeur] = new Chemin(getImage(
								"../images/bloc/pave/karodark.gif", 255, 255, 255));
					}else if (boutons[hauteur][largeur].type.compareTo("caisse")==0){
						grilleJeu[hauteur][largeur] = new Mur_Destructible(
								getImage("../images/bloc/pave/cityfree.gif", 255,
										255, 255));
					}
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void draw() {
		for(int i=0; i<grilleJeu.length; i++){
			for(int j=0; j<grilleJeu[0].length; j++){
				if (!(grilleJeu[i][j] instanceof Chemin)){
					joueur1.stopIfCollidesWith(grilleJeu[i][j]);
				}				
			}
		}
		canvas.clear();
		
		
		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
				grilleJeu[hauteur][largeur].draw();
			}
		}
		for(Flamme f : flammes){
			if(f.isBlown()){
				f.hide();
			}else{
				f.draw();
				//blowSound.play();
				f.spread();
			}
		}
		for( Bombe b : bombes){
			if(b.isBurst()){
				b.hide();
			}else{
				b.draw();
				joueur1.stopIfCollidesWith(b);
				if(b.readyToExplode()){
					b.burst();
					//explodeSound.play();
				}
			}
		}	
		joueur1.draw();
		
		
	}
	
	public void mouvement(){
		 if (keyboard.isDown(keyboard.UP, keyboard.Z))
         {
             joueur1.nextY(joueur1.y() - 4);
             joueur1.play("Haut");
           
         }
         if (keyboard.isDown(keyboard.DOWN, keyboard.S))
         {
             joueur1.nextY(joueur1.y() + 4);
             joueur1.play("Bas");
         }
         if (keyboard.isDown(keyboard.LEFT, keyboard.Q))
         {
             joueur1.nextX(joueur1.x() - 4);
             joueur1.play("Gauche");
         }
         if (keyboard.isDown(keyboard.RIGHT, keyboard.D))
         {
             joueur1.nextX(joueur1.x() + 4);
             joueur1.play("Droite");
         }
         if(keyboard.isDown(keyboard.SPACE)){
         	bombes.add(new Bombe(joueur1.getHauteur()/48,joueur1.getLargeur()/64,5,getImage("../images/bombe/bombe0.gif"),this));
         }
	}
	
	public void onKeyPress()
    {
           mouvement();
    }

	public void createFlamme(int x, int y, int taille, Joueur.Direction d){
		if(x>0 && y>0 && x<grilleJeu.length && y<grilleJeu[0].length){
			if(grilleJeu[y][x].est_destructible()){
				flammes.add(new Flamme(x, y, getImage("../images/flamme/0_zero.gif"), taille, this, d));	
			}
		}
	}
}
