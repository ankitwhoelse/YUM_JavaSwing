package yum;

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
 * Dans le cadre du cours INF111 (voir ?nnonc? fourni).
 *  *          
 * Auteur : Mohamed-Amine Djelloud
 * Auteur : Ankit Patel
 * 
 * Auteur : Pierre B?lisle
 *          
 * Version : Copyright A2021
 */

class Yum {

	
	// Les constantes sont d?finies dans le module Constantes.java
	// Si vous en ajoutez, fa?tes-le ici.
	
	
	// Permet la saisie de donn?e au clavier en mode console.
	public static Scanner clavier = new Scanner(System.in);
	public static Random rand = new Random();
	
	
	public static void main(String[] args){

	    /* Traduisez ici l'algorithme du programme principal
	     * d?crit dans l'?nonc? et commenter votre code au fur et ? mesure.
	     */
		// Creer un tableau pour 
		int grilleCoup[] = new int[Constantes.NB_CASES];
		
		// Creer et initialiser un tableau contenant les points 
		int grillePoint[] = new int[Constantes.NB_CASES];
		Arrays.fill(grillePoint, -1);
		grillePoint[Constantes.SOUS_TOTAL_HAUT] = 0;
		grillePoint[Constantes.BONUS_DU_HAUT] = 0;
		grillePoint[Constantes.TOTAL_HAUT] = 0;
		grillePoint[Constantes.TOTAL_BAS] = 0;
		grillePoint[Constantes.GRAND_TOTAL] = 0;
				
		int toursJoues = 0;

		// Boucle qui debute les 13 tours
		while (toursJoues < Constantes.NB_TOURS){

			System.out.println("\n\n*#*#* Tour no. " + (toursJoues+1) + " *#*#*");
			int des[] = { (rand.nextInt(Constantes.NB_FACES)), (rand.nextInt(Constantes.NB_FACES)),(rand.nextInt(Constantes.NB_FACES)),
					(rand.nextInt(Constantes.NB_FACES)),(rand.nextInt(Constantes.NB_FACES))};

			int intCompteurLancer = 0;
			int desARouler = -1;
			
			// Boucle qui permet les 3 essais
			while (intCompteurLancer < Constantes.NB_ESSAIS && desARouler != 0) {
				Arrays.fill(grilleCoup, 0);
				
				// Evalue tout les coups possible
				evaluerCoups(des, grilleCoup, grillePoint); 
				
				// Affiche les options
				ModAffichage.afficherDes(des);
				ModAffichage.afficherGrillePossibilite(grilleCoup);
				// Permet a l"utilisateur de choisir
				desARouler = lancerDes(des);	
		
				intCompteurLancer++;
			}
			// Evalue si un coup est possible
			evaluerCoups(des, grilleCoup, grillePoint); 
			boolean coupPossible = false;
			
			for(int i = 0; i < grilleCoup.length; i++) {
				if (grilleCoup[i] != 0) {
					coupPossible = true;
				}
			}
			
			if (coupPossible) {
				// Permet de choisir le placement de ses points
				System.out.println("Voici votre main: ");
				ModAffichage.afficherDes(des);
				ModAffichage.afficherGrillePossibilite(grilleCoup);
				ModAffichage.afficherGrille(grillePoint);
				System.out.println("(1 a 6) ou 10 = Brelan, 11 = Carre , 12 = Main pleine\n"
						+ "13 = Petite suite, 14 = Grosse suite, 15 = Surplus, 16 = Yum");
				
				noterPoints(grilleCoup, grillePoint);
			} else {
				// Aucun coup n'est possible
				System.out.println("\n*#* Aucun coup n'est possible avec votre main *#*.\n*#* Meilleure chance la prochaine fois. *#*");
				ModAffichage.afficherGrille(grillePoint);
			}
			
			
			toursJoues++;
		}
		// Fin des 13 tours
		//afficheInfo(toursJoues, grilleCoup,grillePoint);
		
		// Remplir les trous dans le tableau de points
		for(int i = 0; i < grillePoint.length; i++) {
			if (grillePoint[i] == -1) {
				grillePoint[i] = 0;
			}
		}
		
		// Total des points 1 a 6
		int somme1a6 = 0;
		for(int i = 0; i < Constantes.SOUS_TOTAL_HAUT; i++) {
			somme1a6 += grillePoint[i];
		}
		// Sous-total
		grillePoint[Constantes.SOUS_TOTAL_HAUT] = somme1a6;
		
		// Evaluation du boni (63+)
		if (somme1a6 >= Constantes.MIN_BONUS) {
			grillePoint[Constantes.BONUS_DU_HAUT] = Constantes.POINT_BONUS_HAUT;
		}
		
		// Total partie superieure
		grillePoint[Constantes.TOTAL_HAUT] = grillePoint[Constantes.SOUS_TOTAL_HAUT] + grillePoint[Constantes.BONUS_DU_HAUT];
		
		// Total partie du bas
		grillePoint[Constantes.TOTAL_BAS] = grillePoint[Constantes.BRELAN] + grillePoint[Constantes.CARRE] + 
				grillePoint[Constantes.MAIN_PLEINE] + grillePoint[Constantes.PETITE_SUITE] + grillePoint[Constantes.GROSSE_SUITE] 
				+ grillePoint[Constantes.SURPLUS] +	grillePoint[Constantes.YUM];
		
		// Grand total
		grillePoint[Constantes.GRAND_TOTAL] =  grillePoint[Constantes.TOTAL_HAUT] +  grillePoint[Constantes.TOTAL_BAS];
		
		System.out.println("\n\n*#*#* Le jeu est termine! *#*#*");
		ModAffichage.afficherGrille(grillePoint);
		
	}
	
