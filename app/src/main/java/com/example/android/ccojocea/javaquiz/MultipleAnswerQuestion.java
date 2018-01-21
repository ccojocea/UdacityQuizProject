package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

public class MultipleAnswerQuestion extends Question {
    private int correctAnswer1, correctAnswer2;

    protected MultipleAnswerQuestion(int id, int correctAnswer1, int correctAnswer2) {
        super(id);
        this.correctAnswer1 = correctAnswer1;
        this.correctAnswer2 = correctAnswer2;
    }

    public boolean validateAnswer(boolean isAnswer1selected, boolean isAnswer2selected, boolean isAnswer3selected, boolean isAnswer4selected){
        return false;
    }
}
