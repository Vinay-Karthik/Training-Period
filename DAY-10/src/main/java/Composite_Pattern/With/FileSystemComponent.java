package Composite_Pattern.With;

// Common interface for ALL nodes in the tree - files and folders alike.
// This is the "Component" in the Composite pattern.
public interface FileSystemComponent {
    String name();

    long size();            // files return their size; folders sum children
}
