package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

public class SingleAnswerQuestion extends Question {
    private String answer;
    private int correctAnswerNumber;

    public SingleAnswerQuestion(int id, String questionText, int correctAnswer) {
        super(id, questionText);
        correctAnswerNumber = correctAnswer;
    }

    public boolean validateAnswer(int selectedAnswer) {
        return selectedAnswer==correctAnswerNumber;
    }
}
