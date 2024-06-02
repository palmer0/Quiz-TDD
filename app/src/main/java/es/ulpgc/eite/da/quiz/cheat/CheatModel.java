package es.ulpgc.eite.da.quiz.cheat;

/**
 * Created by Luis on March, 2021
 */
public class CheatModel implements CheatContract.Model {

    public static String TAG = "Quiz.CheatModel";

    private String falseAnswerText, trueAnswerText;
    private String emptyAnswerText;


    public CheatModel(String emptyText) {
        emptyAnswerText=emptyText;
    }

    @Override
    public String getEmptyAnswerText() {
        return emptyAnswerText;
    }

    /*
    public void setEmptyAnswerText(String emptyText) {
        this.emptyAnswerText = emptyText;
    }
    */

    @Override
    public String getFalseAnswerText() {
        return falseAnswerText;
    }

    @Override
    public void setFalseAnswerText(String text) {
        falseAnswerText = text;
    }

    @Override
    public String getTrueAnswerText() {
        return trueAnswerText;
    }

    @Override
    public void setTrueAnswerText(String text) {
        trueAnswerText = text;
    }

}