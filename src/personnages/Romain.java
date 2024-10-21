package personnages;

public class Romain {
	
	private static final String SOLDAT_STR = "Le soldat ";
	private String nom;
	private int force;
	private Equipement[] equipements = new Equipement[2];
	private int nbEquipement = 0;
	private boolean vainqueur = false;
	private String texte;

	public Romain(String nom, int force) {

		this.nom = nom;
		this.force = force;
		assert isInvariantSatisfied();
	}

	private boolean isInvariantSatisfied() {
		return force>=0;
	}

	public String getNom() {
		return nom;
	}
	
	public boolean isVainqueur() {
		return vainqueur;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "»");
	}

	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}

//	public void recevoirCoup(int forceCoup) {
//		int locvar = force;
//		force -= forceCoup;
//		assert isInvariantSatisfied();
//		if (force > 0) {
//			parler("Aïe");
//		} else {
//			parler("J'abandonne...");
//		}
//		assert forceCoup < locvar;
//  	}

	public Equipement[] recevoirCoup(int forceCoup) {
		Equipement[] equipementEjecte = null;
		// precondition
		int oldForce = force;
		forceCoup = calculResistanceEquipement(forceCoup);
		if (forceCoup <= 0) {
			vainqueur = true;
			parler("J'ai gagne.");
		} else {
			force -= forceCoup;
			assert isInvariantSatisfied();
			if (force > 0) {
				parler("Aie");
			} else {
				equipementEjecte = ejecterEquipement();
				parler("J'abandonne...");
			}
		}
		// post condition la force a diminuee
		assert force <= oldForce;
		return equipementEjecte;
	}

	private int calculResistanceEquipement(int forceCoup) {
		String texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
		int resistanceEquipement = 0;
		if (nbEquipement != 0) {
			texte += "\nMais heureusement, grace a mon equipement sa force est diminue de ";
			for (int i = 0; i < nbEquipement; i++) {
				if (equipements[i] != null && equipements[i].equals(Equipement.BOUCLIER)) {
					resistanceEquipement += 8;
				} else {
					if (equipements[i] != null && equipements[i].equals(Equipement.CASQUE)) {
						System.out.println("Equipement casque");
						resistanceEquipement += 5;
					}
				}
			}
			texte += resistanceEquipement + "!";
		}
		parler(texte);
		forceCoup -= resistanceEquipement;
		return forceCoup;
	}

	private Equipement[] ejecterEquipement() {
		Equipement[] equipementEjecte = new Equipement[nbEquipement];
		System.out.println("L'equipement de " + nom + " s'envole sous la force du coup.");
		int nbEquipementEjecte = 0;
		for (int i = 0; i < nbEquipement; i++) {
			if (equipements[i] != null) {
				equipementEjecte[nbEquipementEjecte] = equipements[i];
				nbEquipementEjecte++;
				equipements[i] = null;
			}
		}
		return equipementEjecte;
	}


	public void sEquiper(Equipement equipement) {
		switch (nbEquipement) {
		case 0:
			equiperObjet(equipement);
			break;
		case 1:
			if (equipements[0] != null && equipement.equals(equipements[0])) {
				System.out.println(SOLDAT_STR + getNom() + " possede deja un " + equipement + " !");
			} else {
				equiperObjet(equipement);
			}
			break;
		case 2:
			System.out.println(SOLDAT_STR + getNom() + " est deja bien protege !");
			break;
		default:
			break;
		}
	}

	private void ajouterEquipement(Equipement equipement) {
		equipements[nbEquipement] = equipement;
		nbEquipement++;
		System.out.println("Le soldat " + getNom() + " s'equipe avec un " + equipement + ".");
	}
	
	private void equiperObjet(Equipement equipement) {
		System.out.println(SOLDAT_STR + getNom() + " sequipe avec un " + equipement + ".");
		equipements[nbEquipement] = equipement;
		nbEquipement++;
	}

	public static void main(String[] args) {
		Romain minus = new Romain("Minus", 6);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.BOUCLIER);
		minus.sEquiper(Equipement.BOUCLIER);
	}
}
