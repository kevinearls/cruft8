package com.kevinearls.cruft;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 2/13/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class MavenExec {
    private static final Integer MAX_ITERATIONS = 100;
    private static final String ACTIVEMQ_HOME="/Users/kearls/sources/apache/activemq/";
    public static final String ACTIVEMQ_UNIT_TESTS = "activemq-unit-tests";
    public static final String ACTIVEMQ_XMPP = "activemq-xmpp";
    private Map<String, String> failingTests;

    public MavenExec() {
        failingTests = new HashMap<String, String>();
        failingTests.put("NioQueueSubscriptionTest", ACTIVEMQ_UNIT_TESTS);
        failingTests.put("XmppTest", ACTIVEMQ_XMPP);
        failingTests.put("JDBCMessagePriorityTest", ACTIVEMQ_UNIT_TESTS);
    }

    public String getOutputFromStream(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = in.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }

        return builder.toString();
    }


    public void go(List<String> testNames) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        for (String testName : testNames) {
            // TODO add logging
            System.out.println(">>>>> Starting " + testName);
            Integer iteration = 1;
            boolean failed = false;
            while ((iteration <= MAX_ITERATIONS) && !failed) {
                System.out.println(">>>> Iteration " + iteration + " of " + testName);
                Process p = runtime.exec("mvn -Dtest=" + testName + " test");
                p.waitFor();
                String output = getOutputFromStream(p.getInputStream());
                if (p.exitValue() != 0) {
                    System.out.println("**** Test " + testName + " failed on iteration " + iteration);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(output);
                    System.out.println("-----------------------------------------------------------");
                    failed= true;
                } else {
                    iteration++;
                }
            }

        }
    }

    /**
     * FIXME - figure out how to get runtime.exec with File parameter to work
     * @throws IOException
     */
    /*
    public void goOLD() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String[] env = new String[] {};
        for (String testName : failingTests.keySet()) {
            String testDirectoryName = failingTests.get(testName);
            // TODO add logging
            System.out.println(">>>>> Starting " + testName + " in " + ACTIVEMQ_HOME + testDirectoryName);
            File testDirectory = new File(ACTIVEMQ_HOME + testDirectoryName);
            Integer iteration = 1;
            boolean failed = false;
            while ((iteration <= MAX_ITERATIONS) && !failed) {
                System.out.println(">>>> Iteration " + iteration + " of " + testName);
                Process p = runtime.exec("mvn -Dtest=" + testName + " test", env, testDirectory);
                String output = getOutputFromStream(p.getInputStream());
                System.out.println(output);
                if (p.exitValue() != 0) {
                    System.out.println("**** Test " + testName + " failed on iteration " + iteration);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(output);
                    System.out.println("-----------------------------------------------------------");
                    failed= true;
                } else {
                    iteration++;
                }
            }

        }
    }    */


    public static void main(String[] args) throws IOException, InterruptedException {
        MavenExec me = new MavenExec();
        // Slower tests are tat the end
        String[] failingTestNames = {
                "XBeanStartFalseTest",
                "AMQ2736Test",
                "AMQ4083Test",
                "AMQ4221Test",
                "AMQ2584Test",
                "CompressionOverNetworkTest",
                "DbRestartJDBCQueueMasterSlaveLeaseTest",
                "JDBCMessagePriorityTest",
                "JMSConsumerTest",
                "JmsTempDestinationTest",
                "JobSchedulerStoreTest",
                "LevelDBXARecoveryBrokerTest",
                "MessageEvictionTest",
                "TwoSecureBrokerRequestReplyTest",
                "VMTransportThreadSafeTest",
                "LevelDBXARecoveryBrokerTest",
                "TransactedStoreUsageSuspendResumeTest",
                "DurableSubscriptionOfflineTest",
                "PListTest",
                "JDBCMessagePriorityTest"
        };
        List<String> testNames = Arrays.asList(failingTestNames) ;
        me.go(new ArrayList<String>(testNames));
    }
}
