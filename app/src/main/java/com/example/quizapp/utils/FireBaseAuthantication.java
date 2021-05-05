package com.example.quizapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.quizapp.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthantication {
     FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    Context context;

    public FireBaseAuthantication(Context context){
        this.context = context;
    }




    public  void signUpWithUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Successfully created user", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                } else {
                    Toast.makeText(context, "Error connection with user", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public  void loginWithUser(String email,String password){

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener((Activity) context,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(context,"Login successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(context,MainActivity.class);
                                    context.startActivity(intent);
                                    ((Activity)context).finish();
                                }
                                else {
                                    Toast.makeText(context,"Login Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    public void reload(){
        firebaseAuth.getCurrentUser().reload().addOnCompleteListener((Activity) context, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context,"relod successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context,"relod Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void signWithExistingUser(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}

