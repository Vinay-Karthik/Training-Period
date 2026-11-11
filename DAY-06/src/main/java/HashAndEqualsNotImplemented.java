import java.util.HashSet;
import java.util.Set;

public class HashAndEqualsNotImplemented {
    public static void main(String[] args) {
        Set<Entry> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(new Entry("test"));
        }
        System.out.println(set.size());
    }
}
class Entry {
    public String entry;
    public Entry(String entry) {
        this.entry = entry;
    }
}