package Composite_Pattern.with_composite;

// WITH COMPOSITE PATTERN
// Files and folders share one interface (FileSystemComponent).
// The client treats every node the same way - no instanceof checks,
// no duplicated logic. Adding a new operation means adding one method
// to the interface and implementing it in File and Folder.
public class Main {

    public static void main(String[] args) {
        // Build the same tree as the non-composite version
        Folder root = new Folder("root");
        Folder src = new Folder("src");
        Folder util = new Folder("util");
        Folder docs = new Folder("docs");

        src.add(new File("Main.java", 1200));
        util.add(new File("Helper.java", 800));
        src.add(util);
        docs.add(new File("README.md", 300));

        root.add(src);
        root.add(docs);

        // Same operations, but uniform - works on ANY component
        System.out.println("Total size: " + root.getSize() + " bytes");

        // You can even call these on a single file - no special casing
        File single = new File("notes.txt", 50);
        System.out.println("Single file size: " + single.getSize());
    }
}
