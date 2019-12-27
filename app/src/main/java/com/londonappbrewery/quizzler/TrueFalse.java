package com.londonappbrewery.quizzler;

public class TrueFalse {
    private int questionID;
    private boolean questionAnswer;

    public TrueFalse(int questionID, boolean questionAnswer) {
        this.questionID = questionID;
        this.questionAnswer = questionAnswer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(boolean questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
