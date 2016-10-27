package com.kevinearls;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kearls on 10/02/16.
 */
public class WalkFileTreeExample {
    final static File rootDirectory = new File("/Users/kearls/fuse/fabric8-karaf-1.2.0.redhat-630-SNAPSHOT/data/git/local/fabric/fabric/profiles/");
    final static Path rootPath = rootDirectory.toPath();

    public static void main(String[] args) throws Exception {
        MyFileVisitor<Path> visitor = new MyFileVisitor<>(rootDirectory, "quickstarts");
        Files.walkFileTree(rootPath, visitor);
        System.out.println("Found " + visitor.getProfiles().size() + " profiles");
        List<File> matchingProfiles = visitor.getProfiles();
        for (File profile : matchingProfiles) {
            System.out.println(">> Found [" + profile.toString().replace(rootDirectory.getCanonicalPath() + "/", "") + "]");
        }
    }
}

class MyFileVisitor<T> extends SimpleFileVisitor<T> {
    private List<File> profiles = new ArrayList<>();
    private PathMatcher pathMatcher;

    public MyFileVisitor(File rootDirectory, String pattern) {
        pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + rootDirectory.toString() + "**" + pattern + "**.profile");
    }

    @Override
    public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) throws IOException {
        Path p = (Path) dir;

        if (pathMatcher.matches(p)) {
            System.out.println("Matched: [" + p.toString() + "] Filename? " + p.toFile().getName());
            profiles.add(p.toFile());
        }
        return FileVisitResult.CONTINUE;
    }

    public List<File> getProfiles() {
        return profiles;    // TODO return a copy blah blah blah
    }

}
