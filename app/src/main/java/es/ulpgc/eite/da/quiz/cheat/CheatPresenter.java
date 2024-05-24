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

    state = new CheatState();
    mediator.setCheatState(state);

  }

  @Override
  public void onRecreateCalled() {
    Log.e(TAG, "onRecreateCalled");

    state = mediator.getCheatState();
  }

  @Override
  public void onResumeCalled() {
    Log.e(TAG, "onResumeCalled");

    view.get().displayCheatData(state);
  }

  @Override
  public void onBackPressed() {
    Log.e(TAG, "onBackPressed");

    //mediator.resetCheatToQuestionState();

    /*if(state.cheated) {
      state.cheated = false;

      CheatToQuestionState newState = new CheatToQuestionState(true);
      mediator.setCheatToQuestionState(newState);
    }*/
  }

  /*
  @Override
  public void onDestroyCalled() {
    Log.e(TAG, "onDestroyCalled");

    // Reset current state 
    mediator.resetCheatState();
  }
  */


  @Override
  public void yesButtonClicked() {

    // set passed state
    QuestionToCheatState savedState = mediator.getQuestionToCheatState();
    if(savedState != null) {

      //state.cheated = true;

      CheatToQuestionState newState = new CheatToQuestionState(true);
      mediator.setCheatToQuestionState(newState);
      
      if(savedState.answer) {
        state.answerText = model.getTrueAnswerText();
      } else {
        state.answerText = model.getFalseAnswerText();
      }

      state.yesButton = false;
      state.noButton = false;

      view.get().displayCheatData(state);
    }
  }

  @Override
  public void noButtonClicked() {
    CheatToQuestionState newState = new CheatToQuestionState(false);
    mediator.setCheatToQuestionState(newState);
    view.get().finishView();
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
