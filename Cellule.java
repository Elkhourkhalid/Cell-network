import java.util.ArrayList;

// Classe pour représenter une cellule dans le réseau.
public class Cellule {
    private String type;
    private ArrayList<Integer> liens;
    private boolean courant;
    private boolean suivant;

    /**
     * Constructeur pour une cellule avec des liens spécifiés.
     * @param type Le type de la cellule.
     * @param liens Les indices des cellules liées.
     */
    public Cellule(String type, ArrayList<Integer> liens) {
        this.type = type;
        this.liens = liens;
        this.courant = false;
         // Initialisez courant à false
        this.suivant = false;
         // Initialisez suivant à false
    }
    /**
     * Constructeur pour une cellule avec des liens spécifiés par des indices.
     * @param type Le type de la cellule.
     * @param lien1 L'indice du premier lien.
     * @param lien2 L'indice du deuxième lien.
     */
    public Cellule(String type2, int lien1, int lien2) {
        this.type = type2;
        this.liens = new ArrayList<>();
        this.liens.add(lien1);
        this.liens.add(lien2);
        this.courant = false; 
        // Initialisez courant à false
        this.suivant = false; 
        // Initialisez suivant à false
    }

    /**
     * Obtient le type de la cellule.
     * @return Le type de la cellule.
     */
    public String getType() {
        return type;
    }

    /**
     * Obtient les liens de la cellule.
     * @return Les liens de la cellule.
     */
    public ArrayList<Integer> getLiens() {
        return liens;
    }

    /**
     * Obtient l'état courant de la cellule.
     * @return L'état courant de la cellule.
     */
    public boolean getCourant() {
        return courant;
    }

    /**
     * Définit l'état courant de la cellule.
     * @param courant Le nouvel état courant.
     */
    public void setCourant(boolean courant) {
        this.courant = courant;
    }

    /**
     * Obtient l'état suivant de la cellule.
     * @return L'état suivant de la cellule.
     */
    public boolean getSuivant() {
        return suivant;
    }

    /**
     * Définit l'état suivant de la cellule.
     * @param suivant Le nouvel état suivant.
     */
    public void setSuivant(boolean suivant) {
        this.suivant = suivant;
    }

    /**
     * Obtient les états de la cellule.
     * @return Liste des états de la cellule.
     */
    public ArrayList<Boolean> getEtats() {
        ArrayList<Boolean> etats = new ArrayList<>();
        etats.add(courant);
        return etats;
    }
}