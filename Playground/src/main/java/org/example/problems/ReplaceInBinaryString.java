package org.example.problems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ReplaceInBinaryString {

    public String replace(String str){
        char[] arr = str.toCharArray();
        int left = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == '1'){
                swap(arr, i, ++left);
            }
        }

        return String.valueOf(arr);
    }
    public void swap(char[] chars, int i, int j){
        if(i == j) return;
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String str = "00000000111";
        System.out.println("input string: " + str);
        ReplaceInBinaryString obj = new ReplaceInBinaryString();
        String result = obj.replace(str);
        System.out.println(result);
    }
    /*public static void main(String[] args) {
        //String str = "0101";
        String str = "00000000111";
        System.out.println("input string: " + str);
        String result = "1100";

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //while (!stack.isEmpty() && stack.peek() == '0')
        }
        //str.replace("01", "10");
        //System.out.println(str.replace("01", "10"));
        boolean change = true;
        String out = null;
        int counter = 0;
        while(change){
            out = str.replace("01", "10");
            if(str.equals(out)){
                change = false;
            }else {
                counter++;
                str = out;
                System.out.println("counter: " + counter + " str: " + str);
            }
        }

        System.out.println("answer: " + counter + " str: " + str);
    }*/
}
