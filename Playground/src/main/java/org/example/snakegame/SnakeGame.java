package org.example.snakegame;

import java.util.*;


// 353. Design Snake Game
// https://leetcode.com/problems/design-snake-game/description/
public class SnakeGame {
    int width;
    int height;
    int[][] food;



    // assumption: top left is (0,0)
    // move right: x++
    // move left: x--
    // move up: y--
    // move down: y++

    class Position implements  Cloneable{
        int c;
        int r;

        public Position(int c, int r){
            this.c = c;
            this.r = r;
        }

        private void moveUp(){
            //return new Position(x, --y);
            this.r--;
        }
        public void moveDown(){
            //return new Position(x, ++y);
            this.r++;
        }
        public void moveLeft(){
            //return new Position(--x, y);
            this.c--;
        }
        public void moveRight(){
            //return new Position(++x, y);
            this.c++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position position)) return false;
            return this.c == position.c && this.r == position.r;
        }

        @Override
        public int hashCode() {
            return Objects.hash(c, r);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "P{" +
                    "c=" + c +
                    ", r=" + r +
                    '}';
        }
    }


    Position current;

    Queue<Position> snake;
    Set<Position> set;

    // initialize a food queue from food array in the constructor
    // process food queue, if current position matches food queue head item,
    // remove it from the queue and increase the sixe of the snake
    Queue<Position> foodQueue;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width; // column
        this.height = height; // row
        this.food = food;
        current  = new Position(0,0);
        snake = new LinkedList<>();
        snake.offer(new Position(0,0)); // add current to snake
        set = new HashSet<>();
        set.add(new Position(0,0)); // add current to set
        foodQueue = new LinkedList<>();
        for (int i = 0; i < food.length; i++) {
            foodQueue.offer(new Position(food[i][1], food[i][0]));
        }

    }

    // possible values of direction; UP, DOWN, LEFT, RIGHT

    public int move(String direction) throws CloneNotSupportedException {
        System.out.println("MOVING ::::" + direction + " " + this);
        switch(direction.toUpperCase()){
            case "U":
            case "UP":
                System.out.println("current position before moving up:" + current);
                this.current.moveUp();
                System.out.println("current position after moving up:" + current);
                break;
            case "D":
            case "DOWN":
                System.out.println("current position before moving down:" + current);
                this.current.moveDown();
                System.out.println("current position after moving down:" + current);
                //System.out.println(this);
                break;
            case "L":
            case "LEFT":
                System.out.println("current position before moving left:" + current);
                this.current.moveLeft();
                System.out.println("current position after moving left:" + current);
                break;
            case "R":
            case "RIGHT":
                System.out.println("current position before moving right:" + current);
                this.current.moveRight();
                System.out.println("current position after moving right:" + current);
                break;
            default:
                System.out.println("Invalid direction");
        }
        if(current.c < 0 || current.c > width-1 || current.r < 0 || current.r > height - 1 || set.contains(current)){
            System.out.println("Current position: " +this.current +  "GAME OVER!!!");
            return -1;
        }else{
            // if snake head is not at food queue head, remove position from queue and set
            /*System.out.println("IN MOVE()::");
            System.out.println("snake.peek(): " +snake.peek());
            System.out.println("foodQueue.peek(): " +foodQueue.peek());
            System.out.println("set: " + set);
            System.out.println("BEFORE FOOD CHECK::::" + this);*/
            //if(!foodQueue.isEmpty() && !snake.peek().equals(foodQueue.peek())){
            /*if(foodQueue.isEmpty() || (!foodQueue.isEmpty() && !current.equals(foodQueue.peek()))){
                System.out.println("inside if: removing from set and snake");
                set.remove(snake.poll());
            }*/
            /*System.out.println("set  - after food check: " + set);
            System.out.println("AFTER FOOD CHECK::::" + this);*/

            if(!foodQueue.isEmpty() && foodQueue.peek().equals(current)){
                foodQueue.poll();
            }else{
                set.remove(snake.poll());
            }


            snake.offer((Position) current.clone());
            set.add((Position) current.clone());
        }
        System.out.println(snake.size() + " " + this);
        return snake.size() - 1;
    }

    @Override
    public String toString() {
        return "SnakeGame{" +
                "\ncurrent=" + current +
                ", \nsnake=" + snake +
                ", \nset=" + set +
                ", \nfoodQ=" + foodQueue +
                '}';
    }

    //public boolean isMoveValid(Position position)
}


/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */