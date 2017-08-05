package com.db.process;

import com.db.model.TreeNode;

/**
 * Created by dbenoff on 8/5/2017.
 */
public interface NodeProcessor
{
    void process(TreeNode node) throws NodeProcessingException;

    String getProcessingReport();
}
