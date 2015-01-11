import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// Αποσυμπιέζει ένα εισαγώμενο αρχείο ,το οποίο είχε συμπιεστεί με την κωδικοποίηση Χόφμαν, σε ένα εξαγώμενο αρχείο
public final class Aposympiesi {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Usage: java Aposympiesi InputFile OutputFile");
			System.exit(1);
			return;
		}

		//Αλλιώς αποσυμπίεσε
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);

		Eisagwgh_bit in = new Eisagwgh_bit(new BufferedInputStream(new FileInputStream(inputFile)));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
		try {
			Kanonikos_kodikas canonCode = readCode(in);
			Kodikos_dentroy codetree = canonCode.toCodeTree();
			 aposympiesi(codetree, in, out);
		} finally {
			out.close();
			in.close();
		}
	}

	static Kanonikos_kodikas readCode(Eisagwgh_bit in) throws IOException {
		int[] lengths = new int[257];
		for (int i = 0; i < lengths.length; i++) {
			int val = 0;
			for (int j = 0; j < 8; j++)
				val = val << 1 | in.readNoEof();
			lengths[i] = val ;
		}
		return new Kanonikos_kodikas(lengths);
	}
	

	static void aposympiesi(Kodikos_dentroy codetree, Eisagwgh_bit in, OutputStream out) throws IOException {
		Apokodikopoiisi ap = new Apokodikopoiisi(in);
		ap.kodikos_dentroy = codetree ;
		while (true) {
			int symbol = ap.read();
			if (symbol == 256)
				break;
		out.write(symbol);
		}
	}
}

