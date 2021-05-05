package com.example.quizapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.activity.QuestionActivity;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.utils.ColorPicker;
import com.example.quizapp.utils.IconPicker;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizHolder> {

    public List<Quiz> quizList ;
    public Context context;


    public QuizAdapter(List<Quiz> quizList,Context context){
        this.quizList = quizList;
        this.context = context;

    }



    @NonNull
    @Override
    public QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_adapter, parent, false);

        return new QuizHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuizHolder holder, int position) {
        final Quiz quiz  = quizList.get(position);
        holder.tvQuizTitle.setText(quiz.title);
        holder.cardViewQuiz.setCardBackgroundColor(Color.parseColor(ColorPicker.getColors()));
        holder.ivQuizIcon.setImageResource(IconPicker.getIcon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,quiz.title,Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(context, QuestionActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class QuizHolder extends RecyclerView.ViewHolder {

        TextView tvQuizTitle;
        ImageView ivQuizIcon;
        CardView cardViewQuiz;
        public QuizHolder(@NonNull View itemView) {
            super(itemView);

            tvQuizTitle = itemView.findViewById(R.id.tvQuizTitle);
            ivQuizIcon = itemView.findViewById(R.id.ivQuizIcon);
            cardViewQuiz = itemView.findViewById(R.id.cardViewQuiz);
        }
    }
}
