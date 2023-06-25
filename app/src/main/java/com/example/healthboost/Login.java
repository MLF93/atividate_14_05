package com.example.healthboost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Login extends AppCompatActivity {

    private EditText editTextUsername, editTextSenha;
    private Button buttonLogin;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersCollectionRef = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextSenha.getText().toString();
                login(username, password);
            }
        });
    }
    private void login(String username, String password) {
        // Consultar o Firestore para verificar se o username e senha são válidos
        Query query = usersCollectionRef
                .whereEqualTo("username", username)
                .whereEqualTo("password", password);

        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Credenciais de login corretas
                        startActivity(new Intent(Login.this, MainMenu.class).putExtra("username", username));
                    } else {
                        Toast.makeText(Login.this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Login.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                });
    }
}
