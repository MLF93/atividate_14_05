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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        textViewUsername = findViewById(R.id.textViewUsername);

        // Para salvar o nome de usuário
        String username = getIntent().getStringExtra("username");
        SharedPreferences.Editor editor = getSharedPreferences("Session", MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.apply();

        // Exibe o nome de usuário no TextView
        textViewUsername.setText(username);


        ImageView water_image = findViewById(R.id.water_icon_id);
        water_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, WaterMenuActivity.class);
                startActivity(intent);
            }
        });

        ImageView food_image = findViewById(R.id.food_icon_id);
        food_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, FoodMenu.class);
                startActivity(intent);
            }
        });

        ImageView sleep_image = findViewById(R.id.sleep_icon_id);
        sleep_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, SleepMenu.class);
                startActivity(intent);
            }
        });

        ImageView exercise_image = findViewById(R.id.exercise_icon_id);
        exercise_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, ExerciseMenu.class);
                startActivity(intent);
            }
        });

        ImageView meds_image = findViewById(R.id.meds_icon_id);
        meds_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MedsMenu.class);
                startActivity(intent);
            }
        });

    }

}