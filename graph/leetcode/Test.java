package graph.leetcode;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {

        System.out.println(Stream.iterate(1 , x-> x+1).limit(5).toList());
    }
}
