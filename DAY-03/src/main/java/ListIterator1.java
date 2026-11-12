import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ListIterator1 {
    public void callListIterator(){
        List<String> names =
                new ArrayList<>(Arrays.asList("A", "B", "C"));

        ListIterator<String> itr = names.listIterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        while (itr.hasPrevious()) {
            System.out.println(itr.previous());
        }
    }
}
