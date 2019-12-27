package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button trueButton, falseButton;
    TextView questionTextView, scoreTextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionTextView = findViewById(R.id.question_text_view);

        questionID = mQuestionBank[questionNumber].getQuestionID();
        questionTextView.setText(mQuestionBank[questionNumber].getQuestionID());

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
        questionNumber += 1;
        questionTextView.setText(mQuestionBank[questionNumber].getQuestionID());
    }

    public void CheckAnswer(boolean userInput) {
        if ( userInput == mQuestionBank[questionNumber].getQuestionAnswer() ) {
            Log.d("Quiz","Answered Correctly");
            score += 1;
            //scoreTextView
        } else {
            Log.d("Quiz","Answered Inorrectly");
        }
    }

} // end of Class
