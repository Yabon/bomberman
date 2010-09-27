/*
  La galerie est repérée selon ce schéma :
  XBBBBBTBBBBBB   X est aux coordonnees (0,0)
  B           B   T est aux coordonnees (0,6)
  B     Y     B   Y est au sud de T
  B           B   T est a l'es tde X
  B           B
  B           B
  B           B
  BBBBBBBBBBBBB
*/

import java.util.ArrayList;
import java.util.List;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;

public class Galerie extends Canvas{
	static final long serialVersionUID=0;// je sais, c'est dégueu ^^
    private Case[][] galerie; 
    int c = 50; // taille d'une cellule
    Image mur = getToolkit().getImage("./mur.jpg");
    Image but = getToolkit().getImage("./chemin.jpg");
    Image chemin = getToolkit().getImage("./but.jpg");
    Image taupeN = getToolkit().getImage("./nord.jpg");
    Image taupeE = getToolkit().getImage("./est.jpg");
    Image taupeS = getToolkit().getImage("./sud.jpg");
    Image taupeO = getToolkit().getImage("./ouest.jpg");
    
    // crée une galerie identique a celui du fichier enregistré précédemment
    public Galerie(){
    	char[] charBuffer = new char[1];
		String galerieTemp = "";
		int fin=0;
		FileReader fichier = null;
		// lecture complète du fichier
		try{
			fichier = new FileReader("./galerie.gal");
			while(fin!=-1){
				fin=fichier.read(charBuffer, 0, 1);
				galerieTemp+=String.copyValueOf(charBuffer);
				charBuffer = new char[1];
			}
			fichier.close();
		}catch(FileNotFoundException e){
			System.out.println("fichier non-trouvé");	
		}catch(Exception e){
			System.out.println("Problème survenu lors de la lecture du fichier.");
		}
		// traitement des données
		// compter le nombre de lignes et de collonnes
		int i=0, nbCollonnes=0, nbLignes=1;
		while(galerieTemp.charAt(i)!='\n'){
			i++;
		}
		nbCollonnes = i++;
		while(galerieTemp.charAt(i)!='$'){
			if(galerieTemp.charAt(i)=='\n'){
				nbLignes++;
			}
			i++;
		}
		// associer a chaques cases le type de case (vide, mur ou but)
		int nbNVues = 0, ligne=0, collonne=0;
		galerie = new Case[nbLignes][nbCollonnes];
		for(i=0; i<galerieTemp.length(); i++){
			if(galerieTemp.charAt(i)=='B'){
				galerie[ligne][collonne] = new CaseBut(ligne, collonne);
				collonne++;
			}else if(galerieTemp.charAt(i)== 'M'){
				galerie[ligne][collonne] = new CaseMur(ligne, collonne);
				collonne++;
			}else if(galerieTemp.charAt(i)== ' '){
				galerie[ligne][collonne] = new CaseVide(ligne, collonne);
				collonne++;
			}else if(galerieTemp.charAt(i)== '\n'){
				nbNVues++;
				collonne=0;
				ligne++;
			}else if(galerieTemp.charAt(i)== 'T'){
				galerie[ligne][collonne] = new CaseVide(ligne, collonne);
				collonne++;}
			else if(galerieTemp.charAt(i)== '$'){
				}
		}
		// y plonger la taupe
    	galerie[nbLignes/2][nbCollonnes/2] = new Taupe(nbCollonnes/2, nbLignes/2, this);
    }
    
    public Galerie(int hauteur, int largeur, double ratio){
    	galerie = new Case[hauteur][largeur];
    	genererNouveau(hauteur, largeur, ratio);
    }
    
