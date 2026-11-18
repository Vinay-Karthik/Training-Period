package Composite_Pattern.Without;

public class Main {

    public static void main(String[] args) {
        FolderItem root = new FolderItem("root");
        FolderItem src = new FolderItem("src");
        FolderItem util = new FolderItem("util");
        FolderItem docs = new FolderItem("docs");

        src.addFile(new FileItem("Main.java", 1200));
        util.addFile(new FileItem("Helper.java", 800));
        src.addFolder(util);
        docs.addFile(new FileItem("README.md", 300));

        root.addFolder(src);
        root.addFolder(docs);

        System.out.println("Total size: " + totalSize(root) + " bytes");
    }

    private static long totalSize(FolderItem folder) {
        long size = 0;
        for (FileItem f : folder.getFiles()) {
            size += f.size();
        }
        for (FolderItem sub : folder.getSubFolders()) {
            size += totalSize(sub);
        }
        return size;
    }
}
