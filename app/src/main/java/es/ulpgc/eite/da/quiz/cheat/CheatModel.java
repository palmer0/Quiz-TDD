package es.ulpgc.eite.da.quiz.cheat;

/**
 * Created by Luis on March, 2021
 */
public class CheatModel implements CheatContract.Model {

    public static String TAG = "Quiz.CheatModel";

    private String falseAnswerText, trueAnswerText;

    public CheatModel() {

    }

    @Override
    public String getFalseAnswerText() {
        return falseAnswerText;
    }

    public void setFalseAnswerText(String text) {
        falseAnswerText = text;
    }

    @Override
    public String getTrueAnswerText() {
        return trueAnswerText;
    }

    public void setTrueAnswerText(String text) {
        trueAnswerText = text;
    }
}