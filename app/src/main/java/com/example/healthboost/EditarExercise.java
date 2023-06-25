package com.example.healthboost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarExercise extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersCollectionRef = db.collection("users");


    EditText editTextQuantity;
    EditText editTextTime;
    Button buttonConfirmar;
    Button buttonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_exercise);

        editTextQuantity = findViewById(R.id.quantidade);
        editTextTime = findViewById(R.id.tempo);
        buttonConfirmar = findViewById(R.id.confirmarbtn);
        buttonCancelar = findViewById(R.id.cancelar);


        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = editTextQuantity.getText().toString();
                String time = editTextTime.getText().toString();

                // Atualizar os dados no Firebase
                atualizarDadosWarning(quantity, time);
                Intent intent = new Intent(EditarExercise.this, FuncoesExercise.class);
                startActivity(intent);
                finish();
            }
        });
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void atualizarDadosWarning(String quantity, String time) {
        SharedPreferences prefs = getSharedPreferences("Session", MODE_PRIVATE);
        String username = prefs.getString("username", "");
        // Referência ao documento de warning para a categoria "water" e o nome de usuário correspondentes
        DocumentReference warningDocRef = usersCollectionRef.document(username)
                .collection("warning")
                .document("exercise");

        warningDocRef
                .update("quantity", quantity, "time", time)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(EditarExercise.this, "Dados atualizados com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(EditarExercise.this, "Erro ao atualizar os dados", Toast.LENGTH_SHORT).show();
                });
    }
}