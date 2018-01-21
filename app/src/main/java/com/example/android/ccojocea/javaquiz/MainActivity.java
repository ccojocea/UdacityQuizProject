package com.example.android.ccojocea.javaquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by ccojo on 1/19/2018.
 */

public class MainActivity extends AppCompatActivity {

    //Declare variables
    Quiz mQuiz;
    RadioGroup radioQuestion1;
    RadioGroup radioQuestion2;
    RadioGroup radioQuestion3;
    RadioGroup radioQuestion4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        radioQuestion1 = findViewById(R.id.q1_radio_group);
        radioQuestion2 = findViewById(R.id.q2_radio_group);
        radioQuestion3 = findViewById(R.id.q3_radio_group);
        radioQuestion4 = findViewById(R.id.q4_radio_group);

        mQuiz = createQuiz();
    }

    /**
     * Create a new Quiz
     * @return a new Quiz object with all question ids and correct answers
     */
    private Quiz createQuiz(){
        Quiz newQuiz = new Quiz();

        //add the correct answer ids for single answer questions
        newQuiz.singleAnswerQuestions.add(new SingleAnswerQuestion(1, 3));
        newQuiz.singleAnswerQuestions.add(new SingleAnswerQuestion(2, 2));
        newQuiz.singleAnswerQuestions.add(new SingleAnswerQuestion(3, 4));
        newQuiz.singleAnswerQuestions.add(new SingleAnswerQuestion(4, 1));

        //add the correct answer ids for multiple answer questions
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(1, false, true, false, true));
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(2, true, false, true, false));
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(3, false, false, true, true));
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(4, false, true, true, false));

        //add the correct answer Strings for the EditText answer questions
        newQuiz.editedAnswerQuestions.add(new EditedAnswerQuestion(1, getString(R.string.a90)));
        newQuiz.editedAnswerQuestions.add(new EditedAnswerQuestion(2, getString(R.string.a100)));
        newQuiz.editedAnswerQuestions.add(new EditedAnswerQuestion(3, getString(R.string.a110)));
        newQuiz.editedAnswerQuestions.add(new EditedAnswerQuestion(4, getString(R.string.a120)));

        return newQuiz;
    }

    /**
     * Check all answers and display the result in a Toast message.
     */
    public void checkAnswers(View view){
        float scoreFloat = checkSingleQuestionAnswers() + checkMultipleQuestionAnswers() + checkEditedQuestionAnswers();
        int scoreInt = (int) scoreFloat;
        String score = "";
        String toastMessage = "";

        if (scoreFloat == Math.round(scoreFloat)){
            score += scoreInt;
        } else {
            score += scoreFloat;
        }

        if(scoreFloat == 12){
            toastMessage = "Amazing! You've got a perfect 12 out of 12 score!";
        } else if (scoreFloat > 8){
            toastMessage = "Congratulations! You've got most of the questions right!" + " Your score is: " + score +" out of 12!";
        } else if (scoreFloat > 4){
            toastMessage = "Not a great achievement but better than nothing!" + " Your score is: " + score +" out of 12!";
        } else if (scoreFloat > 1){
            toastMessage = "At least you got some right!" + " Your score is: " + score +" out of 12!";
        } else if (scoreFloat > 0) {
            toastMessage = "At least you got one right!" + " Your score is: " + score +" out of 12!";;
        } else {
            toastMessage = "Not even one correct answer? Did you even try?";
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
    }

    /**
     * Checks answers for single answer questions
     * @return score for the 4 questions with single answers - Possible: 0, 1, 2, 3, 4
     */
    private int checkSingleQuestionAnswers(){
        int score = 0;

        int answer1 = radioQuestion1.getCheckedRadioButtonId();
        int answer2 = radioQuestion2.getCheckedRadioButtonId();
        int answer3 = radioQuestion3.getCheckedRadioButtonId();
        int answer4 = radioQuestion4.getCheckedRadioButtonId();

        RadioButton radioButton1 = radioQuestion1.findViewById(answer1);
        int index1 = radioQuestion1.indexOfChild(radioButton1);
        Log.i("First question: ", "" + index1);
        if(mQuiz.singleAnswerQuestions.get(0).validateAnswer(++index1)){
            score++;
        }

        RadioButton radioButton2 = radioQuestion2.findViewById(answer2);
        int index2 = radioQuestion2.indexOfChild(radioButton2);
        Log.i("Second question: ", "" + index2);
        if(mQuiz.singleAnswerQuestions.get(1).validateAnswer(++index2)){
            score++;
        }

        RadioButton radioButton3 = radioQuestion3.findViewById(answer3);
        int index3 = radioQuestion3.indexOfChild(radioButton3);
        Log.i("Third question: ", "" + index3);
        if(mQuiz.singleAnswerQuestions.get(2).validateAnswer(++index3)){
            score++;
        }

        RadioButton radioButton4 = radioQuestion4.findViewById(answer4);
        int index4 = radioQuestion4.indexOfChild(radioButton4);
        Log.i("Fourth question: ", "" + index4);
        if(mQuiz.singleAnswerQuestions.get(3).validateAnswer(++index4)){
            score++;
        }

        return score;
    }

    /**
     * Checks answers for multiple answer questions
     * @return score for the 4 questions with multiple answers - Possible: 0, 1, 2, 3, 4, 0.5, 1.5, 2.5, 3.5
     * Max value per question is 1, can still get 0.5 points for 1 correct answer!
     */
    private float checkMultipleQuestionAnswers(){
        float score = 0.0f;

        CheckBox checkBox51 = findViewById(R.id.q5_a1_checkbox);
        CheckBox checkBox52 = findViewById(R.id.q5_a2_checkbox);
        CheckBox checkBox53 = findViewById(R.id.q5_a3_checkbox);
        CheckBox checkBox54 = findViewById(R.id.q5_a4_checkbox);
        CheckBox checkBox61 = findViewById(R.id.q6_a1_checkbox);
        CheckBox checkBox62 = findViewById(R.id.q6_a2_checkbox);
        CheckBox checkBox63 = findViewById(R.id.q6_a3_checkbox);
        CheckBox checkBox64 = findViewById(R.id.q6_a4_checkbox);
        CheckBox checkBox71 = findViewById(R.id.q7_a1_checkbox);
        CheckBox checkBox72 = findViewById(R.id.q7_a2_checkbox);
        CheckBox checkBox73 = findViewById(R.id.q7_a3_checkbox);
        CheckBox checkBox74 = findViewById(R.id.q7_a4_checkbox);
        CheckBox checkBox81 = findViewById(R.id.q8_a1_checkbox);
        CheckBox checkBox82 = findViewById(R.id.q8_a2_checkbox);
        CheckBox checkBox83 = findViewById(R.id.q8_a3_checkbox);
        CheckBox checkBox84 = findViewById(R.id.q8_a4_checkbox);

        score += mQuiz.multipleAnswerQuestions.get(0).validateAnswer(checkBox51.isChecked(), checkBox52.isChecked(), checkBox53.isChecked(), checkBox54.isChecked());
        score += mQuiz.multipleAnswerQuestions.get(1).validateAnswer(checkBox61.isChecked(), checkBox62.isChecked(), checkBox63.isChecked(), checkBox64.isChecked());
        score += mQuiz.multipleAnswerQuestions.get(2).validateAnswer(checkBox71.isChecked(), checkBox72.isChecked(), checkBox73.isChecked(), checkBox74.isChecked());
        score += mQuiz.multipleAnswerQuestions.get(3).validateAnswer(checkBox81.isChecked(), checkBox82.isChecked(), checkBox83.isChecked(), checkBox84.isChecked());

        return score;
    }

    /**
     * Checks input text question answers
     * @return score for the 4 questions. Possible: 0, 1, 2, 3, 4
     */
    private int checkEditedQuestionAnswers(){
        int score = 0;

        EditText editText9 = findViewById(R.id.q9_edit_text);
        EditText editText10 = findViewById(R.id.q10_edit_text);
        EditText editText11 = findViewById(R.id.q11_edit_text);
        EditText editText12 = findViewById(R.id.q12_edit_text);

        if(mQuiz.editedAnswerQuestions.get(0).validateAnswer(editText9.getText().toString())){
            score++;
        }
        if(mQuiz.editedAnswerQuestions.get(1).validateAnswer(editText10.getText().toString())){
            score++;
        }
        if(mQuiz.editedAnswerQuestions.get(2).validateAnswer(editText11.getText().toString())){
            score++;
        }
        if(mQuiz.editedAnswerQuestions.get(3).validateAnswer(editText12.getText().toString())){
            score++;
        }
        return score;
    }
}
