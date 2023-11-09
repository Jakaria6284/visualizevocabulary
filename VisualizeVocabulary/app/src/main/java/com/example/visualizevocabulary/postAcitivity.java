package com.example.visualizevocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class postAcitivity extends AppCompatActivity {
    private EditText q1,op1,op2,op3,op4,a1;
    private Button ad1;
    String NAME;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_acitivity);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        NAME=getIntent().getStringExtra("Name");

        q1 = findViewById(R.id.question1);
        op1 = findViewById(R.id.option1);
        op2 = findViewById(R.id.option2);
        op3 = findViewById(R.id.option3);
        op4 = findViewById(R.id.option4);
        a1 = findViewById(R.id.answer1);


        ad1 = findViewById(R.id.add1);
        ad1.setVisibility(View.GONE); // Initially hide the button

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not used in this case
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateButtonVisibility();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not used in this case
            }
        };

        // Attach the TextWatcher to your EditText fields
        q1.addTextChangedListener(textWatcher);
        op1.addTextChangedListener(textWatcher);
        op2.addTextChangedListener(textWatcher);
        op3.addTextChangedListener(textWatcher);
        op4.addTextChangedListener(textWatcher);
        a1.addTextChangedListener(textWatcher);


        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = q1.getText().toString();
                String option1 = op1.getText().toString();
                String option2 = op2.getText().toString();
                String option3 = op3.getText().toString();
                String option4 = op4.getText().toString();
                String answer = a1.getText().toString();


                Map<String, Object> qdata = new HashMap<>();
                qdata.put("question", question);
                qdata.put("option1", option1);
                qdata.put("option2", option2);
                qdata.put("option3", option3);
                qdata.put("option4", option4);
                qdata.put("answer", answer);
                qdata.put("user",NAME);
                qdata.put("timestamp", FieldValue.serverTimestamp());

                CollectionReference userpostCollection = firebaseFirestore.collection("database")
                        .document("level1")
                        .collection("quiz");
                DocumentReference newDocumentRef = userpostCollection.document();

                qdata.put("documentId", newDocumentRef.getId());
                newDocumentRef.set(qdata)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    q1.getText().clear();
                                    op1.getText().clear();
                                    op2.getText().clear();
                                    op3.getText().clear();
                                    op4.getText().clear();
                                    a1.getText().clear();


                                    Toast.makeText(postAcitivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }

    private void updateButtonVisibility() {
        String question = q1.getText().toString();
        String option1 = op1.getText().toString();
        String option2 = op2.getText().toString();
        String option3 = op3.getText().toString();
        String option4 = op4.getText().toString();
        String answer = a1.getText().toString();

        boolean isAnyFieldEmpty = question.isEmpty() || option1.isEmpty() || option2.isEmpty() ||
                option3.isEmpty() || option4.isEmpty() || answer.isEmpty() ;

        ad1.setVisibility(isAnyFieldEmpty ? View.GONE : View.VISIBLE);
    }
}
