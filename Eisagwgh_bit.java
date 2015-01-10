import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class Eisagwgh_bit {
      
      private InputStream input;
    
      private int nextBits;
     
      private int numBitsRemaining;
      private boolean isEndOfStream;
      
      public Eisagwgh_bit(InputStream in) {
            if (in == null)
                  throw new NullPointerException("Null");
            input = in;
            numBitsRemaining = 0;
            isEndOfStream = false;
      }

      public int read() throws IOException {
            if (isEndOfStream)
                  return -1;
            if (numBitsRemaining == 0) {
                  nextBits = input.read();
                  if (nextBits == -1) {
                        isEndOfStream = true;
                        return -1;
                  }
                  numBitsRemaining = 8;
            }
            numBitsRemaining--;
            return (nextBits >>> numBitsRemaining) & 1;
      }

      public int readNoEof() throws IOException {
            int result = read();
            if (result != -1)
                  return result;
            else
                  throw new EOFException("Τέλος της σειράς διαβάσματος");
      }

      public void close() throws IOException {
            input.close();
      }
}
