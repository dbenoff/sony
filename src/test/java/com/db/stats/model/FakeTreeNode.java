package com.db.stats.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeTreeNode implements TreeNode {

    String name;
    private FakeTreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private long size = 0;
    private Date modified = new Date();

    private FakeTreeNode () {
    }

    public FakeTreeNode (FakeTreeNode parent, List<TreeNode> children, String name) {
        this.parent = parent;
        this.children = children;
        this.name = name;
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public Date getModified() {
        return modified;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}
