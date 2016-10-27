package com.kevinearls.cruft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by kearls on 12/13/13.
 */
public class RemoveFromListExample {
    private static final String ONE = "one";
    private static final String TWO = "two";
    private static final String THREE = "three";
    String[] source = {ONE, TWO, THREE} ;
    public List<String> removeFromList(List<String> target, String removeThis) {
        target.remove(removeThis);
        return target;
    }

    public boolean removeFromCopyOnWriteArrayList() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        for (int i=0; i < source.length; i++) {
            list.add(source[i]);
        }

        removeFromList(list, TWO);
        System.out.println(">>> After remove, CopyOnWriteArrayList contains");
        for (String s : list) {
            System.out.println("\t" + s);
        }

        return true;
    }

    public boolean removeFromArrayList() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i=0; i < source.length; i++) {
            list.add(source[i]);
        }

        removeFromList(list, TWO);
        System.out.println(">>> After remove, ArrayList contains");
        for (String s : list) {
            System.out.println("\t" + s);
        }

        return true;
    }


    public static void main(String[] args) {
        RemoveFromListExample rmle = new RemoveFromListExample();
        rmle.removeFromArrayList();
        rmle.removeFromCopyOnWriteArrayList();
    }
}
