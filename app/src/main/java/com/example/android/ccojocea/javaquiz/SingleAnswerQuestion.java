package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

class SingleAnswerQuestion extends Question {
    private int correctAnswerNumber;

    public SingleAnswerQuestion(int id, int correctAnswer) {
        super(id);
        correctAnswerNumber = correctAnswer;
    }

    public boolean validateAnswer(int selectedAnswer) {
        return selectedAnswer==correctAnswerNumber;
    }
}
