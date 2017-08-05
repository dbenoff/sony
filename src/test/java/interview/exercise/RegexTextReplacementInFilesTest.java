package interview.exercise;

import com.db.model.FileSystemTreeNode;
import com.db.process.FileSystemProcessingStatus;
import com.db.process.FileSystemTreeNodeProcessorImpl;
import com.db.process.NodeProcessingException;
import com.db.traverse.FileSystemTreeTraverserImpl;
import com.db.traverse.TreeTraverser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class RegexTextReplacementInFilesTest {

    @Test
    public void testSanity() {

        String startingPath = "c:\\sample_dir";
        String regexPattern = "\\w*(lan)\\w+";
        String replacement = "<-replaced->";
        String fileAcceptPattern = "*.txt";
        TreeTraverser treeTraverser = new FileSystemTreeTraverserImpl(
                new FileSystemTreeNodeProcessorImpl(regexPattern, replacement, fileAcceptPattern));
        File rootDir = new File(startingPath);
        FileSystemTreeNode rootNode = new FileSystemTreeNode(rootDir);
        try {
            treeTraverser.bfs(rootNode);
        } catch (NodeProcessingException e) {
            e.printStackTrace();
        }
        FileSystemProcessingStatus status = ((FileSystemTreeNodeProcessorImpl)treeTraverser.getNodeProcessor()).getStatus();
        Assert.assertEquals(status.filesProcessedCount, 7);
        Assert.assertEquals(status.replacementFrequencyMap.keySet().size(), 6);
    }
}
