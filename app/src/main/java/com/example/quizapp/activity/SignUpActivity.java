package com.example.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.utils.FireBaseAuthantication;

public class SignUpActivity extends AppCompatActivity {
    EditText edEmail,edPassword,edConfirmPassword;
    Button  btnSignUp;
    TextView tvAlreadySignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvAlreadySignIn = findViewById(R.id.tvAlreadySignIn);

//        FirebaseApp.initializeApp(this);
        tvAlreadySignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String conPassword = edConfirmPassword.getText().toString();
                isValidate(email,password,conPassword);

            }
        });


    }
    public  boolean isValidate(String email,String password,String conPassword){
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Email and Password Can't be blank", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(conPassword)){
            Toast.makeText(getApplicationContext(), "password should be match", Toast.LENGTH_SHORT).show();
        }
        else {
            FireBaseAuthantication authantication  = new FireBaseAuthantication(SignUpActivity.this);
            authantication.signUpWithUser(email,password);
        }
        return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}