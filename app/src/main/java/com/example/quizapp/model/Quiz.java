package com.example.quizapp.model;

import java.util.HashMap;
import java.util.Map;

public class Quiz {
    public String id = "";
    public String title = "";
    public Map<String,Question> questions  = new HashMap<>();


    public Quiz(){ }


    public Quiz(String id,String title){
        this.id = id;
        this.title = title;

    }


}
