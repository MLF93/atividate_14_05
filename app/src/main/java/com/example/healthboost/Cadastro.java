package com.example.healthboost;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersCollectionRef = db.collection("users");

    // Declarar as variáveis
    EditText user, email, password;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        user = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        send = findViewById(R.id.buttonCadastrar);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criaUsuario();
            }
        });
    }

    private void criaUsuario() {
        String strUsername = user.getText().toString();
        String strEmail = email.getText().toString();
        String strPassword = password.getText().toString();

        if (TextUtils.isEmpty(strUsername)) {
            user.setError("O username não pode estar vazio");
            user.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(strEmail)) {
            email.setError("O e-mail não pode ficar vazio");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(strPassword)) {
            password.setError("A senha não pode estar vazia");
            password.requestFocus();
            return;
        }

        // Criando um mapa com os dados a serem salvos
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", strUsername);
        userMap.put("email", strEmail);
        userMap.put("password", strPassword);

        // Referência ao documento onde os dados serão salvos
        DocumentReference userDocRef = usersCollectionRef.document(strUsername);

        userDocRef
                .set(userMap)
                .addOnSuccessListener(aVoid -> {
                    // Dados do usuário salvos com sucesso
                    // Agora podemos adicionar os campos "quantity" e "time" diretamente ao documento do usuário

                    String[] categories = {"water", "food", "sleep", "exercise", "meds"};

                    for (String category : categories) {
                        Map<String, Object> warningData = new HashMap<>();
                        warningData.put("quantity", "0");
                        warningData.put("time", "None");

                        userDocRef.collection("warning")
                                .document(category)
                                .set(warningData)
                                .addOnSuccessListener(documentReference -> {
                                    // Dados de warning salvos com sucesso para a categoria
                                    // Você pode realizar qualquer ação adicional necessária
                                })
                                .addOnFailureListener(e -> {
                                    // Ocorreu um erro ao salvar os dados de warning para a categoria
                                });
                    }


                    startActivity(new Intent(Cadastro.this, MainMenu.class).putExtra("username", strUsername));
                })
                .addOnFailureListener(e -> {
                    // Ocorreu um erro ao salvar os dados do usuário
                });
    }

}