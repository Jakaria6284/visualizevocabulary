package com.example.visualizevocabulary.Adapter;









import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visualizevocabulary.Model.userQuestionModel;
import com.example.visualizevocabulary.R;
import com.example.visualizevocabulary.userExamActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userQuestionAdapter extends RecyclerView.Adapter<userQuestionAdapter.viewHolder> {
    List<userQuestionModel>userQuestionModelList;
    public FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    public FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();




    public static int count=0;
  Context context;

    public userQuestionAdapter(List<userQuestionModel> userQuestionModelList) {
        this.userQuestionModelList = userQuestionModelList;


    }

    @NonNull
    @Override
    public userQuestionAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.userexamitem,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userQuestionAdapter.viewHolder holder, int position) {
        String question=userQuestionModelList.get(position).getQuestion();
        String optionn1=userQuestionModelList.get(position).getOption1();
        String optionn2=userQuestionModelList.get(position).getOption2();
        String optionn3=userQuestionModelList.get(position).getOption3();
        String optionn4=userQuestionModelList.get(position).getOption4();
        String answer=userQuestionModelList.get(position).getAnswer();
        holder.setData(question,optionn1,optionn2,optionn3,optionn4,answer);


    }

    @Override
    public int getItemCount() {
        return userQuestionModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView Question;

        private Button opp1,opp2,opp3,opp4;



        boolean optionsClickable = true;



        public viewHolder(@NonNull View itemView) {
            super(itemView);
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









            opp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optionsClickable) {
                        optionsClickable = false; // Disable all options
                      //  imageView.setVisibility(View.VISIBLE);



                        opp1.setBackgroundColor(Color.GRAY);
                        String Option1 = opp1.getText().toString();
                        if (ans.equals(Option1)) {
                            count++;

                        }else {
                            CollectionReference userpostCollection = firebaseFirestore.collection("wrong")
                                    .document(firebaseUser.getUid())
                                    .collection("mcq");
                             DocumentReference newDocumentRef = userpostCollection.document();

                            Map<String,Object> wronganswer=new HashMap<>();
                            wronganswer.put("question",Q);
                            wronganswer.put("option1",O1);
                            wronganswer.put("option2",O2);
                            wronganswer.put("option3",O3);
                            wronganswer.put("option4",O4);
                            wronganswer.put("answer",ans);


                            newDocumentRef.set(wronganswer)
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful())
                                           {

                                           }
                                       }
                                   });
                        }
                    }
                }
            });

            opp2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optionsClickable) {
                        optionsClickable = false; // Disable all options
                    //   imageView.setVisibility(View.VISIBLE);



                        opp2.setBackgroundColor(Color.GRAY);
                        String Option2 = opp2.getText().toString();
                        if (ans.equals(Option2)) {
                            count++;
                        }else {
                            CollectionReference userpostCollection = firebaseFirestore.collection("wrong")
                                    .document(firebaseUser.getUid())
                                    .collection("mcq");
                            DocumentReference newDocumentRef = userpostCollection.document();
                            Map<String,Object> wronganswer=new HashMap<>();
                            wronganswer.put("question",Q);
                            wronganswer.put("option1",O1);
                            wronganswer.put("option2",O2);
                            wronganswer.put("option3",O3);
                            wronganswer.put("option4",O4);
                            wronganswer.put("answer",ans);


                            newDocumentRef.set(wronganswer)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {

                                            }
                                        }
                                    });
                        }
                    }
                }
            });

            opp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optionsClickable) {
                        optionsClickable = false; // Disable all options
                      //  imageView.setVisibility(View.VISIBLE);



                        opp3.setBackgroundColor(Color.GRAY);
                        String Option3 = opp3.getText().toString();
                        if (ans.equals(Option3)) {
                            count++;
                        }else {
                            CollectionReference userpostCollection = firebaseFirestore.collection("wrong")
                                    .document(firebaseUser.getUid())
                                    .collection("mcq");
                            DocumentReference newDocumentRef = userpostCollection.document();
                            Map<String,Object> wronganswer=new HashMap<>();
                            wronganswer.put("question",Q);
                            wronganswer.put("option1",O1);
                            wronganswer.put("option2",O2);
                            wronganswer.put("option3",O3);
                            wronganswer.put("option4",O4);
                            wronganswer.put("answer",ans);


                            newDocumentRef.set(wronganswer)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {

                                            }
                                        }
                                    });
                        }
                    }
                }
            });

            opp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optionsClickable) {
                        optionsClickable = false; // Disable all options
                       // imageView.setVisibility(View.VISIBLE);



                        opp4.setBackgroundColor(Color.GRAY);
                        String Option4 = opp4.getText().toString();
                        if (ans.equals(Option4)) {
                            count++;
                        }else {
                            CollectionReference userpostCollection = firebaseFirestore.collection("wrong")
                                    .document(firebaseUser.getUid())
                                    .collection("mcq");
                            DocumentReference newDocumentRef = userpostCollection.document();
                            Map<String,Object> wronganswer=new HashMap<>();
                            wronganswer.put("question",Q);
                            wronganswer.put("option1",O1);
                            wronganswer.put("option2",O2);
                            wronganswer.put("option3",O3);
                            wronganswer.put("option4",O4);
                            wronganswer.put("answer",ans);


                            newDocumentRef.set(wronganswer)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {

                                            }
                                        }
                                    });
                        }
                    }
                }
            });









        }








    }
}
