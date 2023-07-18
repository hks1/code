package org.example.snakegame;

import java.util.*;

public class Main {
    /*public static void main(String[] args) throws CloneNotSupportedException {
        SnakeGame game = new SnakeGame(3,2, new int[][]{{1,2}, {0,1}});

        SnakeGame.Position pos1 = game.new Position(1,2);
        SnakeGame.Position pos2 = game.new Position(1,2);

        System.out.println(pos2.equals(pos1)); // true after overriding equals() method in Position class
        System.out.println(pos2 == pos1); // false

        List<Character> inputs = new ArrayList<>(Arrays.asList('R', 'D', 'R', 'U', 'L', 'U'));
        List<Integer> scores = new ArrayList<>();

        for(char c : inputs){
            scores.add(game.move(String.valueOf(c)));
        }

        System.out.println(inputs + "\n" + scores);

        *//*game.move("R");
        game.move("D");
        game.move("R");
        game.move("U");
        game.move("L");
        game.move("U");*//*

        *//*System.out.println(game);
        game.move("right");
        System.out.println(game);
        game.move("down");
        System.out.println(game);
        int score = game.move("RIGHT");
        System.out.println("scoure: " + score) ;
        System.out.println(game);
        score = game.move("right");
        System.out.println("scoure: " + score) ;
        System.out.println(game);
        score = game.move("up");
        System.out.println("scoure: " + score) ;
        System.out.println(game);
        score = game.move("left");
        System.out.println("scoure: " + score) ;
        System.out.println(game);
        score = game.move("up");
        System.out.println("scoure: " + score) ;
        System.out.println(game);*//*
    }*/

    /*public static void main(String[] args) throws CloneNotSupportedException {
        SnakeGame game = new SnakeGame(3, 3, new int[][] {{2,0}, {0,0}, {0,2}, {0,1}, {2,2}, {0,1}});

        // [[3,3,[[2,0],[0,0],[0,2],[0,1],[2,2],[0,1]]],["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["L"],["D"],["R"],["U"]]
        List<Character> inputs = new ArrayList<>(Arrays.asList('D', 'D', 'R', 'U', 'U', 'L', 'D', 'R', 'R', 'U', 'L', 'L', 'D', 'R', 'U'));
        // Expected
        // [null,0,1,1,1,1,2,2,2,2,3,4,4,4,4,-1]
        // scores - [0,1,1,1,1,2,2,2,2,3,4,4,4,4,-1]
        List<Integer> expectedScores = new ArrayList<>(Arrays.asList(0,1,1,1,1,2,2,2,2,3,4,4,4,4,-1));

        List<Integer> scores = new ArrayList<>();

        // [[3,3,[[2,0],[0,0],[0,2],[2,2]]],["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["D"]]


        for(char c : inputs){
            scores.add(game.move(String.valueOf(c)));
        }

        System.out.println("Assertion: " + scores.equals(expectedScores));

        System.out.println(inputs + "\n" + scores);

        int[] a1 = new int[]{1,1};
        int[] a2 = new int[]{1,1};

        Set<int[]> set = new HashSet<>();
        set.add(a1);
        set.add(a2);
        System.out.println(set.size());

        Map<int[], Boolean> map = new HashMap<>();
        map.put(a1, true);
        map.put(a2, true);

        System.out.println(map);
    }*/

    public static void main(String[] args) throws CloneNotSupportedException {
        SnakeGame game = new SnakeGame(3, 3, new int[][] {{2,0}, {0,0}, {0,2},  {2,2}});

        // [[3,3,[[2,0],[0,0],[0,2],[2,2]]],["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["D"]]
        List<Character> inputs = new ArrayList<>(Arrays.asList('D', 'D', 'R', 'U', 'U', 'L', 'D', 'R', 'R', 'U', 'L', 'D'));
        // expected - [null,0,1,1,1,1,2,2,2,2,3,3,3]

        List<Integer> expectedScores = new ArrayList<>(Arrays.asList(0,1,1,1,1,2,2,2,2,3,3,3));

        List<Integer> scores = new ArrayList<>();


        for(char c : inputs){
            scores.add(game.move(String.valueOf(c)));
        }

        System.out.println("Assertion: " + scores.equals(expectedScores));

        System.out.println(inputs + "\n" + scores);

    }
}
