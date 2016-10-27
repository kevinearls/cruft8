package com.kevinearls.cruft;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 2/27/13
 * Time: 5:01 PM
 */
public class TestFailures {

    public void createScript(String fileName, List<String> linesFromFile) throws Exception{
        String scriptName = fileName.replace(".txt", ".sh");
        System.out.println(">>>> Writing to " + scriptName);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(scriptName)));

        for (String line : linesFromFile) {
            String[] parts = line.split("\\s");
            List<String> stringParts = Arrays.asList(parts);
            if (line.startsWith(">>>") && stringParts.size() >= 5) {
                String fullTestName = stringParts.get(1);
                String[] testNameParts = fullTestName.split("\\.");
                List<String> nameParts = Arrays.asList(testNameParts);
                // test suite name is next to last, test name is last
                String testSuiteName = nameParts.get(nameParts.size() - 2);
                String testCaseName = nameParts.get(nameParts.size() -1);
                out.println("\tmvn -Dtest=" + testSuiteName + "#" + testCaseName + " test");
            } else {
                System.err.println("Skipping line [" + line + "]");
            }
        }
        out.close();
    }

    public void createCsvFile(String fileName, List<String> linesFromFile) throws Exception{
        String csvFileName = fileName.replace(".txt", ".csv");
        System.out.println(">>>> Writing to " + csvFileName);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(csvFileName)));
        for (String line : linesFromFile) {
            String[] parts = line.split("\\s");
            List<String> stringParts = Arrays.asList(parts);
            if (line.startsWith(">>>") && stringParts.size() >= 5) {
                String fullTestName = stringParts.get(1);
                String duration = stringParts.get(stringParts.size() - 2);
                String failureCount = stringParts.get(stringParts.size() - 1);

                out.println(fullTestName + "," + duration + "," +  failureCount);
            } else {
                System.err.println("Skipping line [" + line + "]");
            }
        }
        out.close();
    }

    public void printNames(String fileName, List<String> linesFromFile) throws Exception{
        String namesFileName = fileName.replace(".txt", ".names");
        System.out.println(">>>> Writing to " + namesFileName);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(namesFileName)));

        for (String line : linesFromFile) {
            String[] parts = line.split("\\s");
            List<String> stringParts = Arrays.asList(parts);
            if (line.startsWith(">>>") && stringParts.size() >= 5) {
                String fullTestName = stringParts.get(1);
                out.println(fullTestName);
            } else {
                System.err.println("Skipping line [" + line + "]");
            }
        }
        out.close();
    }

    /**
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public List<String> getLinesSorted(String fileName) throws Exception{
        List<String> linesFromFile = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\s");
            List<String> stringParts = Arrays.asList(parts);
            if (line.startsWith(">>>") && stringParts.size() >= 5) {
                linesFromFile.add(line);
            } else {
                System.err.println("Skipping line [" + line + "]");
            }
        }
        br.close();

        Collections.sort(linesFromFile);
        return linesFromFile;
    }



    public static void main(String[] args) throws Exception{
        String base = "/Users/kearls/sources/activemq/";

        FilenameFilter matcher = new FileMatcher();
        File root = new File(base);
        File[] matches = root.listFiles(matcher);
        List<File> matchesList = Arrays.asList(matches);
        for (File f : matchesList) {
            String fileName = base + f.getName();
            int dot = fileName.lastIndexOf(".");
            String testName = fileName.substring(0, dot);

            TestFailures tf = new TestFailures();
            List<String> testLines =  tf.getLinesSorted(fileName);
            System.out.println(">>>> Using " + fileName);
            System.out.println("--------------- CSV File --------------- ");
            tf.createCsvFile(fileName, testLines);
            System.out.println("--------------- Script File --------------- ");
            tf.createScript(fileName, testLines);
            System.out.println("--------------- File names for " + testName + "--------------- ");
            tf.printNames(fileName, testLines);
        }
    }
}

/**
 * Match files that presumably contain a list of failing tests cut and pasted from Hudson
 */
class FileMatcher implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        if (name.endsWith("Failures.txt") || name.endsWith("failures.txt")) {
            return true;
        } else {
            return false;
        }
    }
}
