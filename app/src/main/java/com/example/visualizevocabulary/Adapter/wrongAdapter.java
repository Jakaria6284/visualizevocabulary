package com.example.visualizevocabulary.Adapter;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visualizevocabulary.Model.userQuestionModel;
import com.example.visualizevocabulary.Model.wrongModel;
import com.example.visualizevocabulary.R;

import java.util.List;

public class wrongAdapter extends RecyclerView.Adapter<wrongAdapter.viewHolder> {

    List<wrongModel>wrongModelList;
    public wrongAdapter(List<wrongModel>wrongModelList) {
        this.wrongModelList = wrongModelList;


    }
    @NonNull
    @Override
    public wrongAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userexamitem,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wrongAdapter.viewHolder holder, int position) {
        String question=wrongModelList.get(position).getQuestion();
        String optionn1=wrongModelList.get(position).getOption1();
        String optionn2=wrongModelList.get(position).getOption2();
        String optionn3=wrongModelList.get(position).getOption3();
        String optionn4=wrongModelList.get(position).getOption4();
        String answer=wrongModelList.get(position).getAnswer();

        holder.setData(question,optionn1,optionn2,optionn3,optionn4,answer);

    }

    @Override
    public int getItemCount() {
        return wrongModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        private TextView Question;

        private Button opp1,opp2,opp3,opp4;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.aswer2);
            Question=itemView.findViewById(R.id.q);
            opp1=itemView.findViewById(R.id.useroption1);
            opp2=itemView.findViewById(R.id.useroption2);
            opp3=itemView.findViewById(R.id.useroption3);
            opp4=itemView.findViewById(R.id.useroption4);
        }
        public void setData(String Q,String O1,String O2,String O3,String O4,String ans)
        {
            Question.setText(Q);
            opp1.setText(O1);
            opp2.setText(O2);
            opp3.setText(O3);
            opp4.setText(O4);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(itemView.getContext());
                    dialog.setContentView(R.layout.showansweritem);
                    dialog.setCancelable(true);

                    Button button=dialog.findViewById(R.id.buttonforanswerset);
                    TextView textView=dialog.findViewById(R.id.questionforanswer);

                    button.setText(ans);
                    textView.setText(Q);
                    dialog.show();

                }
            });

            opp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                }
            });
            opp2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                }
            });
            opp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                }
            });
            opp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                }
            });

        }
    }
}
