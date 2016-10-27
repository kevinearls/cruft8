package com.kevinearls;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by kearls on 4/7/14.
 */
public class LambdaQuickStart {
    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        System.out.println("\n---- Starting " + testName.getMethodName() + " -----");
    }

    @Test
    public void testRunnable() {
        Runnable r1 = () -> System.out.println("Hello from r1");
        Runnable r2 = () -> System.out.println("Hello from r2");

        r1.run();
        r2.run();
    }

    @Test
    public void testComparator() {
        List<Human> people = Human.createShortList();

        people.stream().
                sorted((Human p1, Human p2) -> p1.getName().compareTo(p2.getName())).
                forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testRoboCall04() {
        List<Human> people = Human.createShortList();

        people.stream().filter(new ReadyToRetire())
                .forEach(h -> System.out.println(h.getName()));
    }
}

// TODO do something with this
class StupidFunction implements Function<Human, String> {

    @Override
    public String apply(Human s) {
        return null;
    }
}



class ReadyToRetire implements Predicate<Human> {

    @Override
    public boolean test(Human h) {
        return h.getAge() > 60;
    }
}

enum Gender{ Male, Female };

class Human {
    private String name;
    private Integer age;
    private Gender gender;

    public static List<Human> createShortList() {
        Human joe = new Human("Joe", 42, Gender.Male);
        Human steve = new Human("Steve", 61, Gender.Male);
        Human jill = new Human("Jill", 27, Gender.Female);

        List<Human> people = new ArrayList<>();
        people.add(joe);
        people.add(steve);
        people.add(jill);

        return Collections.unmodifiableList(people);
    }

    public Human(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() { return name; }
    public Integer getAge() { return age; }
    public Gender getGender() { return gender; }
}


