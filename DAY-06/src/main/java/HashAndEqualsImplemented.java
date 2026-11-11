import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashAndEqualsImplemented {
    public static void main(String[] args) {
        Set<EntryImplemented> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(new EntryImplemented("test"));
        }
        System.out.println(set.size());
    }
}
 class EntryImplemented {
    public String entry;
    public EntryImplemented(String entry) {
        this.entry = entry;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryImplemented entry1 = (EntryImplemented) o;
        return Objects.equals(entry, entry1.entry);
    }
    @Override
    public int hashCode() {
        return Objects.hash(entry);
    }
}