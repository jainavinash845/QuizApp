package com.example.quizapp.utils;

import android.graphics.Color;

public class ColorPicker {
   public  static String[] colors ={"#f8f8ff","#f5fffa","#afeeee","#48d1cc","#556b2f","#ffd700","#cd5c5c","#ff69b4"};
   static int currentColorIndex =0;

   public static  String getColors() {
      currentColorIndex = (currentColorIndex+1)% colors.length;
      return colors[currentColorIndex];
   }


}
