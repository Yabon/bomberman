package bomberman.game;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import ucigame.Ucigame;


public class Editeur extends Ucigame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1413529883595625321L;
	/*Case[][] grilleJeu = new Case[13][15];
	Sprite s,d;
	boolean b = true;*/
	public JButton tab[][];
	
	public void initialisation(){
		JFrame f = new JFrame("editeur");
		f.setSize(1000,1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		Container c = f.getContentPane();
		c.setLayout(new GridLayout(13,15));
		tab = new JButton[13][15];
		for (int ligne = 0 ; ligne<tab.length;ligne++){
			for (int colonne = 0 ; colonne<tab[0].length;colonne++){
				if(ligne == 0 || ligne == tab.length-1 || colonne == 0 || colonne == tab[0].length-1){
					tab[ligne][colonne] = new JButton("i");
					c.add(tab[ligne][colonne]);
				}else{
				tab[ligne][colonne] = new JButton("c");
				tab[ligne][colonne].setIcon(new ImageIcon("../images/bloc/brique/dark.gif"));
				c.add(tab[ligne][colonne]);
				f.repaint();
				}	
			}
		}
	}
	ActionListener listener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			
		}
			
	};
	public void ajoutListener(){
		for (int ligne = 0 ; ligne<tab.length;ligne++){
			for (int colonne = 0 ; colonne<tab[0].length;colonne++){
				tab[ligne][colonne].addActionListener(listener);
			}
		}
	}
	
	public static void main(String argz[]){
		
		Editeur e = new Editeur();
		e.initialisation();
	}
/*public void affichage(){
		for (int hauteur = 1; hauteur < grilleJeu.length - 1; hauteur++) {
			for (int largeur = 1; largeur < grilleJeu[0].length - 1; largeur++) {
				grilleJeu[hauteur][largeur] = new Mur_Destructible(getImage("../images/bloc/pave/cityfree.gif", 255, 255, 255));
				grilleJeu[hauteur][largeur] = (Case) makeButton("Play", getImage(
					"../images/bloc/pave/cityfree.gif", 255, 255, 255), 64, 48);
							
			}
		}
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
	}
	public void setup() {
		//affichage();
		window.size(13 * 64, 15 * 48);
		window.title("IBomberMan");
		window.showFPS();
		canvas.background(100, 255, 100);
		this.resize(13 * 64, 15 * 48);

		s = makeButton("Play", getImage(
				"../images/bloc/pave/check.gif", 255, 255, 255), 64, 48);
		d = makeButton("Play", getImage(
				"../images/bloc/pave/check.gif", 255, 255, 255), 64, 48);
		d.position(150, 150);
		/*for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
				 if (grilleJeu[hauteur][largeur].est_traversable()) {
					// System.out.println("traversable");
					grilleJeu[hauteur][largeur].position(hauteur * 64,
							largeur * 48);

				} else if (grilleJeu[hauteur][largeur].est_destructible()) {
					// System.out.println("destruc");
					grilleJeu[hauteur][largeur].position(hauteur * 64,
							largeur * 48);

				} else {
					grilleJeu[hauteur][largeur].position(hauteur * 64,
							largeur * 48);
				}
			}
		}
		
		
		framerate(30);

	}
	public void onClickPlay(){
		int x = mouse.x();
		int y = mouse.y();
		System.out.println("toto");
		if(b){
			s = makeSprite(getImage(
				"../images/bloc/pave/cityfree.gif"));
			
			
		}else{
			s = makeSprite(getImage(
			"../images/bloc/brique/dark.gif"));
			
		}
		b = !b;
		
	
	}
	
	public void draw() {
	
		canvas.clear();
				
		for (int hauteur = 0; hauteur < grilleJeu.length; hauteur++) {
			for (int largeur = 0; largeur < grilleJeu[0].length; largeur++) {
				if (!(grilleJeu[hauteur][largeur] instanceof Chemin)) {
					grilleJeu[hauteur][largeur].draw();
				
				}
			}
		}
		s.draw();
		d.draw();
	}*/
}
