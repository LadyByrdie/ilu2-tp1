package villagegaulois;

import ExceptionsPersonnalises.VillageSansChefException;
import personnages.Chef;
import personnages.Gaulois;

public class Village {
    private String nom;
    private Chef chef;
    private Gaulois[] villageois;
    private int nbVillageois = 0;
    public Village(String nom, int nbVillageoisMaximum) {
	this.nom = nom;
	villageois = new Gaulois[nbVillageoisMaximum];
    }

    private class Marche {
	private Etal[] etals;
	private int nbetal;

	private Marche(int nbetal) {
	    super();
	    this.nbetal = nbetal;
	    this.etals = new Etal[nbetal];
	}

	private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
	    etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	}

	private int trouverEtalLibre() {
	    int EtalLibre = -1;
	    for (int i = 0; i < nbetal && EtalLibre == -1; i++) {
		if (!etals[i].isEtalOccupe()) {
		    EtalLibre = i;
		}
	    }
	    return EtalLibre;
	}

	private Etal[] trouverEtals(String produit) {
	    Etal[] etalProduit;
	    int nbEtalPro = 0;
	    for (int i = 0; i < nbetal; i++) {
		if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
		    nbEtalPro++;
		}
	    }

	    etalProduit = new Etal[nbEtalPro];
	    for (int i = 0; i < nbEtalPro; i++) {
		if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
		    etalProduit[i] = etals[i];
		}
	    }

	    return etalProduit;

	}

	private Etal trouverVendeur(Gaulois gaulois) {
	    Etal etalVendeur = null;
	    for (int i = 0; (i < nbetal) && (etalVendeur == null); i++) {
		if ((gaulois == etals[i].getVendeur())) {
		    etalVendeur = etals[i];
		}
	    }
	    return etalVendeur;
	}

	private String afficherMarche() {
	    int nbEtalVide = 0;
	    for (int i = 0; i < nbetal; i++) {
		if (etals[i].isEtalOccupe()) {
		    etals[i].afficherEtal();
		} else {
		    nbEtalVide++;
		}
	    }
	    if (nbEtalVide != 0) {
		return "Il reste " + nbEtalVide + " �tals non utilis�s dans le march�.\n";
	    }
	    return "\n";
	}
    }

    public String getNom() {
	return nom;
    }

    public void setChef(Chef chef) {
	this.chef = chef;
    }

    public void ajouterHabitant(Gaulois gaulois) {
	if (nbVillageois < villageois.length) {
	    villageois[nbVillageois] = gaulois;
	    nbVillageois++;
	}
    }

    public Gaulois trouverHabitant(String nomGaulois) {
	if (nomGaulois.equals(chef.getNom())) {
	    return chef;
	}
	for (int i = 0; i < nbVillageois; i++) {
	    Gaulois gaulois = villageois[i];
	    if (gaulois.getNom().equals(nomGaulois)) {
		return gaulois;
	    }
	}
	return null;
    }

    public String afficherVillageois() throws VillageSansChefException{
	StringBuilder chaine = new StringBuilder();
	if(this.chef==null) {
    	throw new VillageSansChefException("le village réclame un chef");
    }
	try {
	if (nbVillageois < 1) {
	    chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
	} else {
	    chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
	    for (int i = 0; i < nbVillageois; i++) {
		chaine.append("- " + villageois[i].getNom() + "\n");
	    }
	}
	return chaine.toString();
	}catch(VillageSansChefException e) {
		System.out.println("Veuillez s'il vous plait inserer un chef");
	}
	return null;
    }
}