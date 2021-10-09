package yum;

import yum.Constantes;
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
 * Auteur : Ankit Patel
 * Auteur : Djelloud
 * Auteur :
 * Auteur :
 * Auteur :
 * 
 * 
 * Auteur : Pierre B?lisle
 *          
 * Version : Copyright A2021
 */

public class Yum {

	// Les constantes sont d?finies dans le module Constantes.java
	// Si vous en ajoutez, fa?tes-le ici.
	
	
	// Permet la saisie de donn?e au clavier en mode console.
	public static Scanner clavier = new Scanner(System.in);
	public static Random rand = new Random();
	
	public static void main(String[] args){

	    /* Traduisez ici l'algorithme du programme principal
	     * decrit dans l'enonce et commenter votre code au fur et a mesure.
	     */
		
		int des[] =  { rand.nextInt(6),rand.nextInt(6),rand.nextInt(6),rand.nextInt(6),rand.nextInt(6)};
		int grillePoint[] = new int[19];
		int grilleCoup[] = new int[19];
		
		
		int nbEssais = 1;
		boolean gardeDes = false;
		
		
		Arrays.fill(grillePoint, -1);
		
		
		// Boucle du jeu
		while (nbEssais != Constantes.NB_TOURS){
						
			ModAffichage.afficherGrille(grillePoint);	// Afficher la grille
			ModAffichage.afficherDes(des); 				// Afficher les des
			System.out.println(des[0] + " " + des[1] + " " + des[2] + " " + des[3] + " " + des[4]);
			// Evaluer les coups possibles
			coupsPossible(grilleCoup, des);
			
			ModAffichage.afficherGrillePossibilite(grilleCoup);			
			System.out.print("Entrez les numéros des dés que vous voulez rouler ou 0 (ex: 135): ");
			clavier.nextLine();
			
			
			nbEssais++;
		}
		
		grillePoint[12] = 34;
	}
	
	
	/******************************************************************
	  Ecrivez TOUS vos sous-programmes ici.  Il y en a entre 15 et 20.
	 ******************************************************************/

	/*
	 *  Methode qui evalue les coups possibles
	 */
	public static void coupsPossible(int grilleCoup[], int des[]) {
		
		// Evaluer l'occurences des chiffres simples
		int un= compteDesUns(des);
		grilleCoup[1] = un*1;
		int deux = compteDesDeux(des);
		grilleCoup[2] = deux*2;
		int trois = compteDesTrois(des);
		grilleCoup[3] = trois*3;
		int quatre = compteDesQuatre(des);
		grilleCoup[4] = quatre*4;
		int cinq = compteDesCinq(des);
		grilleCoup[5] = cinq*5;
		int six = compteDesSix(des);
		grilleCoup[6] = six*6;
		
		// Evaluer les coups spéciaux
			//	Brelan et Carre
		int brelanOuCarre = evalueBrelanEtCarre(des);
		grilleCoup[Constantes.BRELAN] = brelanOuCarre;
		grilleCoup[Constantes.CARRE] = brelanOuCarre;
	}
	
	/*
	 *  Methode qui evalue l'occurence d'un BRELAN ET CARRE
	 */
	public static int evalueBrelanEtCarre(int des[]) {
		int brelanOuCarre = 0;
		
		int des2[] = {0,0,0,0,0,0};
		
		for(int i = 1; i < des.length; i++) {
			switch(des[i]) {
				case 1: des2[1]++; break;
				case 2: des2[2]++; break;
				case 3: des2[3]++; break;
				case 4: des2[4]++; break;
				case 5: des2[5]++; break;
				case 0: des2[0]++; break;
			}
		}
		
		// BRELAN
		for(int i = 0; i < des2.length; i++) {
			if (des2[i] == 3 && i!=0) {
				brelanOuCarre = i*3;
			} else if (des2[i] == 3 && i==0) {
				brelanOuCarre = 6*3;
			}
		}
		
		// CARRE
		for(int i = 0; i < des2.length; i++) {
			if (des2[i] == 4 && i!=0) {
				brelanOuCarre = i*4;
			} else if (des2[i] == 4 && i==0) {
				brelanOuCarre = 6*4;
			}
		}
		
		return brelanOuCarre;
	}


	/*
	 *	Methode qui compte l'occurences de UN
	 */
	public static int compteDesUns(int des[]) {
		int qteUn = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 1) {
				qteUn++;
			}
		}
		
		return qteUn;
	}
	
	/*
	 *	Methode qui compte l'occurences de DEUX
	 */
	public static int compteDesDeux(int des[]) {
		int qteDeux = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 2) {
				qteDeux++;
			}
		}
		
		return qteDeux;
	}
	
	/*
	 *	Methode qui compte l'occurences de TROIS
	 */
	public static int compteDesTrois(int des[]) {
		int qteTrois = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 3) {
				qteTrois++;
			}
		}
		
		return qteTrois;
	}
	
	/*
	 *	Methode qui compte l'occurences de QUATRE
	 */
	public static int compteDesQuatre(int des[]) {
		int qteQuatre = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 4) {
				qteQuatre++;
			}
		}
		
		return qteQuatre;
	}
	
	/*
	 *	Methode qui compte l'occurences de CINQ
	 */
	public static int compteDesCinq(int des[]) {
		int qteCinq = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 5) {
				qteCinq++;
			}
		}
		
		return qteCinq;
	}
	
	/*
	 *	Methode qui compte l'occurences de SIX
	 */
	public static int compteDesSix(int des[]) {
		int qteSix = 0;
		
		for(int i = 0; i < des.length; i++) {
			if (des[i] == 0) {
				qteSix++;
			}
		}
		
		return qteSix;
	}
	
	// Lance les des
	public static void lancerDes(int[] des, String desALancer){
		for(int i = 0; i < desALancer.length();i++){
			des[Integer.parseInt(desALancer.substring(i,i+1)) -1] = rand.nextInt(6);
		}
	}
	
	
}