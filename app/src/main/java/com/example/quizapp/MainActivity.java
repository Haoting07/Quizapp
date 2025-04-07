package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView questionText;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3;
    Button submitButton;
    ProgressBar progressBar;

    int currentQuestionIndex = 0;
    int score = 0;

    String[] questions = {
            "What is 2 + 2?",
            "What is the capital of France?",
            "Which planet is known as the Red Planet?"
    };

    String[][] options = {
            {"3", "4", "5"},
            {"London", "Paris", "Rome"},
            {"Earth", "Mars", "Jupiter"}
    };

    int[] correctAnswers = {1, 1, 1}; // 每题正确选项下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);

        loadQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedButton = findViewById(selectedId);
                int selectedIndex = radioGroup.indexOfChild(selectedButton);

                // 清除背景颜色
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    radioGroup.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }

                if (selectedIndex == correctAnswers[currentQuestionIndex]) {
                    selectedButton.setBackgroundColor(Color.GREEN);
                    score++;
                } else {
                    selectedButton.setBackgroundColor(Color.RED);
                    RadioButton correctButton = (RadioButton) radioGroup.getChildAt(correctAnswers[currentQuestionIndex]);
                    correctButton.setBackgroundColor(Color.GREEN);
                }

                // 延迟加载下一题
                submitButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentQuestionIndex++;
                        if (currentQuestionIndex < questions.length) {
                            loadQuestion();
                        } else {
                            goToResult();
                        }
                    }
                }, 1000);
            }
        });
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        radioGroup.clearCheck();

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
        }

        progressBar.setProgress(currentQuestionIndex);
    }

    private void goToResult() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
}

