package com.kevinearls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kearls on 4/22/14.
 */
public class RemoveFromSet {

    public void dump(Set<String> strings) {
        for (String s : strings) {
            System.out.print(s + " ");
        }
        System.out.println("");
    }

    public void go() {
        Map<String, Set<String>> axes = new HashMap<>();
        String[] normal = {"jdk6", "jdk7"};
        String[] full = {"jdk6", "jdk7", "openjdk6", "openjdk7"};
        String[] foo =  {"jdk5", "jdk6", "jdk7"};


        String[] wtf = {"one", "two", "three", "four", "five"};
        Set<String> blah = new HashSet<>();

        List<String> wtfList = Arrays.asList(wtf);
        for (String w : wtfList) {
            blah.add(w);
        }

        dump(blah);

        blah.remove("one");
        System.out.println("After ");
        dump(blah);

    }

    public static void main(String[] args) {
        RemoveFromSet rfs = new RemoveFromSet();
        rfs.go();




    }
}
