package com.example.android.ccojocea.javaquiz;

import java.util.ArrayList;

/**
 * Created by ccojo on 1/20/2018.
 */

class Quiz {
    public ArrayList<SingleAnswerQuestion> singleAnswerQuestions;
    public ArrayList<MultipleAnswerQuestion> multipleAnswerQuestions;
    public ArrayList<EditedAnswerQuestion> editedAnswerQuestions;

    public Quiz(){
        singleAnswerQuestions = new ArrayList<>();
        multipleAnswerQuestions = new ArrayList<>();
        editedAnswerQuestions = new ArrayList<>();
    }
}
