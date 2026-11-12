package OddEvenPrinter;

public class PrintRoundRobinWay implements Runnable {
    final SharedClass sharedClass;
    private int threadId;
    private int M;
    PrintRoundRobinWay(SharedClass sharedClass,int threadId,int M){
        this.sharedClass=sharedClass;
        this.threadId=threadId;
        this.M=M;
    }
    @Override
    public void run() {
        while (true){
            synchronized (sharedClass){
                if(sharedClass.getCounter()%M!=threadId && sharedClass.getCounter()<=sharedClass.getMAX()){
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
