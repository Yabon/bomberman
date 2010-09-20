import java.util.List;
import java.util.ArrayList;
public class Plateau {
	
	/**
	 * On utilisera la première dimension pour la verticalité et la deuxième pour l'horizontalité
	 */
	private Case[][] grilleJeu;
	List<Bombe> bombes;
	
	public Plateau(int hauteur, int largeur){
		grilleJeu = new Case[hauteur][largeur];
		// à implémenter : génération d'un plateau aléatoire
		bombes = new ArrayList<Bombe>(1024); // taille de la liste a l'origine pour éviter la réallocation
	}
	
	public Case getCase(int posX, int posY){
		return grilleJeu[posY][posX];
	}
}
