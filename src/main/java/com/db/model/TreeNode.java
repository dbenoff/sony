package com.db.model;

import java.util.Collection;

/**
 * represents an arbirtary node in a tree data structure
 */
public interface TreeNode {
    Collection<TreeNode> getChildren();
    boolean isDirectory();
}
