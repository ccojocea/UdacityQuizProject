package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

public class EditedAnswerQuestion extends Question {
    private String correctAnswer;

    protected EditedAnswerQuestion(int id, String questionText, String correctAnswer) {
        super(id, questionText);
        this.correctAnswer = correctAnswer;
    }

    public boolean validateAnswer(String inputAnswer) {
        return correctAnswer.toLowerCase().equals(inputAnswer.toLowerCase());
    }
}
