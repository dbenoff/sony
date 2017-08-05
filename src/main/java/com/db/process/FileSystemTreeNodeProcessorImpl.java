package com.db.process;

import com.db.model.FileSystemTreeNode;
import com.db.model.TreeNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dbenoff on 8/5/2017.
 */
public class FileSystemTreeNodeProcessorImpl implements NodeProcessor {

    private Pattern regex;
    private String replacement;
    private String filePattern;
    private FileSystemProcessingStatus status = new FileSystemProcessingStatus();

    public FileSystemTreeNodeProcessorImpl(String regex, String replacement, String filePattern) {
        this.regex = Pattern.compile(regex);
        this.replacement = replacement;
        this.filePattern = filePattern.replace("?", ".?").replace("*", ".*?");
    }

    private FileSystemTreeNodeProcessorImpl() {
    }

    @Override
    public void process(TreeNode node) throws NodeProcessingException {
        if (!(node instanceof FileSystemTreeNode) || !node.isDirectory())
            return;
        FileSystemTreeNode fileSystemTreeNode = (FileSystemTreeNode) node;
        File dir = fileSystemTreeNode.file;
        File[] files = dir.listFiles((dir1, name) -> name.matches(filePattern));
        for (int i = 0; i < files.length; i++) {
            try {

                File targetFile = new File(files[i].getAbsolutePath()+".processed");
                FileWriter filewriter = new FileWriter(targetFile);
                BufferedWriter outputStream= new BufferedWriter(filewriter);

                FileInputStream fstream = new FileInputStream(files[i]);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    Matcher m = regex.matcher(strLine);
                    List<String> matches = new ArrayList<>();
                    while (m.find()) {
                        String match = m.group(0);
                        if(!status.replacementFrequencyMap.containsKey(match))
                            status.replacementFrequencyMap.put(match, 0);
                        status.replacementFrequencyMap.put(match, status.replacementFrequencyMap.get(match) + 1);
                        matches.add(match);
                    }
                    for(String match : matches){
                        strLine = strLine.replace(match, replacement);
                    }
                    outputStream.write(strLine);
                }
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                throw new NodeProcessingException(e.getMessage());
            }
            status.filesProcessedCount++;
        }
    }

    @Override
    public String getProcessingReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("Processed " + status.filesProcessedCount + " files\n");
        builder.append("Replaced to: " + replacement + "\n");
        for(String word : status.replacementFrequencyMap.keySet()){
            builder.append(" * " + word + ": " + status.replacementFrequencyMap.get(word)
                    + (status.replacementFrequencyMap.get(word) > 1 ? " occurences\n" : "occurence\n"));
        }
        return builder.toString();
    }

    public FileSystemProcessingStatus getStatus(){
        return status;
    }
}
