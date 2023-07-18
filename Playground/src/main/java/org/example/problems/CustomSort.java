package org.example.problems;

import java.util.*;

public class CustomSort {
    //static String[] votes = {"ABC","ACB","ABC","ACB","ACB"};

    static String[] votes = {"ABC","ACB","ABC","ACB","ACB", "BCA", "BAC", "BCA", "BAC", "BCA", "BCA"};

    public static void main(String[] args) {
        Map<Character, int[]> map = new HashMap<>();

        for(int i = 0; i < votes.length ; i++){
            for (int j = 0; j < votes[i].length(); j++){
                char c = votes[i].charAt(j);
                if(!map.containsKey(c)){
                    map.put(c, new int[votes[0].length()+1]);
                }
                map.get(c)[j+1]++;
            }
        }
        char[] teams = votes[0].toCharArray();
        List<Character> chars = new ArrayList<>();
        for (char c :
                teams) {
            chars.add(c);
        }
        System.out.println(map);
        chars.sort( (a, b) -> {
            for(int i = 0; i < map.get(a).length; i++){
                if(map.get(a)[i] != map.get(b)[i]){
                    //return map.get(a)[i] - map.get(b)[i];
                    // descending order
                    return map.get(b)[i] - map.get(a)[i];
                }

            }
            return 0;
        });
        System.out.println(Arrays.toString(teams));
        System.out.println(chars);
    }
}
