public final class Fyllo extends Komvos {
  
  public final int symbol;
  
  public Fyllo ( int symbol) {
    if (symbol < 0)
      throw new IllegalArgumentException("IllegalArgumentException");
      
      this.symbol = symbol;
  }
}

