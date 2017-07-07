package com.db.stats.service;


import com.db.stats.model.FileSystemTreeNode;
import com.db.stats.model.TreeNode;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileSystemTreeDataServiceImpl implements TreeDataService {

    @Override
    public TreeNode getTreeNode(String path) {
        return new FileSystemTreeNode(new File(path));
    }
}
