package com.db.stats.service;

import com.db.stats.model.TreeNode;
import org.springframework.stereotype.Service;

public interface TreeDataService {
    /**
     * Given a string path to navigate, returns the appropriate TreeNode
     * @param path arbitrary path to a node
     * @return the node corresponding to the given path
     */
    public TreeNode getTreeNode(String path);
}
