package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView scoreText, nameText;
    Button retryButton, finishButton;
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreText = findViewById(R.id.scoreText);
        nameText = findViewById(R.id.nameText);
        retryButton = findViewById(R.id.retryButton);
        finishButton = findViewById(R.id.finishButton);

        int score = getIntent().getIntExtra("score", 0);
        userName = getIntent().getStringExtra("username");

        nameText.setText("Hello, " + userName + "!");
        scoreText.setText("Your Score: " + score);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
                finish();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, WelcomeActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
                finish();
            }
        });
    }
}


