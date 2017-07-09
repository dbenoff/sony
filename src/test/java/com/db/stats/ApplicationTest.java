package com.db.stats;


import com.db.stats.model.FakeTreeNode;
import com.db.stats.model.TreeNode;
import com.db.stats.service.FakeTreeDataService;
import com.db.stats.algo.TreeTraverser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest  {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TreeTraverser treeTraversalHelper;

    /**
     * creates test dataset that exercises DFS and BFS searches
     * @return root with children populated
     */
    private FakeTreeNode getRoot(){
        FakeTreeNode root = new FakeTreeNode(null, new ArrayList<>(), "1");

        FakeTreeNode fakeTreeNode = root;
        fakeTreeNode.getChildren().add(new FakeTreeNode(null, new ArrayList<>(), "2"));
        fakeTreeNode.getChildren().add(new FakeTreeNode(null, new ArrayList<>(), "3"));
        fakeTreeNode.getChildren().add(new FakeTreeNode(null, new ArrayList<>(), "4"));

        fakeTreeNode.getChildren().get(0).getChildren().add(new FakeTreeNode(null, new ArrayList<>(), "5"));
        fakeTreeNode.getChildren().get(0).getChildren().add(new FakeTreeNode(null, new ArrayList<>(), "6"));
        fakeTreeNode.getChildren().get(2).getChildren().add(new FakeTreeNode(null, new ArrayList<>(), "7"));
        fakeTreeNode.getChildren().get(2).getChildren().add(new FakeTreeNode(null, new ArrayList<>(), "8"));

        return  fakeTreeNode;
    }

    @Test
    public void testBfs() {
        FakeTreeDataService fakeTreeService = new FakeTreeDataService();
        FakeTreeNode fakeTreeNode = getRoot();
        fakeTreeService.setRoot(fakeTreeNode);
        List<TreeNode> nodes = treeTraversalHelper.bfs(fakeTreeNode);
        assertEquals(8, nodes.size());
        assertEquals(nodes.get(0).getName(), "1");
        assertEquals(nodes.get(1).getName(), "2");
        assertEquals(nodes.get(2).getName(), "3");
        assertEquals(nodes.get(3).getName(), "4");
        assertEquals(nodes.get(4).getName(), "5");
        assertEquals(nodes.get(5).getName(), "6");
        assertEquals(nodes.get(6).getName(), "7");
        assertEquals(nodes.get(7).getName(), "8");
    }

    @Test
    public void testDfs() {
        FakeTreeDataService fakeTreeService = new FakeTreeDataService();
        FakeTreeNode fakeTreeNode = getRoot();
        fakeTreeService.setRoot(fakeTreeNode);
        List<TreeNode> nodes = treeTraversalHelper.dfs(fakeTreeNode);
        assertEquals(8, nodes.size());
        assertEquals(nodes.get(0).getName(), "1");
        assertEquals(nodes.get(1).getName(), "2");
        assertEquals(nodes.get(2).getName(), "5");
        assertEquals(nodes.get(3).getName(), "6");
        assertEquals(nodes.get(4).getName(), "3");
        assertEquals(nodes.get(5).getName(), "4");
        assertEquals(nodes.get(6).getName(), "7");
        assertEquals(nodes.get(7).getName(), "8");
    }
}