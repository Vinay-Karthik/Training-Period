package Composite_Pattern.with_composite;

// Common interface for ALL nodes in the tree - files and folders alike.
// This is the "Component" in the Composite pattern.
public interface FileSystemComponent {
    String getName();

    long getSize();            // files return their size; folders sum children
}
