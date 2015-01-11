import java.io.IOException;
import java.io.OutputStream;
  public final class Eksagwgh_bit {
    private OutputStream output;
    private int currentByte;
    private int numBitsInCurrentByte;
    public Eksagwgh_bit(OutputStream out) {
      if (out == null)
      throw new NullPointerException("Null argument");
      output = out;
      currentByte = 0;
      numBitsInCurrentByte = 0;
    }
  // Writes a bit to the stream. The bit must be 0 or 1.
    public void write(int a) throws IOException {
      if (!(a == 0 || a == 1))
      throw new IllegalArgumentException("Only 0 or 1");
      currentByte = currentByte << 1 | a;
        numBitsInCurrentByte++; 
        if (numBitsInCurrentByte == 8) {
        output.write(currentByte);
        numBitsInCurrentByte = 0;
      }
    }
    public void close() throws IOException {
      while (numBitsInCurrentByte != 0)
      write(0);
      output.close();
  }
}
