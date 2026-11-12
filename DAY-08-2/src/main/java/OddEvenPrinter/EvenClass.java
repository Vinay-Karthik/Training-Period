package OddEvenPrinter;


public class EvenClass implements Runnable{

    final SharedClass sharedClass;
    EvenClass(SharedClass sharedClass){
        this.sharedClass=sharedClass;
    }
    @Override
    public void run() {
        while (true){
            synchronized (sharedClass){
                if(sharedClass.getCounter()%2!=0 && sharedClass.getCounter()<=sharedClass.getMAX()){
                    try {
                        sharedClass.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(sharedClass.getCounter()> sharedClass.getMAX()){
//                    sharedClass.notifyAll();
                    break;
                }
                sharedClass.print();
                sharedClass.notifyAll();
            }
        }
    }
}

