import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Χρησιμοποιεί κωδικοποίηση Hoffman για να συμπιέσει ενα εισαγόμενο αρχείο σε ένα εξαγόμενο αρχείο.

public final class Sympiesi {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Usage: java  Compress InputFile OutputFile");
			System.exit(1);
			return;
		}

		// Αλλιώς κάνε την συμπίεση
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);

		//Διάβασε μια φορα το εισαγόμενο αρχείο προκειμένου να υπολογίσεις τις συχνότητες των συμβόλων.
		Pinakas_suxnothtwn freq = getFrequencies(inputFile);
		freq.aukshsh(256);
		Kodikos_dentroy  code = freq.xtisimoDentrouKwdika ();
		Kanonikos_kodikas canonCode = new Kanonikos_kodikas(code, 257);
		code = canonCode.toCodeTree();

		// Διάβασε ξανά το εισαγόμενο αρχείο,συμπίεσε με την κωδικοποίηση του Χόφμαν και γράψε το εξαγόμενο αρχείο.

		InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
		Eksagwgh_bit out = new Eksagwgh_bit(new BufferedOutputStream(new FileOutputStream(outputFile)));
		try {
			writeCode(out, canonCode);
			compress(code, in, out);
		} finally {
			out.close();
			in.close();
		}
	}

	private static Pinakas_suxnothtwn getFrequencies(File file) throws IOException {
		Pinakas_suxnothtwn freq = new Pinakas_suxnothtwn(new int[257]);
		InputStream input = new BufferedInputStream(new FileInputStream(file));
		try {
			while (true) {
				int b = input.read();
				if (b == -1)
					break;
				freq.aukshsh(b);
			}
		} finally {
			input.close();
		}
		return freq;
	}

	static void writeCode( Eksagwgh_bit out, Kanonikos_kodikas canonCode) throws IOException {

		for (int i = 0; i < canonCode.getSymbolLimit(); i++) {
			int  e = canonCode.getCodeLength(i);

			if (e >= 256)
				throw new RuntimeException("The code for a symbol is too long");


			for (int j = 7; j >= 0; j--)
				out.write((e >>> j) & 1);
		}
	}
