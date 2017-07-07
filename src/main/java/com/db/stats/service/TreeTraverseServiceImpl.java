package com.db.stats.service;

import com.db.stats.model.TreeNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TreeTraverseServiceImpl implements TreeTraverseService {
    @Override
    public List<TreeNode> dfs(TreeNode file){
        List<TreeNode> all = new ArrayList<>();
        all.add(file);
        dfsRecurse(file, all);
        return all;
    }

    private void dfsRecurse(TreeNode file, List<TreeNode> all) {
        Collection<TreeNode> children = file.getChildren();
        if (children != null) {
            for (TreeNode child : children) {
                if (child.isDirectory()) {
                    all.add(child);
                    dfsRecurse(child, all);
                }
            }
        }
    }

    @Override
    public List<TreeNode> bfs(TreeNode root){
        List<TreeNode> all = new ArrayList<>();
        all.add(root);
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode file = q.remove();
            Collection<TreeNode> children = file.getChildren();
            if (children != null) {
                for (TreeNode child : children) {
                    if (child.isDirectory()) {
                        q.add(child);
                        all.add(child);
                    }
                }
            }
        }
        return all;
    }
}
