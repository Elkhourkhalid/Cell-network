import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Principal {
    private enum Test {
       TestExemple( "rExemple.txt", "eExemple.txt", Arrays.asList( true, true, false, true, false, true, true, true ) ),
       TestVideVrai( "rVide.txt", "eUnVrai.txt" , Arrays.asList( true )),
       TestVideFaux( "rVide.txt", "eUnFaux.txt" , Arrays.asList( false )),
       TestVidePlusieurs( "rVide.txt", "ePlusieurs.txt" , Arrays.asList( false, true, false, false, true, true, false )),

        TestConjonctionVrai( "rConjonctionVrai.txt", "eVF.txt", Arrays.asList( false, true, false )),
        TestConjonctionFaux( "rConjonctionFaux.txt", "eVF.txt", Arrays.asList( false, false, false )),

        TestDisjonctionVrai( "rDisjonctionVrai.txt", "eVF.txt", Arrays.asList( false, true, true )),
        TestDisjonctionFaux( "rDisjonctionFaux.txt", "eVF.txt", Arrays.asList( false, true, false )),

        TestEquivalenceVrai( "rEquivalenceVrai.txt", "eVF.txt", Arrays.asList( true, true, false )),
        TestEquivalenceFaux( "rEquivalenceFaux.txt", "eVF.txt", Arrays.asList( true, false, true )),

        TestNegationVrai( "rNegationVrai.txt", "eVF.txt", Arrays.asList( true, false, true )),
        TestNegationFaux( "rNegationFaux.txt", "eVF.txt", Arrays.asList( true, false, true )),

        TestIdentiteVrai( "rIdentiteVrai.txt", "eVF.txt", Arrays.asList( false, true, false )),
        TestIdentiteFaux( "rIdentiteFaux.txt", "eVF.txt", Arrays.asList( false, true, false )),

        TestTautologieVrai( "rTautologieVrai.txt", "eVF.txt", Arrays.asList( true, true, true )),
        TestTautologieFaux( "rTautologieFaux.txt", "eVF.txt", Arrays.asList( true, true, true )),

        TestContradictionVrai( "rContradictionVrai.txt", "eVF.txt", Arrays.asList( false, false, false )),
        TestContradictionFaux( "rContradictionFaux.txt", "eVF.txt", Arrays.asList( false, false, false )),

        ;
        private String nomFichierDescription;
        private String nomFichierEntrees;
        private List<Boolean> resultatAttendu;

        Test(String nomFichierDescription, String nomFichierEntrees, List<Boolean> resultatAttendu) {
            this.nomFichierDescription = nomFichierDescription;
            this.nomFichierEntrees = nomFichierEntrees;
            this.resultatAttendu = resultatAttendu;
        }

        public boolean tester() {
            ArrayList<ArrayList<Boolean>> resultat = ReseauDeCellules.executer(nomFichierDescription, nomFichierEntrees);
            // Collecter les états de chaque cellule à chaque étape dans une liste
            ArrayList<Boolean> etatsListe = new ArrayList<>();
            for (int i = 1; i < resultat.size(); i++) {
                etatsListe.add(resultat.get(i).get(resultat.get(i).size() - 1));
            }
            boolean estCorrect = etatsListe.equals(resultatAttendu);
            if (!estCorrect) {
                System.err.println("Le resultat du " + name() + " est incorrect.\n" +
                        "    Obtenue : " + resultat + "\n" +
                        "    Attendu : " + resultatAttendu + "\n");
            }
            return estCorrect;
        }
        

        public static int testerTous() {
            int nbCorrect = 0;

            for( Test test : Test.class.getEnumConstants() ) {
                if( test.tester() ) {
                    ++ nbCorrect;
                }
            }

            return nbCorrect;
        }
    }

    public static void main( String [] argv ) {
        int nbReussi = Test.testerTous();
        int nbTests = Test.class.getEnumConstants().length;

        System.out.println( "\nTotal : " + nbReussi + " / " + nbTests );
    }
}