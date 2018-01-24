package com.example.android.ccojocea.javaquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ccojo on 1/19/2018.
 */

public class MainActivity extends AppCompatActivity implements ConfirmSubmitDialogFragment.ConfirmDialogListener{

    //Declare variables
    String score = "";
    int countUp = -1;
    boolean isOver = false;
    boolean readArray;
    int[] unansweredQuestions;
    private boolean wasPaused = false;

    boolean etAnswer9;
    boolean etAnswer10;
    boolean etAnswer11;
    boolean etAnswer12;

    Quiz mQuiz;

    Timer t;
    TextView timerText;
    Button scoreButton;
    Button restartButton;
    Toast toastMessager;
    LinearLayout layoutMask;
    TextView scoreView;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    ScrollView scrollView;

    //Views related to answers:
    //Single Answer Views
    RadioGroup radioQuestion1;
    RadioGroup radioQuestion2;
    RadioGroup radioQuestion3;
    RadioGroup radioQuestion4;

    //Multiple Answer Views
    CheckBox checkBox51;
    CheckBox checkBox52;
    CheckBox checkBox53;
    CheckBox checkBox54;
    CheckBox checkBox61;
    CheckBox checkBox62;
    CheckBox checkBox63;
    CheckBox checkBox64;
    CheckBox checkBox71;
    CheckBox checkBox72;
    CheckBox checkBox73;
    CheckBox checkBox74;
    CheckBox checkBox81;
    CheckBox checkBox82;
    CheckBox checkBox83;
    CheckBox checkBox84;

    //EditText Views - initialised in onCreate
    EditText editText9;
    EditText editText10;
    EditText editText11;
    EditText editText12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mQuiz = createQuiz();

        layoutMask = findViewById(R.id.image_mask);
        restartButton = findViewById(R.id.btn_restart);
        scoreButton = findViewById(R.id.submit_score_check);
        scoreView = findViewById(R.id.main_score_text);
        textView9 = findViewById(R.id.text_view_q9);
        textView10 = findViewById(R.id.text_view_q10);
        textView11 = findViewById(R.id.text_view_q11);
        textView12 = findViewById(R.id.text_view_q12);
        radioQuestion1 = findViewById(R.id.q1_radio_group);
        radioQuestion2 = findViewById(R.id.q2_radio_group);
        radioQuestion3 = findViewById(R.id.q3_radio_group);
        radioQuestion4 = findViewById(R.id.q4_radio_group);
        //Find each checkbox
        checkBox51 = findViewById(R.id.q5_a1_checkbox);
        checkBox52 = findViewById(R.id.q5_a2_checkbox);
        checkBox53 = findViewById(R.id.q5_a3_checkbox);
        checkBox54 = findViewById(R.id.q5_a4_checkbox);
        checkBox61 = findViewById(R.id.q6_a1_checkbox);
        checkBox62 = findViewById(R.id.q6_a2_checkbox);
        checkBox63 = findViewById(R.id.q6_a3_checkbox);
        checkBox64 = findViewById(R.id.q6_a4_checkbox);
        checkBox71 = findViewById(R.id.q7_a1_checkbox);
        checkBox72 = findViewById(R.id.q7_a2_checkbox);
        checkBox73 = findViewById(R.id.q7_a3_checkbox);
        checkBox74 = findViewById(R.id.q7_a4_checkbox);
        checkBox81 = findViewById(R.id.q8_a1_checkbox);
        checkBox82 = findViewById(R.id.q8_a2_checkbox);
        checkBox83 = findViewById(R.id.q8_a3_checkbox);
        checkBox84 = findViewById(R.id.q8_a4_checkbox);
        scrollView = findViewById(R.id.base_scroll_view);

