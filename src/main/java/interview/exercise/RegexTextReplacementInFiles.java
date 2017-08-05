package interview.exercise;

import com.db.process.FileSystemTreeNodeProcessorImpl;
import com.db.traverse.FileSystemTreeTraverserImpl;
import com.db.process.NodeProcessingException;
import com.db.traverse.TreeTraverser;
import com.db.model.FileSystemTreeNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegexTextReplacementInFiles {

    public static void process(String startingPath, String regexPattern,
           String replacement, String fileAcceptPattern) {

        try {
            Stream<Path> fileStream =Files.find(Paths.get(startingPath), 999, (p, bfa) -> bfa.isRegularFile()
                    && p.getFileName().toString().matches(".*\\.processed"));
            List<Path> paths = fileStream.collect(Collectors.toList());
            for(Path path : paths){
                Files.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TreeTraverser treeTraverser = new FileSystemTreeTraverserImpl(
                new FileSystemTreeNodeProcessorImpl(regexPattern, replacement, fileAcceptPattern));
        File rootDir = new File(startingPath);
        FileSystemTreeNode rootNode = new FileSystemTreeNode(rootDir);
        try {
            treeTraverser.bfs(rootNode);
        } catch (NodeProcessingException e) {
            e.printStackTrace();
        }
        System.out.print(treeTraverser.getNodeProcessor().getProcessingReport());
    }

    public static void main(String[] args) {
        String startingDir = null, regexPattern = null, replacement = null, fileAcceptPattern = null;
        if (args.length >= 3) {
            startingDir = args[0];
            regexPattern = args[1];
            replacement = args[2];
        }
        if (args.length >= 4) {
            fileAcceptPattern = args[3];
        }
        if (startingDir != null) {
            process(startingDir, regexPattern, replacement, fileAcceptPattern);
        } else {
            System.out.println("Expected at least 3 parameters but got " + args.length);
        }
    }

}