	/*
	 * Ecrivez TOUS vos sous-programmes ici.  Il y en a entre 15 et 20.
	 */

    /*	TEST
     *  Methode qui affiche nos tableaux
     */
	public static void afficheDes(int[] des){
		System.out.println("***********  DES  ***********");
		System.out.println("       "+ des[0] + "  " + des[1] + "  " + des[2] + "  " + des[3] + "  " + des[4]);
		int tabTempo[] = occurenceDeDes(des);
		System.out.println("1:" + tabTempo[0] + "..2:" + tabTempo[1] +  "..3:" + tabTempo[2] +  
		 		 	"..4:" + tabTempo[3] + "..5:"  + tabTempo[4] +  "..6:"  + tabTempo[5] );
		System.out.println("***********  DES  ***********");
	}
	
	/*	TEST
     *  Methode qui affiche le statut du jeu
     */
	public static void afficheInfo(int toursJoues, int[] grilleCoups, int[] grillePoints){
		System.out.println("***********  INFO  ***********");
		System.out.println("Tour no " + toursJoues);
		System.out.println("Grille Coups");
		for(int i = 0; i<grilleCoups.length; i++) {
			System.out.print(grilleCoups[i] + " - ");
		} 
		System.out.println("Grille Points");
		for(int i = 0; i<grillePoints.length; i++) {
			System.out.print(grillePoints[i] + " - ");
		}
		System.out.println("***********  INFO  ***********");
	}
	
	
    /*
     *  Methode qui evalue les coups
     */
	public static void evaluerCoups(int[] des, int[] grilleCoup, int[] grillePoint){ 
		if (grillePoint[1] == -1) {
			compteDesUns(des,grilleCoup); 
		}
		if (grillePoint[2] == -1) {
			compteDesDeux(des,grilleCoup); 
		}
		if (grillePoint[3] == -1) {
			compteDesTrois(des,grilleCoup); 
		}
		if (grillePoint[4] == -1) {
			compteDesQuatre(des,grilleCoup); 
		}
		if (grillePoint[5] == -1) {
			compteDesCinq(des,grilleCoup); 
		}
		if (grillePoint[6] == -1) {
			compteDesSix(des,grilleCoup); 
		}		
		if (grillePoint[Constantes.BRELAN] == -1) {
			brelan(des,grilleCoup); 
		}
		if (grillePoint[Constantes.CARRE] == -1) {
			carre(des,grilleCoup);
		}
		if (grillePoint[Constantes.PETITE_SUITE] == -1) {
			courtSequence(des,grilleCoup); 
		}
		if (grillePoint[Constantes.GROSSE_SUITE] == -1) {
			longueSequence(des,grilleCoup); 
		}
		if (grillePoint[Constantes.SURPLUS] == -1) {
			surplus(des,grilleCoup); 
		}
		if (grillePoint[Constantes.MAIN_PLEINE] == -1) {
			mainPleine(des,grilleCoup); 
		}
		if (grillePoint[Constantes.YUM] == -1) {
			evalueYUM(des,grilleCoup); 
		}
	}
	
