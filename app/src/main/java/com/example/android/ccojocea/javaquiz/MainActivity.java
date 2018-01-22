package com.example.android.ccojocea.javaquiz;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ccojo on 1/19/2018.
 */

public class MainActivity extends AppCompatActivity {

    //Declare variables
    Quiz mQuiz;
    TextView timerText;
    private int countUp = 0;
    Timer t;

//    long startTime;
//    long countUp;
//    Chronometer stopWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //hide keyboard on rotation of screen
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //this shit ain't fucking working
        View view = getCurrentFocus();
        EditText et9 = findViewById(R.id.q9_edit_text);
        et9.setFocusable(false);
        et9.setFocusableInTouchMode(false);
        et9.setFocusable(true);
        et9.setFocusableInTouchMode(true);


//        if (view != null && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")){
//            view.clearFocus();
//            Button button = findViewById(R.id.submit_score_check);
//            button.requestFocus();
//        }

//        stopWatch = findViewById(R.id.chrono);
//        startTime = SystemClock.elapsedRealtime();
//
//        timerText = findViewById(R.id.main_timer_text);
//        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//            @Override
//            public void onChronometerTick(Chronometer chronometer) {
//                countUp = (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000;
//                String asText = (countUp / 60) + ":" + (countUp % 60);
//                timerText.setText(asText);
//            }
//        });
//        stopWatch.start();

        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerText = findViewById(R.id.main_timer_text);
                        if(countUp >= 3600){
                            String asTextHours = String.format("%02d:%02d:%02d", countUp/3600, (countUp % 3600)/60, (countUp % 3600)%60);
                            timerText.setText("Time: " + asTextHours);
                        }else if(countUp >= 60){
                            String asTextMinutes = String.format("%02d:%02d", countUp/60, countUp % 60);
                            timerText.setText("Time: " + asTextMinutes);
                        } else {
                            String asTextSeconds = String.format("%02d", countUp);
                            timerText.setText("Time: " + asTextSeconds);
                        }
                        countUp += 1;
                    }
                });
            }
        }, 0, 1000);

        mQuiz = createQuiz();
    }

    //touch anywhere on screen to close the keyboard
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int screenCoordinates[] = new int[2];
            view.getLocationOnScreen(screenCoordinates);
            float x = ev.getRawX() + view.getLeft() - screenCoordinates[0];
            float y = ev.getRawY() + view.getTop() - screenCoordinates[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
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
        //stop the timer
        t.cancel();
//        stopWatch.stop();
        //show the score in a permanent way
        TextView scoreView = findViewById(R.id.main_score_text);
        scoreView.setText("Score: " + score + "/" + "12");
    }

    /**
     * Checks answers for single answer questions
     * @return score for the 4 questions with single answers - Possible: 0, 1, 2, 3, 4
     */
    private int checkSingleQuestionAnswers(){
        int score = 0;

        RadioGroup radioQuestion1 = findViewById(R.id.q1_radio_group);
        RadioGroup radioQuestion2 = findViewById(R.id.q2_radio_group);
        RadioGroup radioQuestion3 = findViewById(R.id.q3_radio_group);
        RadioGroup radioQuestion4 = findViewById(R.id.q4_radio_group);

        int answer1 = radioQuestion1.getCheckedRadioButtonId();
        int answer2 = radioQuestion2.getCheckedRadioButtonId();
        int answer3 = radioQuestion3.getCheckedRadioButtonId();
        int answer4 = radioQuestion4.getCheckedRadioButtonId();

        RadioButton radioButton1 = radioQuestion1.findViewById(answer1);
        int index1 = radioQuestion1.indexOfChild(radioButton1);
        if(mQuiz.singleAnswerQuestions.get(0).validateAnswer(++index1)){
            score++;
        }

        RadioButton radioButton2 = radioQuestion2.findViewById(answer2);
        int index2 = radioQuestion2.indexOfChild(radioButton2);
        if(mQuiz.singleAnswerQuestions.get(1).validateAnswer(++index2)){
            score++;
        }

        RadioButton radioButton3 = radioQuestion3.findViewById(answer3);
        int index3 = radioQuestion3.indexOfChild(radioButton3);
        if(mQuiz.singleAnswerQuestions.get(2).validateAnswer(++index3)){
            score++;
        }

        RadioButton radioButton4 = radioQuestion4.findViewById(answer4);
        int index4 = radioQuestion4.indexOfChild(radioButton4);
        if(mQuiz.singleAnswerQuestions.get(3).validateAnswer(++index4)){
            score++;
        }

        //change color of correct radio button answers to green
        RadioButton correctRadioButton1 = findViewById(R.id.q1_a3_radio_button);
        correctRadioButton1.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        RadioButton correctRadioButton2 = findViewById(R.id.q2_a2_radio_button);
        correctRadioButton2.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        RadioButton correctRadioButton3 = findViewById(R.id.q3_a4_radio_button);
        correctRadioButton3.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        RadioButton correctRadioButton4 = findViewById(R.id.q4_a1_radio_button);
        correctRadioButton4.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));

        return score;
    }

    /**
     * Checks answers for multiple answer questions
     * @return score for the 4 questions with multiple answers - Possible: 0, 1, 2, 3, 4, 0.5, 1.5, 2.5, 3.5
     * Max value per question is 1, can still get 0.5 points for 1 correct answer!
     */
    private float checkMultipleQuestionAnswers(){
        float score = 0.0f;

        //Find each checkbox, change color of correct answer checkboxes to reflect their state
        CheckBox checkBox51 = findViewById(R.id.q5_a1_checkbox);
        CheckBox checkBox52 = findViewById(R.id.q5_a2_checkbox);
        checkBox52.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        CheckBox checkBox53 = findViewById(R.id.q5_a3_checkbox);
        CheckBox checkBox54 = findViewById(R.id.q5_a4_checkbox);
        checkBox54.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        CheckBox checkBox61 = findViewById(R.id.q6_a1_checkbox);
        checkBox61.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        CheckBox checkBox62 = findViewById(R.id.q6_a2_checkbox);
        CheckBox checkBox63 = findViewById(R.id.q6_a3_checkbox);
        checkBox63.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        CheckBox checkBox64 = findViewById(R.id.q6_a4_checkbox);
        CheckBox checkBox71 = findViewById(R.id.q7_a1_checkbox);
        CheckBox checkBox72 = findViewById(R.id.q7_a2_checkbox);
        CheckBox checkBox73 = findViewById(R.id.q7_a3_checkbox);
        checkBox73.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        CheckBox checkBox74 = findViewById(R.id.q7_a4_checkbox);
        checkBox74.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        CheckBox checkBox81 = findViewById(R.id.q8_a1_checkbox);
        CheckBox checkBox82 = findViewById(R.id.q8_a2_checkbox);
        checkBox82.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        CheckBox checkBox83 = findViewById(R.id.q8_a3_checkbox);
        checkBox83.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
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

        TextView textView9 = findViewById(R.id.text_view_q9);
        TextView textView10 = findViewById(R.id.text_view_q10);
        TextView textView11 = findViewById(R.id.text_view_q11);
        TextView textView12 = findViewById(R.id.text_view_q12);
        textView9.setVisibility(View.VISIBLE);
        textView10.setVisibility(View.VISIBLE);
        textView11.setVisibility(View.VISIBLE);
        textView12.setVisibility(View.VISIBLE);

        if(mQuiz.editedAnswerQuestions.get(0).validateAnswer(editText9.getText().toString())){
            score++;
            textView9.setText(getString(R.string.edited_answer_correct));
            editText9.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView9.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a90));
            editText9.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }
        if(mQuiz.editedAnswerQuestions.get(1).validateAnswer(editText10.getText().toString())){
            score++;
            textView10.setText(getString(R.string.edited_answer_correct));
            editText10.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView10.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a100));
            editText10.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }
        if(mQuiz.editedAnswerQuestions.get(2).validateAnswer(editText11.getText().toString())){
            score++;
            textView11.setText(getString(R.string.edited_answer_correct));
            editText11.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView11.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a110));
            editText11.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }
        if(mQuiz.editedAnswerQuestions.get(3).validateAnswer(editText12.getText().toString())){
            score++;
            textView12.setText(getString(R.string.edited_answer_correct));
            editText12.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView12.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a120));
            editText12.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }

        return score;
    }
}
