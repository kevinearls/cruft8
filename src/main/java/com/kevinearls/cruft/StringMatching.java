package com.kevinearls.cruft;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

public class StringMatching {

    String[] names = {
            "activemq-5.6.1.fuse-7-1-x-stable-checkin",
            "activemq-5.7.0.fuse-7-1-x-stable-platform",
            "activemq-5.9.0.redhat-6-1-x-stable-checkin",
            "activemq-5.9.0.redhat-6-1-x-stable-platform",
            "archetypes-2012.01.0.fuse-7-1-x-stable-platform",
            "archetypes-2012.01.0.fuse-7-2-x-stable-platform",
            "archetypes-2013.01.0.redhat-6-1-x-stable-checkin",
            "archetypes-2013.01.0.redhat-6-1-x-stable-platform",
            "aries-blueprint-1.0.0.fuse-7-1-x-stable-platform",
            "aries-blueprint-1.0.1.redhat-6-1-x-stable-checkin",
            "aries-blueprint-1.0.1.redhat-6-1-x-stable-platform",
            "aries-jmx-1.0.1.fuse-7-2-x-stable-platform",
            "aries-jmx-1.0.1.redhat-6-1-x-stable-checkin",
            "aries-jmx-1.0.1.redhat-6-1-x-stable-platform",
            "aries-jpa-1.0.1.redhat-6-1-x-stable-checkin",
            "aries-proxy-1.0.1.redhat-6-1-x-stable-checkin",
            "aries-proxy-1.0.1.redhat-6-1-x-stable-platform",
            "aries-transaction-1.0.0.fuse-7-1-x-stable-platform",
            "aries-transaction-1.0.1.redhat-6-1-x-stable-checkin",
            "aries-transaction-1.0.1.redhat-6-1-x-stable-platform",
            "aries-util-1.0.1.fuse-7-2-x-stable-platform",
            "aries-util-1.0.1.redhat-6-1-x-stable-checkin",
            "aries-util-1.0.1.redhat-6-1-x-stable-platform",
            "camel-2.12.0.redhat-6-1-x-stable-checkin",
            "camel-2.12.0.redhat-6-1-x-stable-platform",
            "cxf-2.6.0.fuse-7-1-shipped-checkin",
            "cxf-2.6.0.fuse-7-1-x-stable-checkin",
            "cxf-2.7.0.redhat-6-1-x-stable-checkin",
            "cxf-2.7.0.redhat-6-1-x-stable-platform",
            "felix-configadmin-6.1.x.redhat-stable-checkin",
            "felix-configadmin-6.1.x.redhat-stable-deploy",
            "felix-configadmin-6.1.x.redhat-stable-dualjdk",
            "felix-configadmin-6.1.x.redhat-stable-platform",
            "felix-eventadmin-6.1.x.redhat-stable-checkin",
            "felix-eventadmin-6.1.x.redhat-stable-deploy",
            "felix-eventadmin-6.1.x.redhat-stable-platform",
            "fuseenterprise-6.1.x.redhat-stable-checkin",
            "karaf-2.3.0.redhat-6-1-x-stable-checkin",
            "karaf-2.3.0.redhat-6-1-x-stable-platform",
            "smx-components-2013.01.0.redhat-6-1-x-stable-checkin",
            "smx-components-2013.01.0.redhat-6-1-x-stable-platform",
            "smx-utils-1.6.0.redhat-6-1-x-stable-checkin",
            "smx-utils-1.6.0.redhat-6-1-x-stable-platform",
            "smx4-features-4.5.0.redhat-6-1-x-stable-checkin",
            "smx4-features-4.5.0.redhat-6-1-x-stable-platform",
            "smx4-nmr-1.6.0.fuse-7-1-x-shipped-checkin",
            "smx4-nmr-1.6.0.fuse-7-1-x-stable-checkin",
            "smx4-nmr-1.6.0.redhat-6-1-x-stable-checkin",
            "smx4-nmr-1.6.0.redhat-6-1-x-stable-platform",
            "smx4-specs-2.0.0.fuse-7-2-x-stable-platform",
            "smx4-specs-2.3.0.redhat-6-1-x-stable-checkin",
            "smx4-specs-2.3.0.redhat-6-1-x-stable-platform"
    };
    List<String> fileNames = Arrays.asList(names);


    public void go() {
        String sixOnePattern=".*6[-\\.]1.*";
        String redHatBeforePattern = ".*redhat.*6[-\\.]1.*";
        String redHatAfterPattern = ".*6[-\\.]1.*redhat.*";
        String redHatCombinedPattern = redHatBeforePattern + "|" + redHatAfterPattern + "(-checking|-platform)";

        System.out.println(">>> Matching on [" + redHatCombinedPattern + "]");
        int selected=0;
        for (String name : fileNames) {

            if (name.matches(redHatCombinedPattern)) {
                System.out.println(name);
                selected++;
            }
        }
        System.out.println(">>> Selected " + selected);
    }

    public static void main(String[] args) {
        StringMatching sm = new StringMatching();
        sm.go();

    }

}


/**
 * Filter to select directories which contain desired results
 *
 * @author kearls
 *
 */
class PlatformDirectoryFilter implements FileFilter {
    String matchRegularExpression = "";

    public PlatformDirectoryFilter(String target) {
        this.matchRegularExpression = target;
    }

    @Override
    public boolean accept(File pathname) {
        String name = pathname.getName();
        return name.matches(matchRegularExpression);
    }
}
