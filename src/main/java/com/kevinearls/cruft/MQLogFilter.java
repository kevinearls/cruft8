package com.kevinearls.cruft;

import java.io.*;
import java.net.InetAddress;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 4/4/13
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MQLogFilter {

    public List<String> loadFile(File logFile ) throws IOException {
        List<String> content = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(logFile));
        String line;
        while ((line = br.readLine()) != null) {
            content.add(line);
        }

        br.close();
        return content;
    }


    // tcp://192.168.1.4:61799

    /**
     *
     * Filter out things that look like:tcp://192.168.1.4:61799 and tcp://Kevins-MacBook-Pro.local:61793
     *
     * @param content
     * @return
     * @throws IOException
     */
    public List<String> filterTcpUrls(List<String> content) throws IOException {
        List<String> updatedContent = new ArrayList<String>();
        for (String line : content) {
            if (line.contains("tcp://")) {
                //line.replaceAll()
                line = line.replaceAll("tcp://.*:[0-9][0-9][0-9][0-9][0-9]", "tcp://host:nnnnn");
                line = line.replaceAll("tcp://.*@[0-9][0-9][0-9][0-9][0-9]", "tcp://host:nnnnn@nnnnn");
                if (line.contains("@")) {

                    System.out.println(">>>> " + line);
                }
                updatedContent.add(line);
            } else {
                updatedContent.add(line);
            }


        }
        return updatedContent;
    }

    /**
     * Remove timestamps at the beginning of the line
     *
     * @param content
     * @return
     * @throws IOException
     */
    public List<String> removeTimeStamps(List<String> content) throws IOException {
        List<String> updatedContent = new ArrayList<String>();
        for (String line : content) {
            int firstParen = line.indexOf("[");
            if (firstParen >= 0) {
                line = line.substring(firstParen);
            }
            updatedContent.add(line);
        }
        return updatedContent;
    }

    /**
     * Filter activemq ids
     *
     * @param content
     * @return
     *
     * @throws IOException
     */
    public List<String> maskIds(List<String> content) throws IOException {
        InetAddress addr = InetAddress.getLocalHost();
        String hostname = addr.getHostName();
        String target = "ID:" + hostname + "-";

        List<String> updatedContent = new ArrayList<String>();
        for (String line : content) {
            String[] splitParts = line.split("\\s|,|\\)");
            List<String> parts = Arrays.asList(splitParts);
            boolean updated = false;
            String updatedLine="";
            for (String part : parts) {
                if (part.startsWith("ID:" + hostname + "-")) {
                    updatedLine = line.replace(part, "ID:Kevins-MacBook-Pro.local-ID");     // FIXME assumes only one id per line, which might be ok
                    updated = true;
                }
            }

            if (updated) {
                updatedContent.add(updatedLine);
            } else {
                updatedContent.add(line);
            }


        }
        return updatedContent;
    }


    /**
     * Take this log file, run all filters against it, and write it to a file with "filtered" in the name
     * @param logFile
     */
    public void filterLog(File logFile) throws IOException {
        MQLogFilter lf = new MQLogFilter();
        List<String> fileContent = lf.loadFile(logFile);
        fileContent = lf.maskIds(fileContent);
        fileContent = lf.removeTimeStamps(fileContent);
        fileContent = lf.filterTcpUrls(fileContent);
        // TODO add other filters here.
        // TODO remove blank lines?
        // TODO filter java object ids?
        // TODO filter tcp connections?

        // figure out the new filename
        String filteredFileName = logFile.getAbsolutePath() + ".filtered";
        System.out.println(">>> Writing " + filteredFileName);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filteredFileName)));

        for (String line : fileContent) {
            out.println(line);
        }
        out.close();
    }


    public static void main(String[] args) throws IOException {
        MQLogFilter lf = new MQLogFilter();

        String base = "/Users/kearls/sources/apache/activemq/activemq-unit-tests/";
        FilenameFilter matcher = new LogFileMatcher();
        File root = new File(base);
        File[] matches = root.listFiles(matcher);
        List<File> matchesList = Arrays.asList(matches);
        for (File f : matchesList) {
            System.out.println(">>>> Filtering " + f.getAbsolutePath());
            lf.filterLog(f);
        }
    }
}


/**
 * Matches log files
 */
class LogFileMatcher implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        if (name.endsWith("log")) {
            return true;
        } else {
            return false;
        }
    }
}
