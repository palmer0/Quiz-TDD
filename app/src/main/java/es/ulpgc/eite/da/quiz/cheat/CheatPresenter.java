package es.ulpgc.eite.da.quiz.cheat;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;


public class CheatPresenter implements CheatContract.Presenter {

    public static String TAG = "Quiz.CheatPresenter";

    private WeakReference<CheatContract.View> view;
    private CheatContract.Model model;
    private CheatState state;
    private AppMediator mediator;

    public CheatPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        // init the screen state
        state = new CheatState();
        state.answerText = model.getEmptyAnswerText();
        //mediator.setCheatState(state);

    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        // restore the screen state
        state = mediator.getCheatState();
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        // refresh the display with updated state
        view.get().displayCheatData(state);
    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed");

        //mediator.resetCheatToQuestionState();

        /*if(state.cheated) {
          state.cheated = false;

          CheatToQuestionState newState = new CheatToQuestionState(true);
          mediator.setCheatToQuestionState(newState);
        }*/
    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled");

        // save the current state
        mediator.setCheatState(state);
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled");

        // Reset current state
        //mediator.resetCheatState();
    }


    @Override
    public void yesButtonClicked() {
        Log.e(TAG, "yesButtonClicked");

        // get the saved state from previous screen
        QuestionToCheatState savedState = mediator.getQuestionToCheatState();
        if (savedState != null) {

            //state.cheated = true;

            // save the state to previous screen
            saveStateToPreviousScreen(true);
            //CheatToQuestionState prevState = new CheatToQuestionState(true);
            //mediator.setCheatToQuestionState(prevState);

            // update the current state

            if (savedState.answer) {
                state.answerText = model.getTrueAnswerText();

            } else {
                state.answerText = model.getFalseAnswerText();
            }

            //state.isAnswerTrue = savedState.answer;
            //state.isAnswerShown = true;
            //state.cheatButtons = false;

            state.yesButton = false;
            state.noButton = false;

            // refresh the display with updated state
            view.get().displayCheatData(state);
        }
    }

    @Override
    public void noButtonClicked() {
        Log.e(TAG, "noButtonClicked");

        // save the state to previous screen
        saveStateToPreviousScreen(false);
        //CheatToQuestionState prevState = new CheatToQuestionState(false);
        //mediator.setCheatToQuestionState(prevState);

        // finish the current screen
        view.get().finishView();
    }

    private void saveStateToPreviousScreen(boolean cheated) {
        Log.e(TAG, "saveStateToPreviousScreen");

        CheatToQuestionState prevState = new CheatToQuestionState(cheated);
        mediator.setCheatToQuestionState(prevState);
    }

    @Override
    public void injectView(WeakReference<CheatContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CheatContract.Model model) {
        this.model = model;
    }
}
