import java.util.ArrayList;

//Classe principale pour exécuter la simulation du réseau de cellules.

public class App {
    public static void main(String[] args) {
        String nomFichierReseau = "rExemple.txt";
        String nomFichierValeur = "eExemple.txt";

        // Exécuter la simulation du réseau
        ArrayList<ArrayList<Boolean>> etatsParEtape = ReseauDeCellules.executer(nomFichierReseau, nomFichierValeur);

        // Afficher les états de chaque cellule à chaque étape
        for (int i = 0; i < etatsParEtape.size(); i++) {
            System.out.println("États à l'étape " + i + ": " + etatsParEtape.get(i));
        }

        // Collecter les états de chaque cellule à chaque étape dans une liste
        ArrayList<Boolean> etatsListe = new ArrayList<>();
        for (int i = 1; i < etatsParEtape.size(); i++) {
            etatsListe.add(etatsParEtape.get(i).get(etatsParEtape.get(i).size() - 1));
        }

        // Afficher la liste des états
        System.out.println("");
          System.out.println( "");
        System.out.println("____________________Resultat Final__________________");
        System.out.println("La valeur calculée par la dernière cellule du réseau est :  " + etatsListe);
      
    }
}
