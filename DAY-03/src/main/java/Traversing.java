import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Traversing {
    public static void traversing(){

        List<Integer> numbers =
                new ArrayList<>(Arrays.asList(10, 20, 30, 40));

        for (Integer num : numbers) {

            if (num == 20) {
                numbers.remove(num);
            }
        }

        System.out.println(numbers);
    }
}
