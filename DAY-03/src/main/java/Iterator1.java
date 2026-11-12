import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Iterator1 {
    public void callIterator(){
        List<Integer> numbers =
                new ArrayList<>(Arrays.asList(5, 10, 15, 20, 25));

        java.util.Iterator<Integer> itr = numbers.iterator();

            while (itr.hasNext()) {
            Integer num = itr.next();

            if (num > 10) {
                itr.remove();
            }
        }
            System.out.println(numbers);
    }
}
