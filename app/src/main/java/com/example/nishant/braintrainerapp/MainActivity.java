package com.example.nishant.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView timerTextView;
    TextView scoreTextView;
    TextView questionTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    TextView resultTextView;
    View gridlayout;
    int correctAnswerLocation;
    ArrayList<Integer> answers;
    int incorrectAnswer;
    int questionCounter;
    int scoreCounter;
    boolean isTimerOn;
    CountDownTimer timer;


    public void playAgain(View view) {
        playAgain.setVisibility(View.INVISIBLE);
        gameStart(view);

    }

    public void nextQuestion() {
            answers = new ArrayList<Integer>();
            Random rand = new Random();
            int qnum0 = rand.nextInt(50);
            int qnum1 = rand.nextInt(50);

            questionTextView.setText(Integer.toString(qnum0) + " + " + Integer.toString(qnum1));

            correctAnswerLocation = rand.nextInt(4);

            for (int i = 0; i < 4; i++) {
                if (i == correctAnswerLocation) {
                    answers.add(qnum0 + qnum1);
                } else {
                    incorrectAnswer = rand.nextInt(100);
                    while (incorrectAnswer == qnum0 + qnum1) {
                        incorrectAnswer = rand.nextInt(100);
                    }
                    answers.add(incorrectAnswer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }




    public void checkAnswer(View v) {
        if(isTimerOn) {
            questionCounter++;
            if (v.getTag().toString().equalsIgnoreCase(Integer.toString(correctAnswerLocation))) {
                resultTextView.setVisibility(v.VISIBLE);
                Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show();
                scoreCounter++;
                scoreTextView.setText(Integer.toString(scoreCounter) + "/" + Integer.toString(questionCounter));
            } else {
                resultTextView.setVisibility(v.VISIBLE);
                Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show();
                scoreTextView.setText(Integer.toString(scoreCounter) + "/" + Integer.toString(questionCounter));
            }
            resultTextView.setVisibility(v.INVISIBLE);
            nextQuestion();
        }
    }



    public void gameStart(View view) {
        questionCounter = 0;
        scoreCounter = 0;

        goButton.setVisibility(View.INVISIBLE);

        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(Integer.toString(scoreCounter) +" / " + Integer.toString(questionCounter));
        questionTextView.setVisibility(View.VISIBLE);
        gridlayout.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        isTimerOn = true;

        //Start timer
        timer = new CountDownTimer(10000 + 4, 1000) {

            @Override
            public void onTick(long l) {
                if((l/1000) > 9 ) {
                    timerTextView.setText((String.valueOf(l / 1000) + "s"));
                }
                else
                {
                    timerTextView.setText(("0" + String.valueOf(l / 1000) + "s"));
                }
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00s");
                //Stop generating further questions.
                isTimerOn = false;
                //Update the final score.
                resultTextView.setVisibility(View.VISIBLE);
                double score = scoreCounter;
                double scorePercent = (Math.round((score/questionCounter)*100));
                scorePercent = (int)scorePercent;
                resultTextView.setText("Your Score: " + scorePercent + "%");
                //Option to reStart the game.
                playAgain.setVisibility(View.VISIBLE);
                timer.cancel();
            }
        }.start();

        nextQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button) findViewById(R.id.goButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.socreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        gridlayout = (View) findViewById(R.id.gridlayout);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.playAgainButton);
        playAgain.setVisibility(View.INVISIBLE);

    }
}
