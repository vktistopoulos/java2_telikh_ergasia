import java.io.IOException;
import java.util.List;

     public final class Kodikopoiisi {
	private Eksagwgh_bit output;

	public Kodikos_dentroy kodikos_dentroy;
	// O kodikos dentroy borei na allaxei afou kathe simvolo kodikopoiithei,arkei i kadikopoisi kai i apokodikopoiisi na exoun ton idio kodiko dentroy tin idia stigmi;

	public Kodikopoiisi(Eksagwgh_bit out) {
			if (out == null)
				throw new NullPointerException("Null argument");
			output = out;
	}
	public void write(int symbol) throws IOException {
		if ( kodikos_dentroy == null)
			throw new NullPointerException("Null κωδικός");

		List<Integer> bits = kodikos_dentroy.getCode(symbol);
		for (int b : bits)
			output.write(b);
	}
}
