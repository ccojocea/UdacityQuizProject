package com.example.android.ccojocea.javaquiz;

/**
 * Created by ccojo on 1/20/2018.
 */

public class MultipleAnswerQuestion extends Question {
    private boolean isAnswer1Correct, isAnswer2Correct, isAnswer3Correct, isAnswer4Correct;
    public enum GradingSystem{FULL, PARTIAL_OLD, PARTIAL_NEW}

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
    public float validateAnswer(GradingSystem grading, boolean isAnswer1selected, boolean isAnswer2selected, boolean isAnswer3selected, boolean isAnswer4selected){
        float score = 0.0f;

        //complete result score - Get a point if both answers are found and no incorrect answers are marked
        if(grading == GradingSystem.FULL){
            int count = 0;
            if (isAnswer1selected) {
                count++;
            }
            if (isAnswer2selected) {
                count++;
            }
            if (isAnswer3selected) {
                count++;
            }
            if (isAnswer4selected) {
                count++;
            }
            if(count <= 2){
                if (isAnswer1Correct == isAnswer1selected & isAnswer1Correct == true){
                    score += 0.5;
                }
                if (isAnswer2Correct == isAnswer2selected & isAnswer2Correct == true){
                    score += 0.5;
                }
                if (isAnswer3Correct == isAnswer3selected & isAnswer3Correct == true){
                    score += 0.5;
                }
                if (isAnswer4Correct == isAnswer4selected & isAnswer4Correct == true) {
                    score += 0.5;
                }
                if (score != 1.0f){
                    score = 0.0f;
                }
            }
        }

        //partial result score (new version) - Get partial points but only if you selected a maximum of 2 questions so you cannot cheat this.
        if(grading == GradingSystem.PARTIAL_NEW){
            int count = 0;
            if (isAnswer1selected) {
                count++;
            }
            if (isAnswer2selected) {
                count++;
            }
            if (isAnswer3selected) {
                count++;
            }
            if (isAnswer4selected) {
                count++;
            }
            if(count <= 2){
                if (isAnswer1Correct == isAnswer1selected & isAnswer1Correct == true){
                    score += 0.5;
                }
                if (isAnswer2Correct == isAnswer2selected & isAnswer2Correct == true){
                    score += 0.5;
                }
                if (isAnswer3Correct == isAnswer3selected & isAnswer3Correct == true){
                    score += 0.5;
                }
                if (isAnswer4Correct == isAnswer4selected & isAnswer4Correct == true) {
                    score += 0.5;
                }
            }
        }

        //partial result score (old version) - Get points for each correct answer, get penalised for the same amount for any incorrect answer
        if(grading == GradingSystem.PARTIAL_OLD){
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
        }

        return score;
    }
}
