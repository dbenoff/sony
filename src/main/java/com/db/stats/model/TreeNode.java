package com.db.stats.model;

import java.util.Collection;
import java.util.Date;

/**
 * represents an arbirtary node in a tree data structure
 */
public interface TreeNode {
    public TreeNode getParent();
    public Collection<TreeNode> getChildren();
    public String getName();
    public long getSize();
    public Date getModified();
    public boolean isDirectory();

}
