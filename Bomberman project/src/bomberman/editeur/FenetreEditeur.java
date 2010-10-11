package bomberman.editeur;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class FenetreEditeur {

	JFrame frame;
	Container container;
	BoutonBomberman[][] boutons = new BoutonBomberman[13][15];//[hauteur][largeur]
	public FenetreEditeur(){
		
		frame = new JFrame("Editeur");
		container = frame.getContentPane();
		frame.setSize(500, 500);               
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panelMenu = new JPanel();
		JPanel boutonEditeur = new JPanel();
		GridLayout gl = new GridLayout();
		gl.setColumns(15);
		gl.setRows(13);
		boutonEditeur.setLayout(gl);

		BoutonBomberman bouton;
		for(int hauteur = 0 ; hauteur < 13 ; hauteur++){
			for(int largeur = 0 ; largeur < 15 ; largeur++){
				bouton = new BoutonBomberman("mur");
				boutons[hauteur][largeur]=bouton;
				boutonEditeur.add(bouton);
				bouton.addMouseListener(new MonMouseListener(bouton));			
				if(hauteur == 0 || largeur == 0 || hauteur == 12 || largeur == 14){
					bouton.setEnabled(false);
				}
			}	
		}
		boutons[1][1].setImage("chemin");
		boutons[1][1].setEnabled(false);
		boutons[1][13].setImage("chemin");
		boutons[1][13].setEnabled(false);
		boutons[11][1].setImage("chemin");
		boutons[11][1].setEnabled(false);
		boutons[11][13].setImage("chemin");
		boutons[11][13].setEnabled(false);
		
		BoutonBomberman boutonV = new BoutonBomberman("valider");
		panelMenu.add(boutonV);
		boutonV.addMouseListener(new MonMouseListener(boutonV));
		boutonV.setFenetre(this);
		
		container.setLayout(new BorderLayout());
		container.add(boutonEditeur,BorderLayout.CENTER);
		container.add(panelMenu,BorderLayout.SOUTH);
		frame.pack();
		
	}
	
	public void getBoutons(){
		
	}
	
	
}
