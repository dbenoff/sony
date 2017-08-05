package com.db.process;

import com.db.model.TreeNode;

/**
 * Created by dbenoff on 8/5/2017.
 */
public interface NodeProcessor
{
    public void process(TreeNode node) throws NodeProcessingException;

    public String getProcessingReport();
}
