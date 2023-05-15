package com.example.healthboost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText editTextUsername, editTextSenha;
    private Button buttonLogin;

    private SharedPreferences sharedPreferences;

    public static final String MyPreferences = "Arquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonLogin = findViewById(R.id.buttonLogin);

        sharedPreferences = getSharedPreferences(MyPreferences, 0);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsername.getText().toString().trim();
                String senha = editTextSenha.getText().toString().trim();

                if (!usuario.isEmpty() && !senha.isEmpty()) {
                    // Verifica se o usuário e a senha correspondem aos dados salvos
                    String savedUsername = sharedPreferences.getString("usuario", "");
                    String savedSenha = sharedPreferences.getString("senha", "");

                    if (usuario.equals(savedUsername) && senha.equals(savedSenha)) {
                        Toast.makeText(Login.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        // Continua com a lógica de login
                        // ...
                        Intent intent = new Intent(Login.this, MainMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
