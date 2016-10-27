package com.kevinearls;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kearls on 4/4/14.
 */
public class FileVisitorTest {
    @Test
    public void fileVisitorTest() throws IOException {
        Path rootPath = Paths.get(System.getProperty("user.home"), "data/");

        JenkinsJobsVisitor<Path> jenkinsJobsVisitor = new JenkinsJobsVisitor<Path>();
        Files.walkFileTree(rootPath, jenkinsJobsVisitor);
        System.out.println("--------------------------");

        Set<String> labels = jenkinsJobsVisitor.getLabels();
        Set<String> jdks = jenkinsJobsVisitor.getJdks();
        Set<String> testSuiteNames = jenkinsJobsVisitor.getTestSuiteNames();
        Map<String, Set<String>> axes = jenkinsJobsVisitor.getAxes();

        System.out.println("---- Labels ----");
        labels.stream().sorted().forEach(l -> System.out.println(l));
        System.out.println("----- JDKS -----");
        jdks.stream().sorted().forEach(j -> System.out.println(j));
        System.out.println("----- AXES -----");
        axes.keySet().stream().sorted().forEach(k -> {
            Set<String> jdksForLabel = axes.get(k);
            jdksForLabel.
                    stream().sorted().
                    forEach(j -> System.out.println(k + " " + j));

        });
        System.out.println("----- Test Suites -----");
        testSuiteNames.stream().sorted().forEach(t -> System.out.println(t));
    }
}

class RetiredJenkinsJobsVisitor<T> extends SimpleFileVisitor<T> {
    Map<String, Set<String>> axes = new HashMap<>();
    Set<String> testSuiteNames = new HashSet<>();
    Set<String> jdks = new HashSet<>();
    Set<String> labels = new HashSet<>();
    Path rootPath;
    Integer rootPartCount=0;

    public RetiredJenkinsJobsVisitor(Path rootPath) {
        this.rootPath=rootPath;
        rootPartCount = rootPath.toFile().getAbsolutePath().split("/").length;
    }

    @Override
    public FileVisitResult visitFile(T file, BasicFileAttributes attributes) {
        return FileVisitResult.CONTINUE;
    }
    @Override
    /**
     * // Looks like: /home/jenkins/jobs/smx4-specs-2.3.0.redhat-6-1-x-stable-platform/configurations/axis-jdk/jdk6/axis-label/rhel ; watch out for
     // /home/jenkins/jobs/smx4-specs-2.3.0.redhat-6-1-x-stable-platform/configurations/axis-jdk/jdk6/axis-label
     */
    public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) throws IOException {
        Path p = (Path) dir;
        String fileName = p.toFile().getAbsolutePath();
        if ((fileName.contains("axis-jdk") && fileName.contains("axis-label")) && !fileName.endsWith("axis-label") ) {      // Look for config.xml?

            List<String> parts = Arrays.asList(fileName.split("/"));
            int jdkIndex = parts.indexOf("axis-jdk") + 1;
            int labelIndex = parts.indexOf("axis-label") + 1;
            String label = parts.get(labelIndex);
            String jdk = parts.get(jdkIndex);
            String testSuiteName = parts.get(rootPartCount);

            testSuiteNames.add(testSuiteName);
            if (testSuiteName.startsWith("felix-framework-") /*&& fileName.contains("/builds/")*/) {  // TODO remove
                System.out.println(">>>> " + fileName);
            }
            jdks.add(jdk);
            labels.add(label);

            if (!axes.containsKey(label)) {
                Set<String> jdksForLabel = new HashSet<>();
                jdksForLabel.add(jdk);
                axes.put(label, jdksForLabel);
            } else {
                Set<String> jdksForLabel = axes.get(label);
                jdksForLabel.add(jdk);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(T dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    public Set<String> getTestSuiteNames() {
        return testSuiteNames;
    }

    public Set<String> getJdks() {
        return jdks;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public  Map<String, Set<String>> getAxes() {
        return axes;
    }
}

