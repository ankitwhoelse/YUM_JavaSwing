import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 * Programme principal qui d?marre le jeu de YUM pour un seul joueur.
 * 
 * Une s?rie de 5 d?s est g?n?r? al?atoirement et le joueur a droit
 * a changer les d?s qu'il d?sire ? deux reprises ? moins qu'il les
 * garde tous.
 * 
 * Par la suite, le programme offre toutes les possibilit?s de points 
 * pouvant ?tre jou?s et le joueur d?cide quel est son choix parmi 
 * ces possibilit?s.
 * 
 * Dans le cadre du cours inf111 (voir ?nnonc? fourni).
 * 
 * Auteur : Mettez le nom de chaque membre du groupe qui a suffisamment 
 *          contribu? en ?criture de code et de commentaires.
 *          
 * Auteur :
 * Auteur :
 * Auteur :
 * Auteur :
 * Auteur :
 * 
 * 
 * Auteur : Pierre B?lisle
 *          
 * Version : Copyright A2021
 */

class YumVsEtud {

	
	// Les constantes sont d?finies dans le module Constantes.java
	// Si vous en ajoutez, fa?tes-le ici.
	
	
	// Permet la saisie de donn?e au clavier en mode console.
	public static Scanner clavier = new Scanner(System.in);
	public static Random rand = new Random();
	public static void main(String[] args){

	    /* Traduisez ici l'algorithme du programme principal
	     * d?crit dans l'?nonc? et commenter votre code au fur et ? mesure.
	     */
		int des[] =  { rand.nextInt(6),rand.nextInt(6),rand.nextInt(6),rand.nextInt(6),rand.nextInt(6)};
		int grillePoint[] = new int[19];
		int grilleCoup[] = new int[19];
		int intCompteur = 1;
		Arrays.fill(grillePoint, -1);

		while (intCompteur != 13){

			intCompteur++;
		}
		ModAffichage mod = new ModAffichage();
		grillePoint[12] = 34;
		mod.afficherGrille(grillePoint);

	}
	
	/*
	 * ?crivez TOUS vos sous-programmes ici.  Il y en a entre 15 et 20.
	 */

	public static void lancerDes(int[] des, String desALancer){
		for(int i = 0; i < desALancer.length();i++){
			des[Integer.parseInt(desALancer.substring(i,i+1)) -1] = rand.nextInt(6);
		}
	}
 
}