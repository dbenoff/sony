package com.db.stats.model;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class FileSystemTreeNode implements TreeNode {

    private File file;

    private FileSystemTreeNode () {
    }

    public FileSystemTreeNode (File file) {
        this.file = file;
    }

    @Override
    public TreeNode getParent() {
        return new FileSystemTreeNode(file.getParentFile());
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
    public String getName() {
        return file.getPath();
    }

    @Override
    public long getSize() {
        long size = 0l;
        try {
            size = Files.walk(file.toPath()).mapToLong(p -> p.toFile().length() ).sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public Date getModified() {
        BasicFileAttributes view
                = null;
        try {
            view = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class)
            .readAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Date(view.lastModifiedTime().toMillis());
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }
}
