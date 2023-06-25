package com.example.healthboost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class FuncoesMeds extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersCollectionRef = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcoes_meds);

        ImageView returnImageView = findViewById(R.id.return_icon);
        returnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Chama o método para pesquisar os dados de warning
        pesquisarDadosWarning("meds");

        ImageView editImageView = findViewById(R.id.editquant_btn);
        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncoesMeds.this, EditarMeds.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void pesquisarDadosWarning(String category) {
        // Recupera o nome de usuário das SharedPreferences
        SharedPreferences prefs = getSharedPreferences("Session", MODE_PRIVATE);
        String username = prefs.getString("username", "");

        // Consulta o documento de warning com base no username e categoria
        DocumentReference warningDocRef = usersCollectionRef.document(username)
                .collection("warning")
                .document(category);

        warningDocRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot != null && documentSnapshot.exists()) {
                            String quantity = documentSnapshot.getString("quantity");
                            String time = documentSnapshot.getString("time");

                            // Use os valores de warning conforme necessário
                            // Exemplo: Armazene-os em variáveis para uso posterior
                            // Neste exemplo, vamos apenas exibi-los em TextViews
                            TextView textViewQuantity = findViewById(R.id.quantidade);
                            TextView textViewTime = findViewById(R.id.tempo);

                            textViewQuantity.setText(quantity);
                            textViewTime.setText(time);
                        } else {
                            // O documento de warning não existe para a categoria especificada
                        }
                    } else {
                        // Ocorreu um erro ao obter os dados de warning
                    }
                });
    }
}
