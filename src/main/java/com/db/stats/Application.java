package com.db.stats;


import com.db.stats.model.TreeNode;
import com.db.stats.service.TreeDataService;
import com.db.stats.service.TreeTraverseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private TreeDataService treeDataService;
    @Autowired
    private TreeTraverseService treeTraverseService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String pathString = null, type = null, format = null;
        if(args != null && args.length > 2 && (args[1].equalsIgnoreCase("bfs") || args[1].equalsIgnoreCase("dfs"))
                && (args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("detailed"))){
            if(args.length > 2){
                pathString = args[0];
                type = args[1];
                format = args[2];
            }
        }else{
            log.warn("must provide a file path, search type (bfs|dfs), and output (normal|detailed) in the syntax <path> <type> <output>");
            return;
        }


        Collection<TreeNode> paths;
        TreeNode path = treeDataService.getTreeNode(pathString);
        if(type.equalsIgnoreCase("bfs")){
            paths = treeTraverseService.bfs(path);
        }else{
            paths = treeTraverseService.dfs(path);
        }
        for(TreeNode childPath : paths){
            if(format.equalsIgnoreCase("normal")){
                System.out.println(childPath.getName());
            }else{
                System.out.println(childPath.getName() + " size: " + childPath.getSize() + " modified: " + childPath.getModified());
            }
        }

    }

    public void setTreeDataService(TreeDataService treeDataService) {
        this.treeDataService = treeDataService;
    }

}