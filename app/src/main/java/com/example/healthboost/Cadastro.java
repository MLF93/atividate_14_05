package com.example.healthboost;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro extends AppCompatActivity {

    //Declarar as variáveis
    EditText user, email, passorwd;
    Button send;

    TextView txtResultado;

    // Declarar o arquivo de preferência
    public static final String MyPreferences = "Arquivo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        user= findViewById(R.id.editTextUsername);
        email= findViewById(R.id.editTextEmail);
        passorwd= findViewById(R.id.editTextPassword);
        send=findViewById(R.id.buttonCadastrar);


        //Clase SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(MyPreferences, 0);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Declaração de variáveis locais
                String usuarioLocal = user.getText().toString();
                String emailLocal = email.getText().toString();
                String senhaLocal = passorwd.getText().toString();

                // Declarção do editor
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //Fazer a persistência dos dados
                editor.putString("usuario", usuarioLocal);
                editor.putString("email", emailLocal);
                editor.putString("senha", senhaLocal);

                // Confirmar a persistencia
                editor.commit();

                //Notificação no app
                Toast.makeText(Cadastro.this, "Dados cadastrados!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Cadastro.this, MainMenu.class);
                startActivity(intent);

            }
        });

        // Recuperar dados

        //RECUPERAR OS DADOS SALVOS


    }
}
