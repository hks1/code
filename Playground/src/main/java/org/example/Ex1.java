package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Ex1 {
    static class Position{
        int x;
        int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position position)) return false;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {
        Set<Position> set = new HashSet<>();


        Position p1 = new Position(1,1);
        Position p2 = new Position(1,1);

        System.out.println(p1.equals(p2));
        System.out.println(p1 == p2);
        set.add(p1);
        System.out.println(set);
        set.add(p2);
        System.out.println(set);

        int[] a1 = new int[]{1,2,3};
        int[] a2 = new int[]{1,2,3};
        System.out.println(a1.equals(a2));
        System.out.println(Arrays.equals(a1, a2));



    }
}
