package com.db.traverse;

import com.db.model.TreeNode;
import com.db.process.NodeProcessingException;
import com.db.process.NodeProcessor;


import java.util.*;

public class FileSystemTreeTraverserImpl extends TreeTraverser {

    public FileSystemTreeTraverserImpl(NodeProcessor nodeProcessor) {
        super(nodeProcessor);
    }

    @Override
    public void dfs(TreeNode file){
        try {
            nodeProcessor.process(file);
        } catch (NodeProcessingException e) {
            e.printStackTrace();
        }
        dfsRecurse(file);
    }

    private void dfsRecurse(TreeNode file) {
        Collection<TreeNode> children = file.getChildren();
        if (children != null) {
            for (TreeNode child : children) {
                if (child.isDirectory()) {
                    dfsRecurse(child);
                }
            }
        }
    }

    @Override
    public void bfs(TreeNode root){
        try {
            nodeProcessor.process(root);
        } catch (NodeProcessingException e) {
            e.printStackTrace();
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode file = q.remove();
            Collection<TreeNode> children = file.getChildren();
            if (children != null) {
                for (TreeNode child : children) {
                    if (child.isDirectory()) {
                        q.add(child);
                        try {
                            nodeProcessor.process(child);
                        } catch (NodeProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
