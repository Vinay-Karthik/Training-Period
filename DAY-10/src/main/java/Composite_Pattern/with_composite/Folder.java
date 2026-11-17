package Composite_Pattern.with_composite;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public long getSize() {
        long total = 0;
        for (FileSystemComponent child : children) {
            total += child.getSize();
        }
        return total;
    }
}
