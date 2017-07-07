package com.db.stats.service;

import com.db.stats.model.TreeNode;
import org.springframework.stereotype.Service;

public interface TreeDataService {
    public TreeNode getTreeNode(String path);
}
