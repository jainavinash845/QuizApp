package com.example.quizapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.OptionAdapter;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.Quiz;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    TextView tvQesDes;
    Button btnPrevious ,btnNext;
    RecyclerView rvOption ;
    OptionAdapter optionAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    com.google.firebase.firestore.FirebaseFirestore firestore;

    List<Quiz> questionList  = new ArrayList<>();
    Question question = new Question();
    HashMap<String,Question> questionHashMap =new HashMap<>();
    int index =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tvQesDes = (TextView) findViewById(R.id.tvQesDes);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setVisibility(View.GONE);

        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnPrevious.setVisibility(View.GONE);
        rvOption = (RecyclerView) findViewById(R.id.rvOption);
        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        firestore = FirebaseFirestore.getInstance();


        CollectionReference collectionReference = firestore.collection("Quiz");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value , @Nullable FirebaseFirestoreException error) {
                if (value == null || error != null) {
                    Toast.makeText(getApplicationContext() , "Error fetching data" , Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("DATA" , value.getMetadata().toString());
                Log.d("DATA" , value.toObjects(Quiz.class).toString());
//                questionList.clear();
//                questionList.addAll(value.toObjects(com.example.quizapp.model.Quiz.class));
//                optionAdapter.notifyDataSetChanged();


            }
        });
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                questionList = (ArrayList<Quiz>) queryDocumentSnapshots.toObjects(Quiz.class);
                questionHashMap = (HashMap<String, Question>) questionList.get(0).questions;
//                question = questionHashMap.get("question1");
                Log.d("question" , question.description);

                setQuestion(questionHashMap);






//               optionAdapter.notifyDataSetChanged();


            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                setQuestion(questionHashMap);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                setQuestion(questionHashMap);
            }
        });

    }


//              " With which of the following was satyajit Ray associated ?",
//              "Commercial art",
//              "Classical Music",
//              "Classical Dance",
//              " Direction of films",
//              " Direction of films");
//        question  = questionHashMap.get("question1");








    private void setQuestion(HashMap<String, Question> questionHashMap) {
        if(index ==1){
            btnNext.setVisibility(View.VISIBLE);
        }
        else if(index==questionHashMap.size()){
            btnNext.setText("FINISH");
            btnNext.setVisibility(View.VISIBLE);
            btnPrevious.setVisibility(View.VISIBLE);
        }
        else {
            btnNext.setVisibility(View.VISIBLE);
            btnPrevious.setVisibility(View.VISIBLE);
        }

        List keys = new ArrayList(questionHashMap.keySet());
        for(int i= 0;i<keys.size();i++){
//          question =  keys.get(i);
            tvQesDes.setText(question.description);


        }
//        question= questionHashMap.get("question1");

        optionAdapter = new OptionAdapter(QuestionActivity.this,question);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rvOption.setLayoutManager(layoutManager);
        rvOption.setAdapter(optionAdapter);
    }
}