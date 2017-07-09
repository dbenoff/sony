package com.db.stats.algo;

import com.db.stats.model.TreeNode;

import java.util.Collection;
import java.util.List;

/**
 * Represents algorithm class for performing tree traversal operations
 */
public interface TreeTraverser {

    /**
     * @param path node of tree to explore
     * @return List of nodes in dfs traversal order starting from root.
     */
    List<TreeNode> dfs(TreeNode path);
    /**
     * @param path node of tree to explore
     * @return List of nodes in bfs traversal order starting from root.
     */
    List<TreeNode> bfs(TreeNode path);

}