    /*
     *  Methode qui gere les points
     */
	public static void noterPoints(int[] grilleCoup, int[] grillePoint){
		boolean inputValide = false;
		
		while (!inputValide) {
			System.out.print("#Entrez le numero de case ou vous mettez les points : ");
			String point = "";
			int intPoint = 0;
	
			point = clavier.nextLine().trim();
			point = point.replaceAll("[^\\d.]", "");
			
			if (point.length() == 0) {
				System.out.println("Le numero de des est invalide, veuillez reessayer.");
				point = "0";
			} else {
				intPoint = Integer.parseInt(point);
			}
			
			// S'assurer que l'input est bien entre 0 et 17
			if (intPoint > 0 && intPoint < Constantes.TOTAL_BAS){

				if(grillePoint[intPoint] == -1){
					grillePoint[intPoint] = grilleCoup[intPoint];
					ModAffichage.afficherGrille(grillePoint);
					inputValide = true;
				} else {
					System.out.println("Votre selection est invalide, veuillez reessayer.");
				}
			}
		}
	}
	
	
    /*
     *  Methode qui reroule les des
     */
	public static int lancerDes(int[] des){
		String desARouler = "";
		boolean inputValide = false;
				
		while (!inputValide) {
			System.out.print("#Entrez les numeros des des que vous voulez rouler ou 0: ");
			desARouler = clavier.nextLine().trim();
			desARouler = desARouler.replaceAll("[^\\d.]", "");
			
			if (desARouler.length() == 0) {
				System.out.println("Le numero de des est invalide, veuillez reessayer.");
				lancerDes(des);
			}
			
			for(int i=0; i<desARouler.length(); i++) {
				if (Integer.parseInt(desARouler.substring(i,i+1)) < 0 || Integer.parseInt(desARouler.substring(i,i+1)) > 6) {
					System.out.println("Le numero de des est invalide, veuillez reessayer.");
					lancerDes(des);
				}
			}
			inputValide = true;
		}
		
		if(desARouler != "0"){
			for(int i = 0; i < desARouler.length(); i++){
				int de = Integer.parseInt(desARouler.substring(i,i+1)); 
				if (de > 0 && de < 6) {
					des[de-1] = rand.nextInt(Constantes.NB_FACES);
				}							
			}
		}

		return Integer.parseInt(desARouler.trim());
	}

    /*
     *  Methode qui evalue l'occurence d'un BRELAN
     */
	public static void brelan(int[] des , int grilleCoup[]){
		int tabTempo[] = occurenceDeDes(des);
		int desTotal = 0;
		
		for(int i = 0; i < tabTempo.length;i++){
			desTotal += (i+1)* tabTempo[i]; 
			if(tabTempo[i] == 3){
				grilleCoup[Constantes.BRELAN] = desTotal;
			}
		}
	}

    /*
     *  Methode qui evalue l'occurence d'un CARRE
     */
	public static void carre(int[] des , int grilleCoup[]){
		int tabTempo[] = occurenceDeDes(des);
		int desTotal = 0;
		
		for(int i = 0; i < tabTempo.length;i++){
			desTotal += (i+1)* tabTempo[i]; 
			if(tabTempo[i] == 4){
				grilleCoup[Constantes.CARRE] = desTotal;
			}
		}
	}

    /*
     *  Methode qui evalue l'occurence d'une COURTE SEQUENCE
     */
	public static void courtSequence(int[] des, int grilleCoup[]){
		int tabTempo[] = occurenceDeDes(des);

		int compteur = 0;
		while( compteur < 3){
			boolean booSequence = true;
			for(int i = 0 + compteur; i < (Constantes.PETITE + compteur) && booSequence;i++){
				if((tabTempo[i] != 0)){
					booSequence = true;
				}else{
					booSequence = false;
				}
			}
			if(booSequence){
				grilleCoup[Constantes.PETITE_SUITE] = 15;
			}
			compteur++;
		}
	}

