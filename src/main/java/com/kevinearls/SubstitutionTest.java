package com.kevinearls;

/**
 * Created by kearls on 01/03/16.
 */
public class SubstitutionTest {
    public static void main(String[] arsgs) {
        String URL = "https://origin-repository.jboss.org/nexus/content/groups/ea/org/jboss/amq/jboss-a-mq/6.3.0.redhat-025/jboss-a-mq-6.3.0.redhat-025.zip";

        //sh export ZIPFILENAME=$(echo $URL|sed 's/^.*\/amq\/jboss-a-mq\///g' | sed 's/^.*\///g')
        //sh export AMQ_HOME=$(echo $ZIPFILENAME | sed 's/\.zip//g')
        String zipFileName = URL.replaceAll("^.*amq/jboss-a-mq", "").replaceAll(".*\\/", "");
        System.out.println("Got ZIPFILENAME: [" + zipFileName + "]");
        String AMQ_HOME = zipFileName.replace(".zip", "");
        System.out.println("Got AMQ_HOME [" + AMQ_HOME + "]");

        System.out.println("Last slash " + URL.lastIndexOf("/") + " length " + URL.length());
        int lastSlash = URL.lastIndexOf("/");
        String zfName = URL.substring(lastSlash + 1);
        System.out.println("ZIP FILE  [" + zfName + "]");
        String dirName = zfName.substring(0, zfName.length() - 4);
        System.out.println("Directory [" + dirName + "]");


    }
}
