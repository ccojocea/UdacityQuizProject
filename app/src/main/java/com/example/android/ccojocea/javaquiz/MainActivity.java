package com.example.android.ccojocea.javaquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ccojo on 1/19/2018.
 */

public class MainActivity extends AppCompatActivity {

    //Declare variables
    Quiz mQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

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
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(1, 2, 4));
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(2, 1, 3));
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(3, 3, 4));
        newQuiz.multipleAnswerQuestions.add(new MultipleAnswerQuestion(4, 2, 3));

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
    private void checkAnswers(View view){
        float score = checkSingleQuestionAnswers() + checkMultipleQuestionAnswers() + checkEditedQuestionAnswers();
        String toastMessage = "";

        if (score % 1 == 0){
            score = (int) score;
        }

        if(score == 12){
            toastMessage = "Amazing! You've got a perfect score!";
        } else if (score > 8){
            toastMessage = "Congratulations! You've got most of the questions right!" + " Your score is: " + score +" out of 12!";
        } else if (score > 4){
            toastMessage = "Not a great achievement but better than nothing!" + " Your score is: " + score +" out of 12!";
        } else if (score > 0){
            toastMessage = "At least you got something right!" + " Your score is: " + score +" out of 12!";
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
        return 0;
    }

    /**
     * Checks answers for multiple answer questions
     * @return score for the 4 questions with multiple answers - Possible: 0, 1, 2, 3, 4, 0.5, 1.5, 2.5, 3.5
     * Max value per question is 1, can still get 0.5 points for 1 correct answer!
     */
    private float checkMultipleQuestionAnswers(){
        return 0;
    }

    /**
     * Checks input text question answers
     * @return score for the 4 questions. Possible: 0, 1, 2, 3, 4
     */
    private int checkEditedQuestionAnswers(){
        return 0;
    }
}
