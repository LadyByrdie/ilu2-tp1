package histoire;
import personnages.Gaulois;
import villagegaulois.Etal;
public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		Etal etal = new Etal();
		etal.occuperEtal(assurancetourix, "patates", 2);
		etal.acheterProduit(2,null);
		System.out.println("Fin du test");
	}
}
