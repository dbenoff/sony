package com.db.model;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TreeNode wrapping a file handle in a filesystem
 */
public class FileSystemTreeNode implements TreeNode {

    public File file;

    private FileSystemTreeNode () {
    }

    public FileSystemTreeNode (File file) {
        this.file = file;
    }

    @Override
    public Collection<TreeNode> getChildren() {
        List<TreeNode> children = new ArrayList<>();
        for(File child : file.listFiles()){
            children.add(new FileSystemTreeNode(child));
        }
        return children;
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }
}
