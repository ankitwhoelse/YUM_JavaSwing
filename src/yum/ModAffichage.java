package yum;

/*
 * Regroupe toutes les proc?dures d'affichage du jeu de YUM.
 * 
 * L'affichage est en mode console.  
 * 
 * Le module connait le jeu, il sert ? afficher la feuille de pointage et les d?s
 * 
 * L'affichage des coups possibles doit ?tre ?crit dans le m?me fichier que
 * votre main().  
 * 
 * Ne touchez pas ? ce module.
 * 
 * Auteur : Pierre B?lisle
 * Version : Copyright A2021
 */
public class ModAffichage {
	
	/*
	 * Affiche toutes les cases diff?rentes de 0 de la grille re?ue avec
	 * son nom et sa valeur.
	 */
	public static void afficherGrillePossibilite(int grille[]){

	    int i;

	    System.out.printf ("\n----------------------");
	    System.out.printf ("\n    Choix possibles");
	    System.out.printf ("\n-----------------------\n");

		for (i = 1; i < grille.length; i++){

	        if (grille[i] != 0){

	            switch (i){
	            
	            case 1:
	            case 2:
	            case 3:
	            case 4:
	            case 5:
	            case 6:
	                System.out.printf("|     %d       |",i);
	                break;
	            case Constantes.BRELAN:
	                System.out.printf("|Brelan       |" );
	                break;
	            case Constantes.CARRE:
	                System.out.printf("|Carre        |");
	                break;
	            case Constantes.MAIN_PLEINE :
	                System.out.printf("|Main pleine  |");
	                break;
	            case Constantes.PETITE_SUITE:
	                System.out.printf("|Petite suite |");
	                break;
	            case Constantes.GROSSE_SUITE:
	                System.out.printf("|Grosse suite |");
	                break;
	            case Constantes.SURPLUS:
	                System.out.printf("|Surplus      |");
	                break;
	            case Constantes.YUM:
	                System.out.printf("|YUM          |");
	                break;
	            }


	            System.out.printf("  %3d    |",grille[i]);
	            System.out.printf ("\n-----------------------\n");
			}
	    }
	}


	/*
     * Affiche la grille de pointage du YUM
     *
     * grille : Un tableau de NB_CASES ? afficher.

	*****************************************/
	public static void afficherGrille(int grille[]){
		
		/*
		 * Des appels sont ?crits sur 2 lignes pour respecter les 80 colonnes.
		 * 
		 * Un seul sous-programme utilis? qui affiche une ligne en 2 colonnes.
		 */
	    System.out.printf("\n                   GRILLE DE POINTAGE");
	    
	    System.out.printf("\n--------------------------" + 
	                     "-----------------------------");

	    afficherLigne("\n|     1       |","         |Brelan       |",
	    		                           grille[1],grille[Constantes.BRELAN]);
	    
	    afficherLigne("|     2       |","         |Carre        |",grille[2],
	    		                                      grille[Constantes.CARRE]);
	    
	    afficherLigne("|     3       |","         |Main pleine  |",grille[3],
	    		                                grille[Constantes.MAIN_PLEINE]);
	    
	    afficherLigne("|     4       |","         |Petite suite |",grille[4],
	    		                               grille[Constantes.PETITE_SUITE]);
	    
	    afficherLigne("|     5       |","         |Grosse suite |",grille[5],
	    		                               grille[Constantes.GROSSE_SUITE]);
	    
	    afficherLigne("|     6       |","         |Surplus      |",grille[6],
	    		                                    grille[Constantes.SURPLUS]);
	    
	    afficherLigne("|Sous total   |","         |YUM          |",
	    		     grille[Constantes.SOUS_TOTAL_HAUT],grille[Constantes.YUM]);
	    
	    afficherLigne("|Bonus        |","         |Total bas    |",
	    		 grille[Constantes.BONUS_DU_HAUT],grille[Constantes.TOTAL_BAS]);
	    
	    afficherLigne(" total haut   |","         |Total        |",
	    		  grille[Constantes.TOTAL_HAUT],grille[Constantes.GRAND_TOTAL]);
	}
	
	
	/*
	 * Les titres et les valeurs sur 2 colonnes.
	 */
	public static void afficherLigne(String titre1,
			                          String titre2,
			                          int val1,
			                          int val2){

	    afficherColonne(titre1, val1);
	    afficherColonne(titre2, val2);

	    System.out.printf ("\n----------------------     " + 
	                       "    ------------------------\n");

	}

	/*
	 * Affiche une colonne (titre et valeur)
	 */
	public static void afficherColonne(String titre, int val) {
		
		System.out.printf(titre);

	    if(val != -1){
	        System.out.printf(" %3d   |",val);
	    }
	    else {
	        System.out.printf("       |");
	    }
		
	}
	
	/*
	* Affiche graphiquement un d?s repr?sentant la valeur recue.
	*
	* des : un tableau de NB_DES chiffres entre 1 et NB_FACES 
	*                     repr?sentant la valeur des d?s ? afficher.
	*                     	
	*Algorithme original : Karen DC (f?vrier 2005)
	*Adaptation     : Pierre B?lisle
	*/
	public static void afficherDes(int des[]){
		
		/*
		 * Strat?gie : On affiche une ligne ? la fois sachant que 
		 * les possibilit?s sur un d? NB_FACES (hard coded).
		 * 
		 * Si la constante change, il faut venir ajouter des lignes et changer
		 * le d?tails des prochaines proc?dures d'affichage.
		 */
	    afficherPremiereLigne();
	    System.out.printf("\n");
	    afficherDeuxiemeLigne(des);
	    System.out.printf("\n");
	    afficherTroisiemeLigne(des);
	    System.out.printf("\n");
	    afficherQuatriemeLigne(des);
	    System.out.printf("\n");
	}
	
	
	public static void afficherPremiereLigne(){
	    
	    System.out.printf(" _____  _____  _____  _____  _____ ");
	     
	}
	

	
	public static void afficherDeuxiemeLigne(int des[]){
			   
	   for(int i = 0; i < Constantes.NB_DES; i ++) {

		   //deuxi?me ligne
		   if(des[i] == 1){
			   System.out.printf("|     |");
		   }
		   else if(des[i] == 2 || des[i] == 3){
			   System.out.printf("|o    |");
		   }
		   else{
			   System.out.printf("|o   o|");
		   } 
	   }
	}

	public static void afficherTroisiemeLigne(int des[]){

		for(int i = 0; i < Constantes.NB_DES; i ++){

			if(des[i] == 2 || des[i] == 4){
				System.out.printf("|     |");
			}
			else if(des[i] == 1 || des[i] == 3 || des[i] == 5){
				System.out.printf("|  o  |");
			}
			else{
				System.out.printf("|o   o|");
			}
		}
	}

	public static void afficherQuatriemeLigne(int des[]){

	   for(int i = 0; i < Constantes.NB_DES; i ++){

		   if(des[i] == 1){
			   System.out.printf("|_____|");
		   }
		   else if(des[i]== 3 ||  des[i] == 2){
			   System.out.printf("|____o|");
		   }
		   else {
			   System.out.printf("|o___o|");	 
		   }

	   }
	}

	/*
	 * Affiche l'?tat d'arr?t d'une partie.
	 */
	public static void afficherEtatAnnulation(boolean annule){

		if(annule){
        	System.out.print("\n\nPartie annul?e");
        }
        else {
        	System.out.print("\n\nLa partie est terminee");
        }
	}

}
