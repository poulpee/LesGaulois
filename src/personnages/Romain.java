package personnages;

public class Romain {
	private String nom;
	private int force;
	private Equipement[] equipements = new Equipement[2];
	private int nbEquipement = 0;

	public Romain(String nom, int force) {
		if (force < 0) {
			throw new IllegalArgumentException("Invalid force: " + force);
		}
		this.nom = nom;
		this.force = force;
	}

	public String getNom() {
		return nom;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "»");
	}

	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}

	public void recevoirCoup(int forceCoup) {
		force -= forceCoup;
		if (force > 0) {
			parler("Aïe");
		} else {
			parler("J'abandonne...");
		}
	}

	public void sEquiper(Equipement equipement) {
		switch (nbEquipement) {
		case 0:
			equipements[0] = equipement;
			nbEquipement++;
			System.out.println("Le soldat " + getNom() + " s'equipe avec un " + equipement + ".");
			break;
		case 1:
			if (equipements[0] != null && equipement.equals(equipements[0])) {
				System.out.println("Le soldat " + getNom() + " possede deja un " + equipement + " !");
			} else {
				System.out.println("Le soldat " + getNom() + " s'equipe avec un " + equipement + ".");
				equipements[1] = equipement;
				nbEquipement++;
			}
			break;
		case 2:
			System.out.println("Le soldat " + getNom() + " est deja bien protege !");
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		Romain minus = new Romain("Minus", 6);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.BOUCLIER);
		minus.sEquiper(Equipement.BOUCLIER);
	}
}
