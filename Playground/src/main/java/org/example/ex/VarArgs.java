package org.example.ex;

import java.lang.reflect.Array;
import java.util.Arrays;

public class VarArgs {

    public void method(String... args){
        System.out.println(Arrays.toString(args));
    }

    public static void main(String[] args) {
        VarArgs obj = new VarArgs();
        obj.method(new String[]{"str1", "str2", "str3"});
        obj.method(new String[]{"str1", "str2", "str3", "str4", "str5"});
        obj.method("vararg1", "vararg2", "vararg3");
        obj.method("vararg1", "vararg2", "vararg3", "str4", "str5");
    }
}
