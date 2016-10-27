package com.kevinearls.cruft;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HudsonGenDiff {

    /**
     *
     * @param generatedFileName   Name of file containing a list of generated file names
     * @return
     * @throws Exception
     */
    private Set<String> loadGeneratedFileName(String generatedFileName) throws Exception {
        Set<String> fileNames = new HashSet<String>();
        FileReader fr = new FileReader(generatedFileName);
        BufferedReader br = new BufferedReader(fr);
        String fileName = br.readLine();
        while (fileName != null) {
            fileNames.add(fileName.trim());
            fileName = br.readLine();
        }

        return fileNames;
    }


    /**
     *
     * @param existingFileNames  ame of file containing a list of existing file names
     * @throws IOException
     */
    public void go(String existingFileNames) throws Exception {
        int count=0;
        Set<String> generatedFileNames = loadGeneratedFileName("/Users/kearls/sources/Cruft/resources/allGeneratedJobs.txt");

        FileReader fr = new FileReader(existingFileNames);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            String fileName = line.trim();
            if (!generatedFileNames.contains(fileName)) {
                // TODO how to figure out disabled jobs?
                if (!fileName.contains("-shipped-") && !fileName.contains("-stable-")) {
                    System.out.println(fileName);
                    count++;
                }

            }
            line = br.readLine();
        }
        System.out.println("Found " + count + " differences");

    }

    public static void main(String[] args) throws Exception{
        HudsonGenDiff jd = new HudsonGenDiff();
        jd.go("/Users/kearls/sources/Cruft/resources/allHudsonJobs.txt");
    }
}
