package com.db.stats;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Application {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public static void main(String[] args) {
        Application app = new Application();
        if(args != null && args.length > 2 && (args[1].equalsIgnoreCase("bfs") || args[1].equalsIgnoreCase("dfs"))
                && (args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("detailed"))){
            if(args.length > 2)
            app.run(args[0], args[1], args[2]);
        }else{
            throw new RuntimeException("must provide a file path, search type (bfs|dfs), and output (normal|detailed) in the syntax <path> <type> <output>");
        }
    }

    private void run(String pathString, String type, String format){
        Collection<File> paths;
        File path = new File(pathString);
        if(type.equalsIgnoreCase("bfs")){
            paths = bfs(path);
        }else{
            paths = new ArrayList<>();
            dfs(path, paths);
        }
        for(File childPath : paths){
            try {
                long size = Files.walk(childPath.toPath()).mapToLong(p -> p.toFile().length() ).sum();
                BasicFileAttributes view
                        = Files.getFileAttributeView(childPath.toPath(), BasicFileAttributeView.class)
                        .readAttributes();
                if(format.equalsIgnoreCase("normal")){
                    System.out.println(childPath.getPath());
                }else{
                    System.out.println(childPath.getPath() + " size: " + size + " modified: " + view.lastModifiedTime());
                }

            } catch (IOException e) {
                log.warn(e.getMessage());
                throw new RuntimeException();
            }
        }
    }

    private Collection<File> bfs(File root){
        Collection<File> all = new ArrayList<>();
        Queue<File> q = new LinkedList<File>();
        q.add(root);
        while (!q.isEmpty()) {
            File file = q.remove();
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isDirectory()) {
                        q.add(child);
                        all.add(child);
                    }
                }
            }
        }
        return all;
    }

    private void dfs(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if (child.isDirectory()) {
                    all.add(child);
                    dfs(child, all);
                }
            }
        }
    }

}