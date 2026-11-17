package Composite_Pattern.without_composite;

// A single file - leaf node. No children possible.
public class FileItem {
    private final String name;
    private final long size;

    public FileItem(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }
}
