package com.kevinearls;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by kearls on 7/11/14.
 */
public class MacFilesStuff {
    public static void main(String[] args) throws  Exception {
        /*File[] roots = File.listRoots();
        System.out.println(Arrays.asList(File.listRoots()));



        System.out.println(new File("/tmp/x").getCanonicalPath());
        File x = new File("/Users/kearls/fred.txt");
        PosixFileAttributes fred = Files.readAttributes(x.toPath(), PosixFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        Set<PosixFilePermission> permissions = fred.permissions() ;
        for (PosixFilePermission permission: permissions) {
            System.out.println("" + permission.toString());
        }
        //PosixFilePermission.    */

        for (int i=0; i <=7; i++) {
            System.out.println("i: " + i +  " 04 " + (i & 04)  + " 02 " + (i & 02)  + " 01 " + (i & 01) );
        }

    }
}
