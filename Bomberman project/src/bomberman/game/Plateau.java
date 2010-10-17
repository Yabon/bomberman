package bomberman.game;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
	private String imageChemin = "../images/bloc/pave/karodark.gif";
	private String imageMurDestructible = "../images/bloc/pave/cityfree.gif";
	private String imageMurIndestructible = "../images/bloc/brique/brick.gif";
	private String imageBomber = "../images/joueur/Bomberblanc2.gif";
	private String imageBomber2 = "../images/joueur/Bombernoir2.gif";
	private String imageBombe = "../images/bombe/bombe0.gif";
	private String imageFlamme1H = "../images/flamme/12_vertic.gif";
	private String imageFlamme1V = "../images/flamme/3_horiz.gif";
	private String imageFlamme0N = "../images/flamme/8_bouthaut.gif";
	private String imageFlamme0S = "../images/flamme/4_boutbas.gif";
	private String imageFlamme0E = "../images/flamme/1_boutdroite.gif";
	private String imageFlamme0O = "../images/flamme/2_boutgauche.gif";
	private String imageFlamme00 = "../images/flamme/15_croix.gif";

	private Sound fondSonore = getSound("../sound/Hand in Hand.mp3");
	private Sound explodeSound = getSound("../sound/explosion.mp3");
	private Sound blowSound = getSound("../sound/souffleFlammes.mp3");

	private Case[][] grilleJeu;
	private int[][] effets;
	private List<Bombe> bombes;
	private List<Flamme> flammes;
	public static int hauteurImage, largeurImage;
	public int ratio = 100;// Entre 0 et 100 represente le pourcentage de murs
	// destructibles
	private Joueur joueur1, joueur2;

	public Plateau() {

		grilleJeu = new Case[13][15];
		effets = new int[13][15];

		flammes = new ArrayList<Flamme>(1024);
		bombes = new ArrayList<Bombe>(1024); // taille de la liste a l'origine
		// pour éviter la réallocation

		hauteurImage = getImage(imageChemin).height();
		largeurImage = getImage(imageChemin).width();
	}

	private void genererTerrain() {
		
		// génération de la grille d'effets (flammes)
		for (int hauteur = 1; hauteur < grilleJeu.length - 1; hauteur++) {
			for (int largeur = 1; largeur < grilleJeu[0].length - 1; largeur++) {
				effets[hauteur][largeur] = 0;
			}
		}

		// generation du millieu du plateau
		for (int hauteur = 1; hauteur < grilleJeu.length - 1; hauteur++) {
			for (int largeur = 1; largeur < grilleJeu[0].length - 1; largeur++) {
				if (Math.random() * 100 > ratio) {
					grilleJeu[hauteur][largeur] = new Chemin(getImage(
							imageChemin, 255, 255, 255), largeur, hauteur);
				} else {
					grilleJeu[hauteur][largeur] = new Mur_Destructible(
							getImage(imageMurDestructible, 255, 255, 255),
							largeur, hauteur);
				}
			}
		}
		// Generation du tour indéstructible
		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			grilleJeu[hauteur][0] = new Mur_Indestructible(getImage(
					imageMurIndestructible, 255, 255, 255), 0, hauteur);
			grilleJeu[hauteur][14] = new Mur_Indestructible(getImage(
					imageMurIndestructible, 255, 255, 255), 14, hauteur);
		}
		for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
			grilleJeu[0][largeur] = new Mur_Indestructible(getImage(
					imageMurIndestructible, 255, 255, 255), largeur, 0);
			grilleJeu[12][largeur] = new Mur_Indestructible(getImage(
					imageMurIndestructible, 255, 255, 255), largeur, 12);
		}

		// Generation des cases indéstructibles au millieu du terrain
		for (int hauteur = 2; hauteur < grilleJeu.length - 1; hauteur += 2) {
			for (int largeur = 2; largeur < grilleJeu[0].length - 1; largeur += 2) {
				grilleJeu[hauteur][largeur] = new Mur_Indestructible(getImage(
						imageMurIndestructible, 255, 255, 255), largeur,
						hauteur);
			}
		}

		// Generation du depart des joueurs

		grilleJeu[1][2] = new Chemin(getImage(imageChemin, 255, 255, 255), 2, 1);
		grilleJeu[2][1] = new Chemin(getImage(imageChemin, 255, 255, 255), 1, 2);
		grilleJeu[1][1] = new Chemin(getImage(imageChemin, 255, 255, 255), 1, 1);

		grilleJeu[11][13] = new Chemin(getImage(imageChemin, 255, 255, 255),
				13, 11);/* # */
		grilleJeu[11][12] = new Chemin(getImage(imageChemin, 255, 255, 255),
				11, 12);/* ## */
		grilleJeu[10][13] = new Chemin(getImage(imageChemin, 255, 255, 255),
				13, 10);

		grilleJeu[1][13] = new Chemin(getImage(imageChemin, 255, 255, 255), 13,
				1);/* ## */
		grilleJeu[1][12] = new Chemin(getImage(imageChemin, 255, 255, 255), 12,
				1);/* # */
		grilleJeu[2][13] = new Chemin(getImage(imageChemin, 255, 255, 255), 13,
				2);

		grilleJeu[11][1] = new Chemin(getImage(imageChemin, 255, 255, 255), 1,
				11);/* # */
		grilleJeu[11][2] = new Chemin(getImage(imageChemin, 255, 255, 255), 2,
				11);/* ## */
		grilleJeu[10][1] = new Chemin(getImage(imageChemin, 255, 255, 255), 1,
				10);

	}

	public void setup() {
		fondSonore.loop();
		
		//chargerSauvegarde();
		genererTerrain();
		genererJoueurs();

		resize(15 * 64, 13 * 48);
		window.size(15 * 64, 13 * 48);
		window.title("IBomberMan");
		window.showFPS();
		canvas.background(255, 255, 255);

		// ce bout de code n'à rien a faire là
		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
				grilleJeu[hauteur][largeur]
						.position(largeur * 64, hauteur * 48);
			}
		}

		framerate(30);

	}

	private void genererJoueurs() {
		Image bomber = getImage(imageBomber, 255, 255, 255);
		joueur1 = new Joueur(1, 1, 1, bomber);
		joueur1.addFrames(bomber, 175, 182, // Bas1
				262, 182, // Bas2
				350, 182, // Bas3
				0, 182, // Bas4
				87, 182, // Bas5
				175, 94, // Droite6
				262, 94, // Droite7
				350, 94, // Droite8
				0, 94, // Droite9
				87, 94, // Droite10
				175, 269, // Haut11
				262, 269, // Haut12
				350, 269, // Haut13
				0, 269, // Haut14
				87, 269, // Haut15
				175, 349, // Gauche16
				262, 349, // Gauche17
				350, 349, // Gauche18
				0, 349, // Gauche19
				87, 349); // Gauche20
		joueur1.defineSequence("Bas", 2, 3, 2, 1, 5, 4, 5, 1);
		joueur1.defineSequence("Droite", 7, 8, 7, 6, 10, 9, 10, 6);
		joueur1.defineSequence("Haut", 12, 13, 12, 11, 15, 14, 15, 11);
		joueur1.defineSequence("Gauche", 17, 18, 17, 16, 20, 19, 20, 16);
		joueur1.defineSequence("Stop", 1, 1);
		joueur1.framerate(15);

		Image bomber2 = getImage(imageBomber2, 255, 255, 255);
		joueur2 = new Joueur(2, 1, 13, bomber2);
		joueur2.addFrames(bomber2, 175, 182, // Bas1
				262, 182, // Bas2
				350, 182, // Bas3
				0, 182, // Bas4
				87, 182, // Bas5
				175, 94, // Droite6
				262, 94, // Droite7
				350, 94, // Droite8
				0, 94, // Droite9
				87, 94, // Droite10
				175, 269, // Haut11
				262, 269, // Haut12
				350, 269, // Haut13
				0, 269, // Haut14
				87, 269, // Haut15
				175, 349, // Gauche16
				262, 349, // Gauche17
				350, 349, // Gauche18
				0, 349, // Gauche19
				87, 349); // Gauche20
		joueur2.defineSequence("Bas", 2, 3, 2, 1, 5, 4, 5, 1);
		joueur2.defineSequence("Droite", 7, 8, 7, 6, 10, 9, 10, 6);
		joueur2.defineSequence("Haut", 12, 13, 12, 11, 15, 14, 15, 11);
		joueur2.defineSequence("Gauche", 17, 18, 17, 16, 20, 19, 20, 16);
		joueur2.defineSequence("Stop", 1, 1);
		joueur2.framerate(15);
	}

	public void chargerSauvegarde() {
		FileInputStream file;
		try {
			file = new FileInputStream("../sauvegarde.save");
			ObjectInputStream output = new ObjectInputStream(file);
			BoutonBomberman[][] boutons = (BoutonBomberman[][]) output
					.readObject();

			for (int largeur = 0; largeur < 15; largeur++) {
				for (int hauteur = 0; hauteur < 13; hauteur++) {
					if (boutons[hauteur][largeur].type.compareTo("mur") == 0) {
						grilleJeu[hauteur][largeur] = new Mur_Indestructible(
								getImage(imageMurIndestructible, 255, 255, 255),
								largeur, hauteur);
					} else if (boutons[hauteur][largeur].type
							.compareTo("chemin") == 0) {
						grilleJeu[hauteur][largeur] = new Chemin(getImage(
								imageChemin, 255, 255, 255), largeur, hauteur);
					} else if (boutons[hauteur][largeur].type
							.compareTo("caisse") == 0) {
						grilleJeu[hauteur][largeur] = new Mur_Destructible(
								getImage(imageMurDestructible, 255, 255, 255),
								largeur, hauteur);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		// Arrêt des animations joueurs si inactivité du clavier
		if(!(keyboard.isDown(keyboard.Z)) && !(keyboard.isDown(keyboard.S)) && !(keyboard.isDown(keyboard.Q)) && !(keyboard.isDown(keyboard.D))){
			joueur1.play("Stop");
		}
		if(!keyboard.isDown(keyboard.UP) && !keyboard.isDown(keyboard.DOWN) && !keyboard.isDown(keyboard.LEFT) && !keyboard.isDown(keyboard.RIGHT)){
			joueur2.play("Stop");
		}
		
		canvas.clear();

		// Affichage du plateau et gestion de la collision avec les murs
		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
				grilleJeu[hauteur][largeur].draw();
				if (!(grilleJeu[hauteur][largeur] instanceof Chemin)) {
					joueur1.stopIfCollidesWith(grilleJeu[hauteur][largeur]);
					joueur2.stopIfCollidesWith(grilleJeu[hauteur][largeur]);
				}
			}
		}

		// Affichage des flammes et gestion de leur expansion
		for (int i = 0; i < flammes.size(); i++) {
			Flamme f = flammes.get(i);
			if (!f.isBlown()) {
				f.draw();
				if ((!f.isSpread())
						&& grilleJeu[f.getHauteur()][f.getLargeur()]
								.est_traversable()) {
					f.spread();
				}
			} else {
				f.hide();
				flammes.remove(f);
				effets[f.getHauteur()][f.getLargeur()]--;
			}
		}

		// Affichage des bombes et gestion de leur explosion
		for (int i = 0; i < bombes.size(); i++) {
			Bombe b = bombes.get(i);
			if (b.isBurst()) {
				b.hide();
				bombes.remove(b);
			} else {
				b.draw();
				joueur1.stopIfCollidesWith(b);
				joueur2.stopIfCollidesWith(b);
				if (b.readyToExplode() || effets[b.getHauteur()][b.getLargeur()]>0) {
					b.burst();
					explodeSound.play();
					blowSound.play();
				}
			}
		}

		// Affichage des joueurs
		joueur1.draw();
		joueur2.draw();
	}

	public void mouvement() {
		if (keyboard.isDown(keyboard.Z)) {
			joueur1.nextY(joueur1.y() - 4);
			joueur1.play("Haut");

		}
		if(keyboard.isDown(keyboard.UP)){
			joueur2.nextY(joueur2.y() - 4);
			joueur2.play("Haut");
		}
		if (keyboard.isDown(keyboard.S)) {
			joueur1.nextY(joueur1.y() + 4);
			joueur1.play("Bas");
		}
		if (keyboard.isDown(keyboard.DOWN)) {
			joueur2.nextY(joueur2.y() + 4);
			joueur2.play("Bas");
		}
		if (keyboard.isDown(keyboard.Q)) {
			joueur1.nextX(joueur1.x() - 4);
			joueur1.play("Gauche");
		}
		if (keyboard.isDown(keyboard.LEFT)) {
			joueur2.nextX(joueur2.x() - 4);
			joueur2.play("Gauche");
		}
		if (keyboard.isDown(keyboard.D)) {
			joueur1.nextX(joueur1.x() + 4);
			joueur1.play("Droite");
		}
		if (keyboard.isDown(keyboard.RIGHT)) {
			joueur2.nextX(joueur2.x() + 4);
			joueur2.play("Droite");
		}
		if (keyboard.isDown(keyboard.SPACE)) {
			Bombe temp = new Bombe(joueur1.getHauteur(), joueur1.getLargeur(),
					2, getImage(imageBombe), this);
			if (bombes.size() == 0) {
				bombes.add(temp);
			} else {
				for (Bombe b : bombes) {
					if (!b.samePlace(temp)) {
						bombes.add(temp);
						break;
					}
				}
			}
		}
		if (keyboard.isDown(keyboard.ENTER)) {
			Bombe temp = new Bombe(joueur2.getHauteur(), joueur2.getLargeur(),
					2, getImage(imageBombe), this);
			if (bombes.size() == 0) {
				bombes.add(temp);
			} else {
				for (Bombe b : bombes) {
					if (!b.samePlace(temp)) {
						bombes.add(temp);
						break;
					}
				}
			}
		}
	}

	public void onKeyPress() {
		mouvement();
	}

	public void createFlamme(int hauteur, int largeur, int taille, Direction d, long dateFin) {
		if (hauteur > 0 && largeur > 0 && hauteur < grilleJeu.length && largeur < grilleJeu[0].length) {
			// si la flamme est dans l'aire de jeu
			if (grilleJeu[hauteur][largeur].est_destructible()) {
				if(!(grilleJeu[hauteur][largeur] instanceof Chemin)){ // destruction de l'obstacle
					grilleJeu[hauteur][largeur] = new Chemin(getImage(imageChemin), hauteur, largeur);
					taille = 0;
				}
				effets[hauteur][largeur]++;
				if(taille==0){ // Si c'est le dernier carré de flammes, on affiche le bout
					switch(d){
					case N :
						flammes.add(new Flamme(hauteur, largeur, getImage(imageFlamme0N),
								taille, this, d, dateFin));
						break;
					case S :
						flammes.add(new Flamme(hauteur, largeur, getImage(imageFlamme0S),
								taille, this, d, dateFin));
						break;
					case E :
						flammes.add(new Flamme(hauteur, largeur, getImage(imageFlamme0E),
								taille, this, d, dateFin));
						break;
					case O :
						flammes.add(new Flamme(hauteur, largeur, getImage(imageFlamme0O),
								taille, this, d, dateFin));
						break;
					}
				} else { // sinon, on affiche le corps
					if (d != null) { // Si c'est une branche
						switch (d) {
						case N:
							flammes.add(new Flamme(hauteur, largeur,
									getImage(imageFlamme1V), taille, this, d,
									dateFin));
							break;
						case S:
							flammes.add(new Flamme(hauteur, largeur,
									getImage(imageFlamme1V), taille, this, d,
									dateFin));
							break;
						case E:
							flammes.add(new Flamme(hauteur, largeur,
									getImage(imageFlamme1H), taille, this, d,
									dateFin));
							break;
						case O:
							flammes.add(new Flamme(hauteur, largeur,
									getImage(imageFlamme1H), taille, this, d,
									dateFin));
							break;
						}
					} else { // Sinon, c'est le centre de l'explosion
						flammes.add(new Flamme(hauteur, largeur,
								getImage(imageFlamme00), taille, this, d,
								dateFin));
					}
				}
			}
		}
	}
}