        //hide keyboard on rotation of screen
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editText9 = findViewById(R.id.q9_edit_text);
        editText10 = findViewById(R.id.q10_edit_text);
        editText11 = findViewById(R.id.q11_edit_text);
        editText12 = findViewById(R.id.q12_edit_text);
        editText9.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    editText9.clearFocus();
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getWindow().getDecorView().getApplicationWindowToken()), 0);
                }
                return false;
            }
        });
        editText10.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    editText10.clearFocus();
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getWindow().getDecorView().getApplicationWindowToken()), 0);
                }
                return false;
            }
        });
        editText11.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    editText11.clearFocus();
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getWindow().getDecorView().getApplicationWindowToken()), 0);
                }
                return false;
            }
        });
        editText12.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    editText12.clearFocus();
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getWindow().getDecorView().getApplicationWindowToken()), 0);
                }
                return false;
            }
        });

        //restore variables after orientation change
        if(savedInstanceState != null){
            isOver = savedInstanceState.getBoolean("isOver");
            countUp = savedInstanceState.getInt("countUp");
            score = savedInstanceState.getString("score");
            etAnswer9 = savedInstanceState.getBoolean("etAnswer9");
            etAnswer10 = savedInstanceState.getBoolean("etAnswer10");
            etAnswer11 = savedInstanceState.getBoolean("etAnswer11");
            etAnswer12 = savedInstanceState.getBoolean("etAnswer12");
            wasPaused = savedInstanceState.getBoolean("wasPaused");
        }


        setHeight();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();

        //stop timer during pauses
        if (t != null){
            t.cancel();
        }
        wasPaused = true;
    }

    @Override
    protected void onResume() {
        //timer start or end calls if quiz was finished
        if(isOver){
            endMethod();
            displayTimerText();
            if(etAnswer9){
                textView9.setText(getString(R.string.edited_answer_correct));
                editText9.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
            }
            if(etAnswer10){
                textView10.setText(getString(R.string.edited_answer_correct));
                editText10.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
            }
            if(etAnswer11){
                textView11.setText(getString(R.string.edited_answer_correct));
                editText11.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
            }
            if(etAnswer12){
                textView12.setText(getString(R.string.edited_answer_correct));
                editText12.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
            }
        } else {
            t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            countUp += 1;
                            displayTimerText();
                        }
                    });
                }
            }, 0, 1000);
        }

        super.onResume();
    }

    private void setHeight(){
        final View view = findViewById(R.id.top_element);
        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // Layout has happened here.
                        float topElementPosition = findViewById(R.id.top_element).getY();
                        float bottomElementPosition = scoreButton.getY();
                        int bottomElementHeight = scoreButton.getMeasuredHeight();
                        int height = (int)(bottomElementPosition-topElementPosition+bottomElementHeight);
                        layoutMask.setMinimumHeight(height);
                        // Don't forget to remove your listener when you are done with it.
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("countUp", countUp);
        outState.putString("score", score);
        outState.putBoolean("isOver", isOver);
        outState.putBoolean("etAnswer9", etAnswer9);
        outState.putBoolean("etAnswer10", etAnswer10);
        outState.putBoolean("etAnswer11", etAnswer11);
        outState.putBoolean("etAnswer12", etAnswer12);
        outState.putBoolean("wasPaused", wasPaused);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //clear focus from any EditText that might have it after rotation
        View view = getCurrentFocus();
        if (view != null){
            view.clearFocus();
        }
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
            view.clearFocus();
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * Method for sorting in what text format the timer should display
     */
    private void displayTimerText(){
        timerText = findViewById(R.id.main_timer_text);
        if(countUp >= 3600){
            String asTextHours = String.format("%02d:%02d:%02d", countUp/3600, (countUp % 3600)/60, (countUp % 3600)%60);
            timerText.setText(getString(R.string.timer_text) + " " + asTextHours);
        }else if(countUp >= 60){
            String asTextMinutes = String.format("%02d:%02d", countUp/60, countUp % 60);
            timerText.setText(getString(R.string.timer_text) + " " + asTextMinutes);
        } else {
            final String asTextSeconds = String.format("%d", countUp);
            timerText.setText(getString(R.string.timer_text) + " " + asTextSeconds);
        }
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
     * Method called when the check your answers button is pressed.
     * It should open a confirmation dialog
     * @param view
     */
    public void checkAnswers(View view) {
        //hide keyboard if it's open while the button is pressed
        View viewK = this.getCurrentFocus();
        if (viewK != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
                //do nothing :))
            }
        }

        String message;
        if (verifyCheckedAnswers() == 12) {
            message = getString(R.string.dialog_number_of_questions_tv_none);
        } else if (verifyCheckedAnswers() == 0) {
            message = getString(R.string.dialog_number_of_questions_tv_all);
        } else if (verifyCheckedAnswers() == 1) {
            message = getString(R.string.dialog_number_of_questions_tv_one);

            StringBuilder unsQ = new StringBuilder();
            for (int i = 0; i < 12; i++) {
                if(unansweredQuestions[i] != 0){
                    unsQ.append(unansweredQuestions[i]);
                }
            }

            message += "\n(The one you missed is question: " + unsQ.toString() + ")";

        } else {
            message = getString(R.string.dialog_number_of_questions_tv_some, verifyCheckedAnswers());

            StringBuilder unsQ = new StringBuilder();
            for (int i = 0; i < 12; i++) {
                if(unansweredQuestions[i] != 0){
                    unsQ.append(unansweredQuestions[i]);
                }
            }

            message += "\n(The questions you missed are: " + unsQ.toString() + ")";

        }



        ConfirmSubmitDialogFragment csdf = new ConfirmSubmitDialogFragment().newInstance(message);
        csdf.show(getSupportFragmentManager(), "Confirm Dialog");
    }

    /**
     * Check all answers and display the result in a Toast message.
     */
    public void checkYourAnswers(){
        readArray = true;
        float scoreFloat = checkSingleQuestionAnswers(readArray) + checkMultipleQuestionAnswers(readArray) + checkEditedQuestionAnswers();
        int scoreInt = (int) scoreFloat;
        String toastMessage;

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
            toastMessage = "At least you got one right!" + " Your score is: " + score +" out of 12!";
        } else {
            toastMessage = "Not even one correct answer? Did you even try?";
        }

        toastMessager = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        toastMessager.show();

        endMethod();

        //stop the timer
        if (t != null) {
            t.cancel();
        }

        scrollView.fullScroll(View.FOCUS_UP);
    }

    /**
     * This is called if the game has ended. It is also called each time the activity is recreated due to rotation if the flag for game end is true.
     */
    private void endMethod(){
        //show the score in a permanent way
        scoreView.setText(getString(R.string.score) + " " + score + "/" + "12");

        //make the mask layout visible
        //disable the Check your results button
        layoutMask.setVisibility(View.VISIBLE);
        scoreButton.setEnabled(false);
        isOver = true;
        restartButton.setVisibility(View.VISIBLE);

        //Running these each time to make sure correct answers are tagged.
        readArray = false;
        checkEditedQuestionAnswers();
        checkSingleQuestionAnswers(readArray);
        checkMultipleQuestionAnswers(readArray);
    }

    /**
     * Checks answers for single answer questions
     * @return score for the 4 questions with single answers - Possible: 0, 1, 2, 3, 4
     */
    private int checkSingleQuestionAnswers(boolean readArray){
        int score = 0;

        int answer1 = radioQuestion1.getCheckedRadioButtonId();
        int answer2 = radioQuestion2.getCheckedRadioButtonId();
        int answer3 = radioQuestion3.getCheckedRadioButtonId();
        int answer4 = radioQuestion4.getCheckedRadioButtonId();

        if(readArray){
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
    private float checkMultipleQuestionAnswers(boolean readArray){
        float score = 0.0f;

        checkBox52.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        checkBox54.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        checkBox61.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        checkBox63.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        checkBox73.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        checkBox74.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        checkBox82.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));
        checkBox83.setBackground(getResources().getDrawable(R.drawable.bg_select_correct_answer));

        if(readArray){
            score += mQuiz.multipleAnswerQuestions.get(0).validateAnswer(checkBox51.isChecked(), checkBox52.isChecked(), checkBox53.isChecked(), checkBox54.isChecked());
            score += mQuiz.multipleAnswerQuestions.get(1).validateAnswer(checkBox61.isChecked(), checkBox62.isChecked(), checkBox63.isChecked(), checkBox64.isChecked());
            score += mQuiz.multipleAnswerQuestions.get(2).validateAnswer(checkBox71.isChecked(), checkBox72.isChecked(), checkBox73.isChecked(), checkBox74.isChecked());
            score += mQuiz.multipleAnswerQuestions.get(3).validateAnswer(checkBox81.isChecked(), checkBox82.isChecked(), checkBox83.isChecked(), checkBox84.isChecked());
        }

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
            etAnswer9 = true;
            textView9.setText(getString(R.string.edited_answer_correct));
            editText9.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView9.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a90));
            editText9.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }
        if(mQuiz.editedAnswerQuestions.get(1).validateAnswer(editText10.getText().toString())){
            score++;
            etAnswer10 = true;
            textView10.setText(getString(R.string.edited_answer_correct));
            editText10.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView10.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a100));
            editText10.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }
        if(mQuiz.editedAnswerQuestions.get(2).validateAnswer(editText11.getText().toString())){
            score++;
            etAnswer11 = true;
            textView11.setText(getString(R.string.edited_answer_correct));
            editText11.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView11.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a110));
            editText11.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }
        if(mQuiz.editedAnswerQuestions.get(3).validateAnswer(editText12.getText().toString())){
            score++;
            etAnswer12 = true;
            textView12.setText(getString(R.string.edited_answer_correct));
            editText12.setTextColor(getResources().getColor(R.color.textCorrectAnswer));
        }else{
            textView12.setText(getString(R.string.edited_answer_incorrect) + " " + getString(R.string.a120));
            editText12.setTextColor(getResources().getColor(R.color.textWrongAnswer));
        }

        return score;
    }

    public void doNothingClick(View view){
        try{
            toastMessager.cancel();
        } catch (Exception e){
            //do nothing :)
        }
        toastMessager = Toast.makeText(this, getString(R.string.quiz_over), Toast.LENGTH_LONG);
        toastMessager.show();
    }

    /**
     * Check if the user has any question that he hasn't given any answer to.
     */
    private int verifyCheckedAnswers(){
        int numberOfQuestions = 0;
        unansweredQuestions = new int[12];
        for (int i = 0; i < 12; i++){
            unansweredQuestions[i] = 0;
        }

        //checking the 4 radiogroups to see if they are checked
        if(radioQuestion1.getCheckedRadioButtonId() == -1){
            numberOfQuestions++;
            unansweredQuestions[0] = 1;
        }
        if(radioQuestion2.getCheckedRadioButtonId() == -1){
            numberOfQuestions++;
            unansweredQuestions[1] = 2;
        }
        if(radioQuestion3.getCheckedRadioButtonId() == -1){
            numberOfQuestions++;
            unansweredQuestions[2] = 3;
        }
        if(radioQuestion4.getCheckedRadioButtonId() == -1){
            numberOfQuestions++;
            unansweredQuestions[3] = 4;
        }

        //checking all checkboxes, in groups of 4, to see if each question received an answer
        if(!checkBox51.isChecked() && !checkBox52.isChecked() && !checkBox53.isChecked() && !checkBox54.isChecked()){
            numberOfQuestions++;
            unansweredQuestions[4] = 5;
        }
        if(!checkBox61.isChecked() && !checkBox62.isChecked() && !checkBox63.isChecked() && !checkBox64.isChecked()){
            numberOfQuestions++;
            unansweredQuestions[5] = 6;
        }
        if(!checkBox71.isChecked() && !checkBox72.isChecked() && !checkBox73.isChecked() && !checkBox74.isChecked()){
            numberOfQuestions++;
            unansweredQuestions[6] = 7;
        }
        if(!checkBox81.isChecked() && !checkBox82.isChecked() && !checkBox83.isChecked() && !checkBox84.isChecked()){
            numberOfQuestions++;
            unansweredQuestions[7] = 8;
        }

        //checking all 4 edittexts to see if each question received an answer
        if(editText9.getText().length() == 0){
            numberOfQuestions++;
            unansweredQuestions[8] = 9;
        }
        if(editText10.getText().length() == 0){
            numberOfQuestions++;
            unansweredQuestions[9] = 10;
        }
        if(editText11.getText().length() == 0){
            numberOfQuestions++;
            unansweredQuestions[10] = 11;
        }
        if(editText12.getText().length() == 0){
            numberOfQuestions++;
            unansweredQuestions[11] = 12;
        }

        Log.i("THE STRING", unansweredQuestions.toString());

        return numberOfQuestions;
    }

    @Override
    public void submitResults() {
        checkYourAnswers();
    }

    /**
     * Called to recreate the activity from scratch after restart game is clicked (after ending the quiz)
     * @param view
     */
    public void restartGame(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
