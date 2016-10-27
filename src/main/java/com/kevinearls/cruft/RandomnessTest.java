package com.kevinearls.cruft;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 1/30/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class RandomnessTest {
    private Integer max = 50000;
    private Integer maxIterations = max * 1000;

    private Float go() {
        Set<Integer> all = new HashSet<Integer>();
        for (int i=0; i < max; i++) {
            all.add(i);
        }
        //System.out.println(">>> Starting with " + all.size() + " numbers");

        Random rand = new Random(System.currentTimeMillis());
        int iterations = 0;
        while (all.size() > 0 && iterations < maxIterations ) {
            int nextInt = rand.nextInt(max);
            if (all.contains(nextInt)) {
                all.remove(nextInt);
            }
            iterations++;
        }

        float ratio = iterations/max;
        System.out.println(">>> Deleting " + max + " entries took " + iterations + " iterations, ratio: " + ratio);
        return ratio;
    }

    public static void main(String[] args) {
        RandomnessTest rt = new RandomnessTest();
        float highestRatio = (float) 0.0;
        for (int i=0; i < 500; i++) {
            float ratio = rt.go();
            if (ratio > highestRatio) {
                highestRatio = ratio;
            }

        }

        System.out.println("");
        System.out.println("Highest Ratio: " + highestRatio);
    }
}
