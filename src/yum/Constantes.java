package yum;

/*
 * Contient les déclarations des constantes utilisées
 * dans le cadre de l'implémentation INF111A21
 * 
 * 
 * Si vous ajoutez des constantes, faites-le dans le même fichier que
 * votre main().  Ne touchez pas à ce module.
 * 
 * Auteur: Pierre Bélisle
 * Version : Copyright A2021
 */
public class Constantes {


	// Les limites.
	public static final int NB_CASES  = 19;
	public static final int NB_DES  = 5;
	public static final int NB_TOURS = 13;
	public static final int NB_ESSAIS = 3;
	public static final int DES_MIN = 1;
	public static final int NB_FACES = 6;

	// Les numéros de case du bonus.
	public static final int SOUS_TOTAL_HAUT = 7;
	public static final int BONUS_DU_HAUT = 8;
	public static final int TOTAL_HAUT = 9;
	public static final int TOTAL_BAS = 17;
	public static final int GRAND_TOTAL = 18;

	// Valeurs pour le calcul du bonus du haut.
	public static final int MIN_BONUS = 63;
	public static final int POINT_BONUS_HAUT = 25;

	// Les numéros de case des suites.
	public static final int PETITE = 4;
	public static final int GROSSE = 5;

	// Les numéros des autres cas de la grille de possibilités.
	public static final int BRELAN = 10;
	public static final int CARRE = 11;
	public static final int MAIN_PLEINE  = 12;
	public static final int PETITE_SUITE = 13;
	public static final int GROSSE_SUITE = 14;
	public static final int SURPLUS = 15;
	public static final int YUM = 16;
}
