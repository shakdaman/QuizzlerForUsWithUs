package com.foruswithus.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class MainActivity extends Activity {



    // TODO: Declare member variables here:
    Button trueButton, falseButton;
    TextView questionTextView, scoreTextView, questionCounter;
    ProgressBar progressBar;
    int questionNumber, questionID, score;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int INCREMENT_SCORE = (int) Math.ceil (100.0 / mQuestionBank.length );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionTextView = findViewById(R.id.question_text_view);
        scoreTextView = findViewById(R.id.score);
        questionCounter = findViewById(R.id.questionCounterTextView);
        progressBar = findViewById(R.id.progress_bar);

        questionID = mQuestionBank[questionNumber].getQuestionID();
        questionTextView.setText(mQuestionBank[questionNumber].getQuestionID());
        questionCounter.setText( "Question " + (questionNumber + 1) + " of " + mQuestionBank.length );
        scoreTextView.setText("Score " + score + "/" + mQuestionBank.length);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( v.getId() == trueButton.getId() ) {
                    Log.d("QuizApp", "True button pressed.");
                    CheckAnswer(true);
                } else if ( v.getId() == falseButton.getId() ) {
                    Log.d("QuizApp", "False button pressed.");
                    CheckAnswer(false);
                } // end of IF
                updateTextView();
            }
        }; // end of OnClickListener

        trueButton.setOnClickListener(onClickListener);
        falseButton.setOnClickListener(onClickListener);

    } // end of onCreate()

    public void updateTextView() {
        questionNumber = ( questionNumber + 1 ) % mQuestionBank.length;
        Log.d("QuizApp", "Question =" + questionNumber);

        if ( questionNumber == 0 ) {
            DecimalFormat decimalFormat = new DecimalFormat("00.00");
            double compPercentage = (double)score / (double)mQuestionBank.length  * 100;
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.complete);
            alertDialog.setCancelable(false);
            alertDialog.setMessage(score + " out of " + mQuestionBank.length + "\nFinal score: " + decimalFormat.format(compPercentage) + "%" );
            alertDialog.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.show();
        }

        questionTextView.setText(mQuestionBank[questionNumber].getQuestionID());
        questionCounter.setText( (questionNumber + 1) + " of " + mQuestionBank.length );
        progressBar.incrementProgressBy(INCREMENT_SCORE);
    }

    public void CheckAnswer(boolean userInput) {
        if ( userInput == mQuestionBank[questionNumber].getQuestionAnswer() ) {
            Log.d("Quiz","Answered Correctly");
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            score += 1;
            scoreTextView.setText("Score " + score + "/" + mQuestionBank.length);

        } else {
            Log.d("Quiz","Answered Inorrectly");
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }

} // end of Class
