import java.util.ArrayList;
import java.util.List;

/*
*Ένα δυαδικό δέντρο του οποίου κάθε φύλλο κωδικοποιεί ένα σύμβολο για να  αναπαραστήσει τον κώδικα του Χόφμαν.
*Υπάρχουν 2 κύριες χρήσεις του δέντρου κωδικοποίησης :
*Nα διαβάσεις το δεξιά πεδίο και να προχωρήσεις στο δέντρο για να λάβεις την επιθυμητη πληροφορία
*Να καλέσεις την getCode() μέθοδο για να πάρεις τον κωδικό ενός συμβόλου, με την προυπόθεση ότι το σύμβολο έχει έναν κωδικό
* Το μονομάτι προς τον αριστερό κόμβο προσδιορίζει τον κώδικα του συμβόλου του φύλλου. Αρχίζοντας από την ρίζα και πηγαίνοντας προς το αριστερό παιδί συμβολίζεται με 0 και πηγαίνοντας προς το δεξιά παιδί συμβολίζεται με 1.
*Περιορισμοί:
*Το δέντρο πρέπει να είναι ολόκληρο, κάθε φύλλο να έχει ένα σύμβολο
*Κανένα σύμβολο δεν πρέπει να συναντάται σε 2 φύλλα
*Η ρίζα δεν πρέπει να είναι κόμβος ενός  φύλλου
* Παράδειγμα:
*Κωδικοί  Χόφμαν
 *     0: Σύμβολο  A
 *     10: Σύμβολο  B
 *     110: Σύμβολο C
 *     111: Σύμβολο D
 *   Code tree:
 *      Κωδικοποίηση δέντρου :
 *      / \
 *     A   .
 *        / \
 *       B   .
 *          / \
 *         C   D
 */

public final class Kodikos_dentroy {

                public final Esoterikos_komvos root;  // Όχι μηδέν

                // Αποθηκεύει τον κωδικό για κάθε σύμβολο ,ή μηδέν αν το σύμβολο δεν έχει κωδικό
                // Για παράδειγμα, εάν το σύμβολο 5 έχει κωδικό  10001,τότε codes.get(5) είναι η λίστα [1, 0, 0, 0, 1].
                 private List<List<Integer>> codes;

                // Κάθε σύμβολο  στο δέντρο πρέπει να είναι μικρότερο από την μεταβλητή 'symbolLimit'.
                public Kodikos_dentroy(Esoterikos_komvos root, int symbolLimit) {
                                if (root == null)
                                                throw new NullPointerException("Argument is null");
                                this.root = root;

                                codes = new ArrayList<List<Integer>>();
                                for (int i = 0; i < symbolLimit; i++)
                                                codes.add(null);
                                buildCodeList(root, new ArrayList<Integer>());
                }

                private void buildCodeList(Komvos komvos, List<Integer> prefix) {
                                if (komvos instanceof Esoterikos_komvos) {
                                               Esoterikos_komvos esoterikos_komvos = (Esoterikos_komvos)komvos;

                                                prefix.add(0);
                                                buildCodeList( esoterikos_komvos.leftChild , prefix);
                                                prefix.remove(prefix.size() - 1);

                                                prefix.add(1);
                                                buildCodeList( esoterikos_komvos.rightChild, prefix);
                                                prefix.remove(prefix.size() - 1);

                                } else if (komvos instanceof Fyllo) {
                                                Fyllo fyllo = (Fyllo)komvos ;
                                                if (fyllo.symbol >= codes.size())
                                                                throw new IllegalArgumentException("Symbol violates symbol limit");
                                                if (codes.get(fyllo.symbol) != null)
                                                                throw new IllegalArgumentException("Symbol has more than one code");
                                                codes.set(fyllo.symbol, new ArrayList<Integer>(prefix));

                                } else {
                                                throw new AssertionError("Illegal komvos type");
                                }
                }

                public List<Integer> getCode(int symbol) {
                                if (symbol < 0)
                                                throw new IllegalArgumentException("Illegal symbol");
                                else if (codes.get(symbol) == null)
                                                throw new IllegalArgumentException("No code for given symbol");
                                else
                                                return codes.get(symbol);
                }
}
