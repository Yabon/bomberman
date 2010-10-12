package bomberman.editeur;



import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoutonBomberman extends JButton{
	


	private static final long serialVersionUID = 1L;
	ImageIcon img;
	public String type ="";
	FenetreEditeur fenetre;

	
	public BoutonBomberman(String s){
		super();
		if(s.compareTo("mur")==0){
			/*indestructible*/
			img = new ImageIcon("./images/bloc/brique/brick.gif");
		}else if(s.compareTo("caisse")==0){
			/*destructible*/
			img = new ImageIcon("./images/bloc/pave/cityfree.gif");
		}else if(s.compareTo("chemin")==0){
			/*chemin*/
			img = new ImageIcon("./images/bloc/pave/karodark.gif");
		}else if (s.compareTo("valider")==0){
			img = new ImageIcon("./images/Valider.jpg");
		}
		this.setIcon(img);
		this.type = s;

	}

	public void setFenetre(FenetreEditeur f){
		fenetre = f; 
	}
	
	public void setImage(String s) {
		if(s.compareTo("mur")==0){
			/*indestructible*/
			img = new ImageIcon("./images/bloc/brique/brick.gif");
		}else if(s.compareTo("caisse")==0){
			/*destructible*/
			img = new ImageIcon("./images/bloc/pave/cityfree.gif");
		}else if(s.compareTo("chemin")==0){
			/*chemin*/
			img = new ImageIcon("./images/bloc/pave/karodark.gif");
		}
		this.setIcon(img);
		this.type = s;
		
	}

	public void sauvegarder() {
		File f = new File("./sauvegarde.save");
		if(f.exists()){
			f.delete();
		}
		FileOutputStream file;
		try {
			file = new FileOutputStream(f);
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(fenetre.boutons);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
		
	}

	

}
