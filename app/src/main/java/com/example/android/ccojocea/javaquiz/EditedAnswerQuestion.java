package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

class EditedAnswerQuestion extends Question {
    private String correctAnswer;

    EditedAnswerQuestion(int id, String correctAnswer) {
        super(id);
        this.correctAnswer = correctAnswer;
    }

    public boolean validateAnswer(String inputAnswer) {
        return correctAnswer.toLowerCase().equals(inputAnswer.toLowerCase());
    }
}
