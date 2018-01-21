package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

public class MultipleAnswerQuestion extends Question {
    private String[] answers = new String[4];
    private int correctAnswer1, correctAnswer2;

    protected MultipleAnswerQuestion(int id, String questionText, int correctAnswer1, int correctAnswer2) {
        super(id, questionText);
        this.correctAnswer1 = correctAnswer1;
        this.correctAnswer2 = correctAnswer2;
    }

    public boolean validateAnswer(boolean isAnswer1selected, boolean isAnswer2selected, boolean isAnswer3selected, boolean isAnswer4selected){
        return false;
    }
}
