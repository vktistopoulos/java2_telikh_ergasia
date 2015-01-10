import java.io.IOException;


public final class Apokodikopoiisi {

	private Eisagwgh_bit input;

	public Kodikos_dentroy  kodikos_dentroy ;

	public Apokodikopoiisi(Eisagwgh_bit in) {
			if (in == null)
				throw new NullPointerException("To epixeirima einai akiro");
			input = in;
	}

	public int read() throws IOException {
			if (kodikos_dentroy  == null)
				throw new NullPointerException("To code tree einai akiro");

			Esoterikos_komvos currentKomvos = kodikos_dentroy.root;
			while (true) {
				int temp = input.readNoEof();
				Komvos nextKomvos ;
				if      (temp == 0) nextKomvos = currentKomvos.leftChild;
				else if (temp == 1) nextKomvos = currentKomvos.rightChild;
				else throw new AssertionError();

				if (nextKomvos instanceof Fyllo)
					return ((Fyllo)nextKomvos).symbol;
				else if (nextKomvos instanceof Esoterikos_komvos)
					currentKomvos = (Esoterikos_komvos)nextKomvos;
				else
						throw new AssertionError();
			}
	}

}
