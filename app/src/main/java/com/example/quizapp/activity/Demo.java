package com.example.quizapp.activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.quizapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Demo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.demo_layout);
    }
}
