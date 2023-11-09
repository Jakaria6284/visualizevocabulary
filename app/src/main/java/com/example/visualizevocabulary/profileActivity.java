package com.example.visualizevocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visualizevocabulary.Adapter.leaderboardAdapter;
import com.example.visualizevocabulary.Adapter.showanswerAdapter;
import com.example.visualizevocabulary.Model.leaderboardModel;
import com.example.visualizevocabulary.Model.showanswerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class profileActivity extends AppCompatActivity {
    Button signout,motivebutton;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    Dialog dialog;

    private TextView motive,namee,first,second,third;
    String name;
    EditText editText;
    ImageView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationviewww);
        bottomNavigationView.setSelectedItemId(R.id.homeee);
        signout=findViewById(R.id.signout);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();
        first=findViewById(R.id.first);
        second=findViewById(R.id.second);
        third=findViewById(R.id.third);


        motive=findViewById(R.id.motiv);
        namee=findViewById(R.id.profilename);


        dialog=new Dialog(this);
        dialog.setContentView(R.layout.motivedialog);
        dialog.setCancelable(true);
        editText=dialog.findViewById(R.id.inputbio);
        motivebutton=dialog.findViewById(R.id.motivebtn);

        firebaseFirestore.collection("USER").orderBy("coin", Query.Direction.DESCENDING).limit(3).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int index = 1;

                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                String name = queryDocumentSnapshot.getString("User");
                                if (name != null) {



                                    switch (index) {
                                        case 1:
                                            first.setText(name);
                                            break;
                                        case 2:
                                            second.setText(name);
                                            break;
                                        case 3:
                                            third.setText(name);
                                            break;
                                    }
                                    index++;

                                }


                            }
                        }
                    }
                });









        firebaseFirestore.collection("USER").document(firebaseUser.getUid())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists())
                            {
                               String nameee=documentSnapshot.getString("User");
                                String mmm=documentSnapshot.getString("motive");
                                namee.setText(nameee);
                                motive.setText(mmm);
                            }
                        }

                    }
                });



        motive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             dialog.show();

            }
        });

        motivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mot=editText.getText().toString();
                firebaseFirestore.collection("USER")
                        .document(firebaseUser.getUid())
                        .update("motive",mot)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(profileActivity.this, "Successfully update", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profileActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
                firebaseAuth.signOut();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.homeee) {
                startActivity(new Intent(profileActivity.this,HomeActivity.class));
                finish();

                return true;
            } else if (itemId == R.id.readee) {
                startActivity(new Intent(profileActivity.this,readActivity.class));
                finish();
                return true;

            } else if (itemId == R.id.quizeee) {
                startActivity(new Intent(profileActivity.this,quizeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.trunamentee) {
                startActivity(new Intent(profileActivity.this,examActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.profileee) {

                return true;
            }

            return false;
        });

    }
}