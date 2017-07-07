package com.db.stats.service;


import com.db.stats.model.TreeNode;

public class FakeTreeDataService implements TreeDataService {

    private TreeNode root;

    @Override
    public TreeNode getTreeNode(String path) {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