    /*
     *  Methode qui evalue l'occurence d'une LONGUE SEQUENCE
     */
	public static void longueSequence(int[] des , int grilleCoup[]){
		int tabTempo[] = occurenceDeDes(des);

		int compteur = 0;
		while( compteur < 2){
			boolean booSequence = true;
			for(int i = 0 + compteur; i < (Constantes.GROSSE + compteur) && booSequence;i++){
				if((tabTempo[i] != 0)){
					booSequence = true;
				}else{
					booSequence = false;
				}
			}
			if(booSequence){
				grilleCoup[Constantes.GROSSE_SUITE] = 20;
			}
			compteur++;
		}
	}
	
	
    /*
     *  Methode qui evalue l'occurence d'un SURPLUS
     */
	public static void surplus(int[] des , int grilleCoup[]){
		int tabTempo[] = occurenceDeDes(des);
		int desTotal = 0;
		
		for(int i = 0; i < tabTempo.length; i++) {
			desTotal += (i+1)* tabTempo[i]; 
		}
		
		grilleCoup[Constantes.SURPLUS] = desTotal;		
	}
		

    /*
     *  Methode qui evalue l'occurence d'une MAIN PLEINE
     */
	public static void mainPleine(int[] des , int grilleCoup[]){
		int tabTempo[] = occurenceDeDes(des);

		boolean contains3 = Arrays.stream(tabTempo).anyMatch(n -> n == 3);
		boolean contains2 = Arrays.stream(tabTempo).anyMatch(n -> n == 2);

		if(contains3 && contains2){
			grilleCoup[Constantes.MAIN_PLEINE] = 25;
		}
	}


    /*
     *  Methode qui evalue l'occurence d'un YUM
     */
    public static void evalueYUM(int des[], int grilleCoup[]) {
    	int tabTempo[] = occurenceDeDes(des);
		int desTotal = 0;
		
		for(int i = 0; i < tabTempo.length;i++){
			desTotal += (i+1)* tabTempo[i]; 
			if(tabTempo[i] == 5){
				grilleCoup[Constantes.YUM] = desTotal;
			}
		}
    }


    /*
     *  Methode qui retourne un tableau qui indique le nombre de fois que j'ai de des.
     */
	public static int[] occurenceDeDes(int[] des){
		int tabTempo[] = new int [6];

		for(int i = 0; i < des.length;i++){
			switch (des[i]){
				case  1: tabTempo[0]++; break;
				case  2: tabTempo[1]++; break;
				case  3: tabTempo[2]++; break;
				case  4: tabTempo[3]++; break;
				case  5: tabTempo[4]++; break;
				case  0: tabTempo[5]++; break;
			}
		}
		return tabTempo;
	}

	/*
	 *	Methode qui compte l'occurences de UN
	 */
	public static void compteDesUns(int des[], int grilleCoup[]) {
		int qteUn = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 1) {
				qteUn++;
			}
		}
		grilleCoup[1] = qteUn*1;
	}
	
	/*
	 *	Methode qui compte l'occurences de DEUX
	 */
	public static void compteDesDeux(int des[], int grilleCoup[]) {
		int qteDeux = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 2) {
				qteDeux++;
			}
		}
		grilleCoup[2] = qteDeux*2;
	}
	
	/*
	 *	Methode qui compte l'occurences de TROIS
	 */
	public static void compteDesTrois(int des[], int grilleCoup[]) {
		int qteTrois = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 3) {
				qteTrois++;
			}
		}
		grilleCoup[3] = qteTrois*3;
	}
	
	/*
	 *	Methode qui compte l'occurences de QUATRE
	 */
	public static void compteDesQuatre(int des[], int grilleCoup[]) {
		int qteQuatre = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 4) {
				qteQuatre++;
			}
		}
		grilleCoup[4] = qteQuatre*4;
	}
	
	/*
	 *	Methode qui compte l'occurences de CINQ
	 */
	public static void compteDesCinq(int des[], int grilleCoup[]) {
		int qteCinq = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 5) {
				qteCinq++;
			}
		}
		grilleCoup[5] = qteCinq*5;
	}
	
	/*
	 *	Methode qui compte l'occurences de SIX
	 */
	public static void compteDesSix(int des[], int grilleCoup[]) {
		int qteSix = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 0) {
				qteSix++;
			}
		}
		grilleCoup[6] = qteSix*6;
	}
	

}