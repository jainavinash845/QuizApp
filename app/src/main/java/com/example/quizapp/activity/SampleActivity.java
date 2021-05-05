package com.example.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizapp.R;

public class SampleActivity extends AppCompatActivity {



    private ProgressDialog progDialog;
    int typeBar;
    TextView text1;
    EditText edit;
    Button respond;
    private String name;
    private String textAtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sample);

        text1 = (TextView)findViewById(R.id.textView2);
        edit = (EditText)findViewById(R.id.editTextTextPersonName);
        respond = (Button)findViewById(R.id.button);



        Log.d("MainActivity","savedInstance :" + savedInstanceState );
        if(savedInstanceState != null){
            String savedName =   savedInstanceState.getString("SAVEDNAME");
            Log.d("MainActivity","savedName :" +savedName  );
            text1.setText( "your  savedInstance is called "+savedName);
        }
        else{
            text1.setText("Hello! What is your name?");
        }

        respond.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = edit.getText().toString();
                text1.setText("Nice to meet you "+ name);
            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putString("SAVEDNAME", name);
        Log.d("MainActivity","savedInstance :" + name);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState (savedInstanceState);

       name =  savedInstanceState.getString ("SAVEDNAME");
        Log.d("MainActivity","RestoreSavedInstance :" + name);


    }


}
