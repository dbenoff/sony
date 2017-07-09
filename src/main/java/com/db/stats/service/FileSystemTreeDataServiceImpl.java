package com.db.stats.service;


import com.db.stats.model.FileSystemTreeNode;
import com.db.stats.model.TreeNode;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileSystemTreeDataServiceImpl implements TreeDataService {

    /**
     * Given a unix style file paty, return a node representing the file system object
     * @param path unix style path
     * @return TreeNode representing the file system object
     */
    @Override
    public TreeNode getTreeNode(String path) {
        return new FileSystemTreeNode(new File(path));
    }
}
