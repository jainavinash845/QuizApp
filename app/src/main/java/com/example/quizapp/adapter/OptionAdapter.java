package com.example.quizapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.Quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

    Context context;
    Question question;
    List<String> options;
    public OptionAdapter(Context context, Question question){
        this.context = context;
        this.question = question;
        options =Arrays.asList(question.option1,question.option2,question.option3,question.option4);
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.option_layout,parent,false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OptionViewHolder holder, final int position) {
        holder.tvOpt1.setText(options.get(position));
        holder.tvOpt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                question.userAnswer = options.get(position);
                notifyDataSetChanged();


            }
        });
        if(question.userAnswer==options.get(position)){
            holder.tvOpt1.setBackgroundResource(R.drawable.option_line);
        }
        else
        {
            holder.tvOpt1.setBackgroundResource(R.drawable.option_item_bg);
        }

    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder{

        TextView tvOpt1,tvOpt2,tvOpt3,tvOpt4;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOpt1  = itemView.findViewById(R.id.tvOpt1);
//            tvOpt2  = itemView.findViewById(R.id.tvOpt2);
//            tvOpt3  = itemView.findViewById(R.id.tvOpt3);
//            tvOpt4  = itemView.findViewById(R.id.tvOpt4);
        }
    }

}
