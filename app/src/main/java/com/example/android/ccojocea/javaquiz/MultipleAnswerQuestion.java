package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

public class MultipleAnswerQuestion extends Question {
    private boolean isAnswer1Correct, isAnswer2Correct, isAnswer3Correct, isAnswer4Correct;

    protected MultipleAnswerQuestion(int id, boolean answer1, boolean answer2, boolean answer3, boolean answer4) {
        super(id);
        this.isAnswer1Correct = answer1;
        this.isAnswer2Correct = answer2;
        this.isAnswer3Correct = answer3;
        this.isAnswer4Correct = answer4;
    }

    /**
     * Score validation and calculation for multiple answer questions.
     * @return score after checking correct and wrong answers. Value will be positive, however wrong answers will be taken out of correct score.
     */
    public float validateAnswer(boolean isAnswer1selected, boolean isAnswer2selected, boolean isAnswer3selected, boolean isAnswer4selected){
        float score = 0.0f;

        if (isAnswer1Correct == isAnswer1selected & isAnswer1Correct == true){
            score += 0.5;
        }
        if (isAnswer2Correct == isAnswer2selected & isAnswer2Correct == true){
            score += 0.5;
        }
        if (isAnswer3Correct == isAnswer3selected & isAnswer3Correct == true){
            score += 0.5;
        }
        if (isAnswer4Correct == isAnswer4selected & isAnswer4Correct == true){
            score += 0.5;
        }
        if (isAnswer1Correct != isAnswer1selected & isAnswer1Correct == false){
            if(score > 0.0){
                score -= 0.5;
            }
        }
        if (isAnswer2Correct != isAnswer2selected & isAnswer2Correct == false){
            if(score > 0.0){
                score -= 0.5;
            }
        }
        if (isAnswer3Correct != isAnswer3selected & isAnswer3Correct == false){
            if(score > 0.0){
                score -= 0.5;
            }
        }
        if (isAnswer4Correct != isAnswer4selected & isAnswer4Correct == false){
            if(score > 0.0){
                score -= 0.5;
            }
        }

        return score;
    }
}
