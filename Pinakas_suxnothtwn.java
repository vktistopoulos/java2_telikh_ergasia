import java.util.PriorityQueue;
import java.util.Queue;

/*Συλλογή των συχνοτήτων των συμβόλων που θέλουμε να συμπιέσουμε και χτίσιμο του κωδικού δέντρου για αυτό.*/

public final class Pinakas_suxnothtwn {

	private int[] frequencies;
	
	public Pinakas_suxnothtwn(int[] freqs) {
		if (freqs == null) {
			throw new NullPointerException("Null");
		}
		if (freqs.length < 2) {
			throw new IllegalArgumentException("Τουλάχιστον 2 σύμβολα χρειάζονται");
			
		}
		for (int x : frequencies) {
			if (x < 0) {
				throw new IllegalArgumentException("Αρνητική συχνότητα");
			}
		}
	}
	
	public int getSymbolLimit() {
		return frequencies.length;
	}

	public int get(int symbol) {
		if (symbol < 0 || symbol >= frequencies.length)
			throw new IllegalArgumentException("Σύμβολο εκτός εύρους");
		return frequencies[symbol];
	}

	public void set(int symbol, int freq) {
		if (symbol < 0 || symbol >= frequencies.length)
			throw new IllegalArgumentException("Σύμβολο εκτός εύρους");
		frequencies[symbol] = freq;
	}

	public void aukshsh(int symbol) {
		if (symbol < 0 || symbol >= frequencies.length)
			throw new IllegalArgumentException("Σύμβολο εκτός εύρους");
		if (frequencies[symbol] == Integer.MAX_VALUE)
			throw new RuntimeException("Αριθμητική υπερχείλιση");
		frequencies[symbol]++;
	}
	
	// Επιστροφή ενός κώδικα δέντρου για αυτές τις συχνότητες.
	public  Kodikos_dentroy  xtisimoDentrouKwdika() {
	
		Queue<NodeWithFrequency> pqueue = new PriorityQueue<NodeWithFrequency>();

		// Άθροισμα των φύλλων για τα σύμβολα με μη μηδενική συχνότητα.
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] > 0)
				pqueue.add(new NodeWithFrequency(new Fyllo(i), i, frequencies[i]));
		}

		// Δημιουργία τουλάχιστον 2 στοιχείων.
		for (int i = 0; i < frequencies.length && pqueue.size() < 2; i++) {
			if (i >= frequencies.length || frequencies[i] == 0) {
				pqueue.add(new NodeWithFrequency(new Fyllo(i), i, 0));
			}
		}
		if (pqueue.size() < 2) {
			throw new AssertionError();
		}

		// Επαναληπτική ένωση των στοιχείων με τις χαμηλότερες συχνότητες.
		while (pqueue.size() > 1) {
			NodeWithFrequency nf1 = pqueue.remove();
			NodeWithFrequency nf2 = pqueue.remove();
			pqueue.add(new NodeWithFrequency(
					new Esoterikos_komvos(nf1.komvos, nf2.komvos),
					Math.min(nf1.lowestSymbol, nf2.lowestSymbol),
					nf1.frequency + nf2.frequency));
		}

		// Επιστροφή του υπολοίπου.
		return new Kodikos_dentroy((Esoterikos_komvos)pqueue.remove().komvos, frequencies.length);
	}

	private static class NodeWithFrequency implements Comparable<NodeWithFrequency> {

		public final Komvos komvos;

		public final int lowestSymbol;

		public final long frequency;

		public NodeWithFrequency(Komvos komvos, int lowestSymbol, long freq) {
			this.komvos = komvos;
			this.lowestSymbol = lowestSymbol;
			this.frequency = freq;
		}

		public int compareTo(NodeWithFrequency other) {
			if (frequency < other.frequency) {
				return -1;
			} else if (frequency > other.frequency) {
				return 1;
			} else if (lowestSymbol < other.lowestSymbol) {
				return -1;
			} else if (lowestSymbol > other.lowestSymbol) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
