package OddEvenPrinter;

public class SharedClass {
    private int counter=0;
    private  final int MAX=20;

    public int getCounter() {
        return counter;
    }

    public int getMAX() {
        return MAX;
    }

    void print(){
        System.out.println(counter+"->"+Thread.currentThread().getName());
        counter++;
    }
}
