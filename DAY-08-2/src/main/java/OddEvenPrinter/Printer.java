package OddEvenPrinter;

public class Printer  implements Runnable{
    final SharedClass sharedClass;
    private int threadId;
    Printer(SharedClass sharedClass,int threadId){
        this.sharedClass=sharedClass;
        this.threadId=threadId;
    }
    @Override
    public void run() {
        while (true){
            synchronized (sharedClass){
                if(sharedClass.getCounter()%2==threadId && sharedClass.getCounter()<=sharedClass.getMAX()){
                    try {
                        sharedClass.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(sharedClass.getCounter()> sharedClass.getMAX()){
                    sharedClass.notifyAll();
                    break;
                }
                sharedClass.print();
                sharedClass.notifyAll();
            }
        }
    }
}
