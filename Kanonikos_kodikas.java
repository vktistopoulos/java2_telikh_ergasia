import java.util.ArrayList;
import java.util.List;
public final class Kanonikos_kodikas {
private int[] codeLengths ;
public Kanonikos_kodikas(int[] codeLengths) {
if (codeLengths == null)
throw new NullPointerException("NullPointerException");
this.codeLengths = codeLengths.clone();
for (int x : codeLengths) {
if (x < 0)
throw new IllegalArgumentException("Illegal code length");
}
}
public Kanonikos_kodikas(Kodikos_dentroy codetree, int symbolLimit ) {
codeLengths = new int[symbolLimit];
buildCodeLengths(codetree.root, 0);
}
private void buildCodeLengths(Komvos komvos, int vathos) {
if (komvos instanceof Esoterikos_komvos) {
Esoterikos_komvos esoterikos_komvos = (Esoterikos_komvos)komvos ;
buildCodeLengths(esoterikos_komvos.leftChild , vathos + 1);
buildCodeLengths(esoterikos_komvos.rightChild, vathos + 1);
}else if (komvos instanceof Fyllo) {
int symbol = ((Fyllo)komvos).symbol;
if (codeLengths[symbol] != 0)
throw new AssertionError("Error");
if (symbol >= codeLengths.length)
throw new IllegalArgumentException("Error");
codeLengths[symbol] = vathos;
}else {
throw new AssertionError("We cant accept this type");
}
}
public int getSymbolLimit() {
return codeLengths.length;
}
public int getCodeLength(int symbol) {
if (symbol < 0 || symbol >= codeLengths.length)
throw new IllegalArgumentException("Symbol out of range");
return codeLengths[symbol];
}
public Kodikos_dentroy toCodeTree() {
List<Komvos> komvoi = new ArrayList<Komvos>();
for (int u = max(codeLengths); u>= 1 ; u--) {
List<Komvos> newKomvoi = new ArrayList<Komvos>();
for (int j = 0; j < codeLengths.length; j++) {
if (codeLengths[j] == u )
newKomvoi.add(new Fyllo(j));
}
for (int j = 0; j < komvoi.size(); j += 2)
newKomvoi.add(new Esoterikos_komvos(komvoi.get(j), komvoi.get(j + 1)));
komvoi = newKomvoi ;
if (komvoi.size() % 2 != 0)
throw new IllegalStateException("IllegalStateException");
}
if (komvoi.size() != 2)
throw new IllegalStateException("IllegalStateException");
return new Kodikos_dentroy(new Esoterikos_komvos(komvoi.get(0),komvoi.get(1)), codeLengths.length);
}
private static int max(int[] array) {
int apotelesma = array[0];
for (int x : array)
apotelesma = Math.max(x, apotelesma);
return apotelesma;
}
}
