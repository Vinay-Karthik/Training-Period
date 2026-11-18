package Composite_Pattern.Without;

import java.util.ArrayList;
import java.util.List;

// A folder that can contain files AND other folders.
// Problem: we must handle FileItem and FolderItem differently everywhere.
public class FolderItem {
    private final String name;
    private final List<FileItem> files = new ArrayList<>();
    private final List<FolderItem> subFolders = new ArrayList<>();

    public FolderItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFile(FileItem file) {
        files.add(file);
    }

    public void addFolder(FolderItem folder) {
        subFolders.add(folder);
    }

    public List<FileItem> getFiles() {
        return files;
    }

    public List<FolderItem> getSubFolders() {
        return subFolders;
    }
}
