package bomberman.editeur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class MonMouseListener implements MouseListener{

	BoutonBomberman bouton;
	public MonMouseListener(BoutonBomberman bouton){
		this.bouton = bouton;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(bouton.type.compareTo("mur")==0){
			/*chemin*/
			bouton.img = new ImageIcon("./images/bloc/pave/karodark.gif");
			bouton.type="chemin";		
		}else if(bouton.type.compareTo("caisse")==0){
			/*indestructible*/
			bouton.img = new ImageIcon("./images/bloc/brique/brick.gif");
			bouton.type="mur";			
		}else if (bouton.type.compareTo("chemin")==0){
			/*destructible*/
			bouton.img = new ImageIcon("./images/bloc/pave/cityfree.gif");
			bouton.type="caisse";
		}
		bouton.setIcon(bouton.img);	
		
		if(bouton.type.compareTo("valider")==0){
			bouton.sauvegarder();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