    //Affiche graphiquement la galerie
    public void afficherGraphiquement(){

    	//cr�e une nouvelle fen�tre de nom Galerie et ajoute la galerie cr��
    	Frame f = new Frame("Galerie");
    	f.setBounds(100,100,c*galerie[0].length+8, c*galerie.length+28);
    	f.add(this);

    	//ajoute les images dans un tracker
    	MediaTracker tracker = new MediaTracker(f);
    	tracker.addImage(mur,0);
    	tracker.addImage(but,1);
    	tracker.addImage(chemin,2);
    	tracker.addImage(taupeN,3);
    	tracker.addImage(taupeE,4);
    	tracker.addImage(taupeS,5);
    	tracker.addImage(taupeO,6);


    	try{
    		tracker.waitForAll(0);
    	}catch(Exception e){return;}

    	//affiche la fenetre Galerie
    	f.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) {
    			System.exit(0);
    		}
    	});
    	f.setVisible(true);
   	}
    
    public void update(Graphics g) {
    	paint(g);
    }
    
    public void paint(Graphics g) {
    	// dessine les cases de la Galerie
    	for (int x=0; x<galerie.length; x++){
    		for (int y=0; y<galerie[x].length; y++){
    			if (galerie[x][y].estBut()) {
        		    g.drawImage(chemin,y*c,x*c,c,c,null);
    			}
    			else if(galerie[x][y].estTaupe()){
    				switch (((Taupe)galerie[x][y]).getDirection()){
    				case NORD :
    					g.drawImage(taupeN,y*c,x*c,c,c,null);
    					break;
    				case EST :
    					g.drawImage(taupeE,y*c,x*c,c,c,null);
    					break;
    				case OUEST :
    					g.drawImage(taupeO,y*c,x*c,c,c,null);
    					break;
    				case SUD :
    					g.drawImage(taupeS,y*c,x*c,c,c,null);
    					break;
    				}
    			}
    			else if(!galerie[x][y].estLibre()){
    				g.drawImage(mur,y*c,x*c,c,c,null);
    			}
    			else if(galerie[x][y].estLibre()){
    				g.drawImage(but,y*c,x*c,c,c,null);
    			}	
    		}
    	}
    }
	
    public void setCase(Case nouvelleCase){
    	galerie[nouvelleCase.getL()][nouvelleCase.getC()] = nouvelleCase;
    }
	
    public Case getCase(int ligne, int colonne){
    	return galerie[ligne][colonne];
    }
	
    public Case getCase(Coordonnees c){
    	return galerie[c.getL()][c.getC()];
    }
	
    public Taupe getTaupe(){
    	return (Taupe) galerie[galerie.length/2][galerie[0].length/2];
    }
	
    public void sauvegarde(){
    	File monFichier = new File("./galerie.gal");
    	FileWriter fichier = null;
    	String aEcrire = "";
    	for(int i=0; i<galerie.length; i++){
			for(int j=0; j<galerie[0].length; j++){
				aEcrire += galerie[i][j].getEtat();
			}
			aEcrire += '\n';
		}
		aEcrire += '$';
    	try{
    		if(monFichier.exists())monFichier.delete();
    		fichier = new FileWriter(monFichier);
    		//ecrire dans le fichier la nouvelle galerie
    		fichier.write(aEcrire);
    		fichier.close();
    	}catch(IOException e){
    		System.out.println("Une erreur est survenue lors de l'écriture. \nLa galerie n'a pa pu être sauvegardée.");
    	}
    }
    
    //génère une nouvelle galerie aléatoire en prenant en compte la ratio de mur
    public void genererNouveau(int hauteur, int largeur, double ratio){
    	int nbTests = 0;
    	do{
    		// on remplis toute la galerie avec des cases but
    		for(int i=0; i<hauteur; i++){
    			for(int j=0; j<largeur; j++){
    				if(i == 0 || i == hauteur-1 || j == 0 || j == largeur-1){
    					galerie[i][j] = new CaseBut(i, j);
    				}
    			}
    		}
    		// puis on remplis l'interieur avec des cases vides et des murs
    		for(int i=1; i<hauteur-1; i++){
    			for(int j=1; j<largeur-1; j++){
    				if(Math.random() > ratio){
    					galerie[i][j] = new CaseVide(i, j);
    				}else{
    					galerie[i][j] = new CaseMur(i, j);
    				}
    			}
    		}
    		nbTests++;
    	}while(nbTests<1000 || (! existeCheminSortie()));
    	if(nbTests==999){
    		System.out.println("Aucune galerie n'a pu être créée");
    		System.exit(0);
    	}
    	// enfin, on place notre taupe au milieu de cette galerie
    	galerie[hauteur/2][largeur/2] = new Taupe(hauteur/2, largeur/2, this);
    }
	
    private boolean existeCheminSortie(){
    	List<Coordonnees> dejaParcourues = new ArrayList<Coordonnees>();
    	return testCheminSortieRecursif(new Coordonnees(galerie.length/2, galerie[0].length/2), dejaParcourues);
    }
	
    private boolean testCheminSortieRecursif(Coordonnees c, List<Coordonnees> dejaP){
    	boolean n = false, o=false, e=false, s=false;	
    	if(galerie[c.getL()][c.getC()].estBut()){
    		return true;
    	}else if(! galerie[c.getL()][c.getC()].estLibre()){
    		dejaP.add(c);
    	}else if(galerie[c.getL()][c.getC()].estLibre()){
    		dejaP.add(c);
    		if(! dejaP.contains(c.getSud())){
    			s = testCheminSortieRecursif(c.getSud(), dejaP);
    		}if(! dejaP.contains(c.getNord())){
    			n = testCheminSortieRecursif(c.getNord(), dejaP);
    		}if(! dejaP.contains(c.getEst())){
    			e = testCheminSortieRecursif(c.getEst(), dejaP);
    		}if(! dejaP.contains(c.getOuest())){
    			o = testCheminSortieRecursif(c.getOuest(), dejaP);
    		}
    		return n || e || o || s;
    	}
    	return false;
    }
	
    public String toString(){
    	String retour = "";
    	for(int i=0; i<galerie.length; i++){
    		for(int j=0; j<galerie[i].length; j++){
    			retour += galerie[i][j];
    		}
    		retour += "\n";
    	}
    	return retour;
    }
	
    public static void main(String args[]){
    	Galerie maGalerie = new Galerie(13,11,0.65);
    	maGalerie.afficherGraphiquement();
    	maGalerie.sauvegarde();
    	maGalerie = new Galerie();
    }
}
