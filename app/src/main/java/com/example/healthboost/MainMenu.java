package com.example.healthboost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    TextView textViewUsername;
    public static final String MyPreferences = "Arquivo";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        textViewUsername = findViewById(R.id.textViewUsername);

        sharedPreferences = getSharedPreferences(MyPreferences, 0);

        // Recupera o nome de usuário das preferências compartilhadas
        String username = sharedPreferences.getString("usuario", "");

        // Exibe o nome de usuário no TextView
        textViewUsername.setText(username);


        ImageView imageView = findViewById(R.id.water_icon_id);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, WaterMenuActivity.class);
                startActivity(intent);
            }
        });

    }

}