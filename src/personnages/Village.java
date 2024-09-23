package personnages;

import java.util.Arrays;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois=0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}
	
	public void ajouterHabitant(Gaulois gaulois) {
			villageois[nbVillageois] = gaulois;
			nbVillageois ++;
	}
	 
	public void trouverHabitant(int numeroVillageois) {
		System.out.println(villageois[numeroVillageois]);
	}
	
	public void afficherVillageois() {
		System.out.println("Dans village du chef " + chef + " vivent les légendaires gaulois : \n " + villageois[1] + villageois[2]);
	}

	public static void main(String[] args) {
		Village village = new Village("Village des Irréductibles", 30);
		//Gaulois gaulois = village.trouverHabitant(30);
		// puisque le gaulois n'est pas "relier" à son village
		Chef abadourix = new Chef("Abadourix", 6, village);
		Gaulois asterix = new Gaulois("Astérix", 8);
		village.ajouterHabitant(asterix);
		//Gaulois gaulois = village.trouverHabitant(1);
		//System.out.println(gaulois);
		// la méthode trouver habitant n'a pas de return
		Gaulois obelix = new Gaulois("Obélix",25);
		village.ajouterHabitant(obelix);
		village.afficherVillageois();
	}
	
}
