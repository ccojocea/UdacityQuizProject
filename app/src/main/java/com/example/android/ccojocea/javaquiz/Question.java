package com.example.android.ccojocea.javaquiz;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ccojo on 1/20/2018.
 */

public class Question{
    private int id;
    private String questionText;

    protected Question (int id, String questionText){
        this.id = id;
        this.questionText = questionText;
    }
}
