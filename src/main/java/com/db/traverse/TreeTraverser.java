package com.db.traverse;

import com.db.process.NodeProcessingException;
import com.db.process.NodeProcessor;
import com.db.model.TreeNode;

/**
 * Represents algorithm class for performing tree traversal operations
 */
public abstract class TreeTraverser {

    protected NodeProcessor nodeProcessor;

    private TreeTraverser(){};

    /**
     * @param path node of tree to start from, call nodeprocessor on each node
     * @return
     */
    public abstract void dfs(TreeNode path) throws NodeProcessingException;

    /**
     * @param path node of tree to start from, call nodeprocessor on each node
     * @return
     */
    public abstract void bfs(TreeNode path) throws NodeProcessingException;

    public TreeTraverser(NodeProcessor nodeProcessor){
        this.nodeProcessor = nodeProcessor;
    }

    public NodeProcessor getNodeProcessor(){
        return nodeProcessor;
    }

}
