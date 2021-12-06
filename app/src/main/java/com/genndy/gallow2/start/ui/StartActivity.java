package com.genndy.gallow2.start.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.genndy.gallow2.R;
import com.genndy.gallow2.game.ui.GameActivity;

public class StartActivity extends AppCompatActivity {

    private Button startAncientAnimalButton;
    private Button startAncientAntiquityButton;
    private Button startTechnologyButton;
    private Button startAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startAncientAnimalButton = findViewById(R.id.start_game_ancient_animals_button);
        startAncientAntiquityButton = findViewById(R.id.start_game_antiquity_button);
        startTechnologyButton = findViewById(R.id.start_game_technology_button);
        startAllButton = findViewById(R.id.перемешать);

        startAncientAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                intent.putExtra("game_theme", "ancient_animals");

                startActivity(intent);
            }
        });

        startAncientAntiquityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                intent.putExtra("game_theme", "ancient_antiquity");

                startActivity(intent);
            }
        });

        startTechnologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                intent.putExtra("game_theme", "technology");

                startActivity(intent);
            }
        });

        startAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                intent.putExtra("game_theme", "all");

                startActivity(intent);
            }
        });
    }
}