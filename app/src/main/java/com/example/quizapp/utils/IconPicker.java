package com.example.quizapp.utils;

import com.example.quizapp.R;

public class IconPicker {

        public static  int[] icons ={
                R.drawable.ic_lightbulb,
                R.drawable.ic_bag,
                R.drawable.ic_education_filled,
                R.drawable.ic_iconfinder_ilustracoes,
                R.drawable.ic_iconfinder_library,
                R.drawable.ic_iconfinder_trophy};
        static int currentIcon =0;

        public static int getIcon() {
            currentIcon = (currentIcon+1)% icons.length;
            return icons[currentIcon];
        }
    }
