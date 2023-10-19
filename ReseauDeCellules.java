import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Classe représentant un réseau de cellules.

public class ReseauDeCellules {

    /**
     * Exécute la simulation du réseau.
     * @param nomFichierReseau Le nom du fichier décrivant le réseau.
     * @param nomFichierValeur Le nom du fichier contenant les valeurs.
     * @return Une liste des états à chaque étape.
     */
    public static ArrayList<ArrayList<Boolean>> executer(String nomFichierReseau, String nomFichierValeur) {
        ArrayList<Cellule> reseau = lireReseau(nomFichierReseau);
        ArrayList<Boolean> valeurs = lireValeurs(nomFichierValeur);
        

        // Initialiser la liste des états à chaque étape
        ArrayList<ArrayList<Boolean>> etatsParEtape = new ArrayList<>();

        // Ajouter les états initiaux à la liste
        etatsParEtape.add(recupererEtatsCourants(reseau));
        

        // Simuler l'évolution du réseau en fonction des valeurs lues.
        for (Boolean valeur : valeurs) {
            // Évolution de la cellule zéro
            Cellule celluleZero = reseau.get(0);
            celluleZero.setSuivant(valeur);

            // Évolution des autres cellules
            for (int j = 1; j < reseau.size(); j++) {
                Cellule celluleCourante = reseau.get(j);
                int lien1 = reseau.get(j).getLiens().get(0);
                int lien2 = reseau.get(j).getLiens().get(1);
                boolean e1 = reseau.get(lien1).getCourant();
                boolean e2 = reseau.get(lien2).getCourant();
                boolean resultat = evaluerPredicat(celluleCourante.getType(), e1, e2);
                celluleCourante.setSuivant(resultat);
            }

            // Terminer l'évolution en copiant les valeurs suivantes dans les valeurs courantes
            for (Cellule cellule : reseau) {
                cellule.setCourant(cellule.getSuivant());
            }

            // Ajouter les états de cette étape à la liste
            etatsParEtape.add(recupererEtatsCourants(reseau));
        }

        return etatsParEtape;
    }
  
    /**
    * Lit le fichier de description du réseau et crée une liste de cellules correspondante.
    * @param nomFichierReseau Le nom du fichier décrivant le réseau.
    * @return Une liste de cellules représentant le réseau.
    */
    private static ArrayList<Cellule> lireReseau(String nomFichierReseau) {
        ArrayList<Cellule> reseau = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(nomFichierReseau));

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] elements = ligne.split(" ");

                if (elements.length == 3) {
                    String type = elements[0];
                    int lien1 = Integer.parseInt(elements[1]);
                    int lien2 = Integer.parseInt(elements[2]);

                    Cellule cellule = new Cellule(type, lien1, lien2);
                    reseau.add(cellule);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return reseau;
    }

    /**
    * Lit le fichier des valeurs d'entrée et crée une liste de valeurs booléennes.
    * @param nomFichierValeur Le nom du fichier contenant les valeurs d'entrée.
    * @return Une liste de valeurs booléennes.
    */
    private static ArrayList<Boolean> lireValeurs(String nomFichierValeur) {
        ArrayList<Boolean> valeurs = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(nomFichierValeur));

            while (scanner.hasNext()) {
                int valeur = scanner.nextInt();
                boolean valeurBool = (valeur == 1);
                valeurs.add(valeurBool);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return valeurs;
    }
    /**
    * Récupère les états courants de toutes les cellules dans le réseau.
    * @param reseau La liste des cellules représentant le réseau.
    * @return Une liste des états courants de chaque cellule.
    */
    private static ArrayList<Boolean> recupererEtatsCourants(ArrayList<Cellule> reseau) {
        ArrayList<Boolean> etatsCourants = new ArrayList<>();
        for (Cellule cellule : reseau) {
            etatsCourants.add(cellule.getCourant());
        }
        return etatsCourants;
    }

    /**
    * Évalue un prédicat logique en fonction du type de cellule et des valeurs de ses liens.
    * @param type Le type de la cellule (E, N, D, I, V, C, F).
    * @param e1 La valeur du premier lien.
    * @param e2 La valeur du deuxième lien.
    * @return Le résultat de l'évaluation du prédicat.
    */
    private static boolean evaluerPredicat(String type, boolean e1, boolean e2) {
        switch (type) {
            case "E":
                return (e1 == e2);
            case "N":
                return !(e1);
            case "D":
                return (e1 || e2);
            case "I":
                return e1;
            case "V":
                return true;
            case "C":
                return (e1 && e2);
            case "F":
                return false;
            default:
                return false;
        }
    }
}
