package Composite_Pattern.With;

// LEAF node - a file. Cannot have children.
public record File(String name, long size) implements FileSystemComponent {

}
