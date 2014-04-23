package com.kevinearls;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kearls on 4/4/14.
 */
public class Java8CollectionsExamples {
    private void go() {
        String[] numbers = {"one", "two", "three", "four", "five"};
        List<String> myNumbers = Arrays.asList(numbers);
        myNumbers.stream().
                filter(n -> n.contains("o")).
                forEach(s -> System.out.println(s));
    }

    public static void main(String[] args) {
        Java8CollectionsExamples me = new Java8CollectionsExamples();
        me.go();
    }

}
