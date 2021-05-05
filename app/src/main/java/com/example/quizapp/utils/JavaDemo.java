package com.example.quizapp.utils;

import java.util.ArrayList;
import java.util.List;

public class JavaDemo {

    public static void main(String args[]){
        String[] fruit = {"mango","orange","banana","grapes","apple"};

//        List<String> fruitAdd = new ArrayList<>();
//        fruitAdd.add (fruit)

        for(String fruitType: fruit){
            System.out.println("for each "+fruitType);
        }

        for(int i=0;i<fruit.length;i++){
            System.out.println("for loop "+fruit[i]);
        }
    }


}

 class Main extends Thread {
    public static int amount = 0;

    public static void main(String[] args) {
        Integer iOb = 60, iOb2=80; // autobox an int
        int i = iOb; // auto-unbox
        System.out.println (i + " " + iOb);

        System.out.println (i + " " + iOb2);
    }
}


