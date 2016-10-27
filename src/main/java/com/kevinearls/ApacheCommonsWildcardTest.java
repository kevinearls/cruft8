package com.kevinearls;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by kearls on 10/02/16.
 */
public class ApacheCommonsWildcardTest {
    final String targetString = "quickstarts";
    final File rootDirectory = new File("/Users/kearls/fuse/fabric8-karaf-1.2.0.redhat-630-SNAPSHOT/data/git/local/fabric/fabric/profiles/");


    @Test
    public void tryAgainTest() throws Exception {
        FileFilter profileFilter = new WildcardFileFilter("*.profile");
        //rootDirectory.toString().
        File[] profiles = rootDirectory.listFiles(profileFilter);
        System.out.println("Got " + profiles.length + " files");
        for (File f : profiles) {
            System.out.println("Found: " + f.getCanonicalPath());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    @Ignore
    @Test
    public void simpleTest() throws Exception {
        FileFilter filter = new MyFileFilter(targetString);
        blah(rootDirectory, filter);
    }

    public void blah(File rootDirectory, FileFilter filter) throws Exception {
       // System.out.println("Checking " + rootDirectory.getCanonicalPath());
        if (filter.accept(rootDirectory)) {
            System.out.println("Accepted (1)" + rootDirectory.getCanonicalPath());
        } else {
            if (rootDirectory.getCanonicalPath().contains(targetString)) {
                System.out.println("Should have matched");
            }
        }

        if (rootDirectory.isDirectory()) {
            for (File f : rootDirectory.listFiles()) {
                if (f.isDirectory()) {
                    blah(f, filter);
                } else {if (filter.accept(f)) {
                    System.out.println("Accepted (2)" + f.getCanonicalPath());
                }
                }
            }
        }
    }
}

class MyFileFilter implements FileFilter {
    WildcardFileFilter wcf;
    //private String target = "cxf";

    public MyFileFilter(String target) {
        wcf = new WildcardFileFilter(target);
    }

    @Override
    public boolean accept(File f) {
        return wcf.accept(f);
    }
}
