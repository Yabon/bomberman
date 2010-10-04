import java.util.List;
import java.util.ArrayList;
import ucigame.*;

public class Plateau extends Ucigame {

	/**
	 * On utilisera la première dimension pour la verticalité et la deuxième
	 * pour l'horizontalité
	 */
	private Case[][] grilleJeu;
	
	private List<Bombe> bombes;
	public int ratio = 0;// Entre 0 et 100 represente le pourcentage de murs
	// destructibles
	private Joueur joueur1;

	private Plateau(int h, int l) {
		grilleJeu = new Case[h][l];
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
					"../images/bloc/pave/brick.gif", 255, 255, 255));
			grilleJeu[hauteur][14] = new Mur_Indestructible(getImage(
					"../images/bloc/pave/brick.gif", 255, 255, 255));
		}
		for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
			grilleJeu[0][largeur] = new Mur_Indestructible(getImage(
					"../images/bloc/pave/brick.gif", 255, 255, 255));
			grilleJeu[12][largeur] = new Mur_Indestructible(getImage(
					"../images/bloc/pave/brick.gif", 255, 255, 255));
		}

		// Generation des cases indéstructibles au millieu du terrain
		for (int hauteur = 2; hauteur < grilleJeu.length - 1; hauteur += 2) {
			for (int largeur = 2; largeur < grilleJeu[0].length - 1; largeur += 2) {
				grilleJeu[hauteur][largeur] = new Mur_Indestructible(getImage(
						"../images/bloc/pave/brick.gif", 255, 255, 255));
			}
		}
		bombes = new ArrayList<Bombe>(1024); // taille de la liste a l'origine
		// pour éviter la réallocation
	}

	public Plateau() {

		grilleJeu = new Case[13][15];
		// à implémenter : génération d'un plateau aléatoire
		genererTerrain();
		// placer les joueurs a implémenter

		bombes = new ArrayList<Bombe>(1024); // taille de la liste a l'origine
		// pour éviter la réallocation
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
		genererTerrain();
		genererJoueurs();

		
		bombes = new ArrayList<Bombe>(1024); // taille de la liste a l'origine
		// pour éviter la réallocation
		/* FIN AJOUT TEST */

		resize(13 * 64, 15 * 48);
		window.size(13 * 64, 15 * 48);
		window.title("IBomberMan");
		window.showFPS();
		canvas.background(100, 255, 100);
		this.resize(13 * 64, 15 * 48);

		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
					grilleJeu[hauteur][largeur].position(hauteur * 64, largeur * 48);
			}
		}

		framerate(30);

	}

	private void genererJoueurs() {
		joueur1 = new Joueur(Joueur.Direction.N, 1, 5 ,7 , getImage(
				"../images/joueur/skelbas.gif", 255, 255, 255));
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
		
		joueur1.draw();
	}

	public void onKeyPress()
    {
            if (keyboard.isDown(keyboard.UP, keyboard.Z))
            {
                joueur1.nextY(joueur1.y() - 10);
            }
            else if (keyboard.isDown(keyboard.DOWN, keyboard.S))
            {
                joueur1.nextY(joueur1.y() + 10);
            }
            else if (keyboard.isDown(keyboard.LEFT, keyboard.Q))
            {
                joueur1.nextX(joueur1.x() - 10);
            }
            else if (keyboard.isDown(keyboard.RIGHT, keyboard.D))
            {
                joueur1.nextX(joueur1.x() + 10);
            }
        
    }
}
