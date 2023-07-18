package org.example.designfilesystem.withtrie;

public class FileSystem {
    File root;

    public FileSystem(){
        root = new File("");
    }

    public boolean createPath(String path, int value){
        String[] p = path.split("/");

        File curr = root;

        for (int i = 1; i < p.length; i++) {
            String curr_path = p[i];
            if (!curr.map.containsKey(curr_path)){
                if(i == p.length-1){
                    curr.map.put(curr_path, new File(curr_path));
                }else{
                    return false;
                }
            }
            curr = curr.map.get(curr_path);
        }
        curr.value = value;
        return true;
    }

    public int get(String path){
        String[] p = path.split("/");
        File curr = root;
        for (int i = 1; i < p.length; i++) {
            if(!curr.map.containsKey(p[i])){
                return -1;
            }
            curr = curr.map.get(p[i]);  // p[i] is curr_path
        }
        return curr.value;
    }

    @Override
    public String toString() {
        return "FileSystem{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.createPath("/a", 1);
        fileSystem.createPath("/a/b", 2);
        System.out.println(fileSystem.get("/c"));
        System.out.println(fileSystem.get("/a"));
        System.out.println(fileSystem.get("/a/b"));
        System.out.println(fileSystem);
    }
}


// https://leetcode.com/problems/design-file-system/solutions/366094/java-python-trietree-solution/

/*
class FileSystem {
    class File{
        String name;
        int val = -1;
        Map<String, File> map = new HashMap<>();

        File(String name){
            this.name = name;
        }
    }
    File root;
    public FileSystem() {
        root = new File("");
    }

    public boolean create(String path, int value) {
        String[] array = path.split("/");
        File cur = root;

        for(int i=1;i<array.length;i++){
            String cur_name = array[i];
            if(cur.map.containsKey(cur_name)==false){
                if(i==array.length-1){
                    cur.map.put(cur_name, new File(cur_name));
                }else{
                    return false;
                }
            }
            cur = cur.map.get(cur_name);
        }

        if(cur.val!=-1){
            return false;
        }

        cur.val = value;
        return true;
    }

    public int get(String path) {
        String[] array = path.split("/");
        File cur = root;
        for(int i=1;i<array.length;i++){
            String cur_name = array[i];
            if(cur.map.containsKey(cur_name)==false){
                return -1;
            }
            cur = cur.map.get(cur_name);
        }

        return cur.val;


    }
}
*/
/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.create(path,value);
 * int param_2 = obj.get(path);
 */
