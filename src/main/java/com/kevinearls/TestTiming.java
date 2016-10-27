package com.kevinearls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by kearls on 19/08/14.
 */
public class TestTiming {
    public class TestResult{
        private String testName;

    }


    public void go() throws Exception {
        File file = new File("/Users/kearls/activemq-trunk-checkin.html");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        List<Float> times = new ArrayList<Float>();
        while((line = br.readLine()) != null){
            if(line.contains("Time elapsed:")){
                int location = line.indexOf("Time elapsed:");
                line = line.substring(location + "Time elapsed:".length() + 1);

                int firstSpace = line.indexOf(" ");
                String timeInSeconds = line.substring(0, firstSpace);
                float time = Float.parseFloat(timeInSeconds);
                times.add(time);
                //System.out.println(time +  "| " + line);
            }
        }
        br.close();
        fr.close();

        Collections.sort(times);
        Float total = new Float(0);
        for (Float time : times) {
            System.out.println(time );
            total = total + time;
        }

        System.out.println("Total " + total + " " + total / 3600);


        /*
        Stream<String> lines = Files.lines(Paths.get("/Users/kearls", "activemq-trunk-checkin.html"));
        lines = lines.filter(f -> f.contains("Time elapsed:"));
        List<String> blah = Arrays.asList((String[]) lines.toArray());

        for (String s : blah) {
            String parts[] = s.split(" ");
            System.out.println(parts.length );
        }

        System.out.println(blah.size());
        */
    }

    public static void main(String[] args) throws Exception {
        TestTiming tt = new TestTiming();
        tt.go();
    }
}

