package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(-1);
        a.add(0);
        a.add(1);
        a.add(3);
        a.add(1);
        a.add(2);
        a.add(4);

        List<Integer> b = a.stream()
            .distinct()
            .toList();

        System.out.println(b);
    }
}