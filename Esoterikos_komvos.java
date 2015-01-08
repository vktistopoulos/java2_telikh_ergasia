public final class Esoterikos_komvos extends Komvos {
public final  Komvos leftChild;
public final  Komvos rightChild;

public Esoterikos_komvos( Komvos leftChild,  Komvos rightChild) {
if (leftChild == null || rightChild == null)
throw new NullPointerException("NullPointerException!!");
this.leftChild = leftChild;
this.rightChild = rightChild;
}
}
