package org.example.designfilesystem.withtrie;

import java.util.HashMap;
import java.util.Map;

public class File {
    String name;
    int value = -1;
    Map<String, File> map = new HashMap<>();

    public File(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", map=" + map +
                '}';
    }
}
