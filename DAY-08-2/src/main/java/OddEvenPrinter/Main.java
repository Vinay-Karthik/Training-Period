package OddEvenPrinter;

public class Main {
    public static void main(String[] args) {
        SharedClass sharedClass=new SharedClass();

//        Even and Odd
//        Thread thread1=new Thread(new OddClass(sharedClass),"OddClass");
//        Thread thread2=new Thread(new EvenClass(sharedClass),"EvenClass");
//
//        thread1.start();
//        thread2.start();

//        Printer class
//        Thread thread1=new Thread(new Printer(sharedClass,0),"OddClass");
//        Thread thread2=new Thread(new Printer(sharedClass,1),"EvenClass");
//
//        thread1.start();
//        thread2.start();

//        printerRoundRobin
            int M=3;
        Thread thread1=new Thread(new PrintRoundRobinWay(sharedClass,0,M),"OddClass");
        Thread thread2=new Thread(new PrintRoundRobinWay(sharedClass,1,M),"EvenClass");

        thread1.start();
        thread2.start();

    }
}
