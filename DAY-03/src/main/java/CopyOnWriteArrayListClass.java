import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListClass{
    public static void copyOnWriteArrayList(){
        CopyOnWriteArrayList<Integer> list =
                new CopyOnWriteArrayList<>();

        // when we use the ArrayList it throws the exception because it checks the modCount each time
        // CopyOnWriteArrayList doesnt check this way , it takes snapshot of the list at time of iteration and
        //traverses it.

        list.add(10);
        list.add(20);
        list.add(30);

        for (Integer num : list) {

            System.out.println(num);

            list.add(40);
        }

        System.out.println(list);
    }
}
