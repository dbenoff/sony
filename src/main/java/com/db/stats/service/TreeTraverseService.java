package com.db.stats.service;

import com.db.stats.model.TreeNode;

import java.util.Collection;
import java.util.List;

public interface TreeTraverseService {

    List<TreeNode> dfs(TreeNode path);

    List<TreeNode> bfs(TreeNode path);

}
