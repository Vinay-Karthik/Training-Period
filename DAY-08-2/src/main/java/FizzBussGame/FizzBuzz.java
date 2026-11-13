package FizzBussGame;

public class FizzBuzz {
    int counter=0;
    int n=10;
     public void method1() throws InterruptedException {
         while (true){
             synchronized (this){
                 while(counter<=n && !(counter%3==0)){
                     wait();
                     if(counter>n){
                         notifyAll();
                         break;
                     }
                     System.out.println();
                 }
             }
         }
     }

}
