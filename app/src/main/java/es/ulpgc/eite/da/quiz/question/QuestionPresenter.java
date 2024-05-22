package es.ulpgc.eite.da.quiz.question;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;


public class QuestionPresenter implements QuestionContract.Presenter {

  //public static String TAG = QuestionPresenter.class.getSimpleName();
  public static String TAG = "Quiz.QuestionPresenter";

  private WeakReference<QuestionContract.View> view;
  private QuestionState state;
  private QuestionContract.Model model;
  private AppMediator mediator;

  public QuestionPresenter(AppMediator mediator) {
    this.mediator = mediator;
    //state = mediator.getQuestionState();
  }

  @Override
  public void onCreateCalled() {
    Log.e(TAG, "onCreateCalled()");

    state = new QuestionState();
    mediator.setQuestionState(state);
  }

  @Override
  public void onRecreateCalled() {
    Log.e(TAG, "onRecreateCalled()");

    state = mediator.getQuestionState();
  }

  @Override
  public void onResumeCalled() {
    //Log.e(TAG, "onResumeCalled()");

    // set passed state
    CheatToQuestionState savedState =  mediator.getCheatToQuestionState();
    //CheatToQuestionState savedState = getDataFromCheatScreen();
    if(savedState != null) {

        if(savedState.cheated){
          nextButtonClicked();
          return;
        }
    }

    // call the model
    model.setCurrentIndex(state.quizIndex);
    state.questionText = model.getCurrentQuestion();

    view.get().displayQuestionData(state);

  }

  private void updateQuestionData(boolean userAnswer) {

    boolean currentAnswer = model.getCurrentAnswer();

    if(currentAnswer == userAnswer) {
      state.resultText = model.getCorrectLabel();
    } else {
      state.resultText = model.getIncorrectLabel();
    }

    state.falseButton = false;
    state.trueButton = false;
    state.cheatButton = false;

    if(model.isLastQuestion()) {
      state.nextButton = false;
    } else {
      state.nextButton = true;
    }

    view.get().displayQuestionData(state);
  }


  @Override
  public void trueButtonClicked() {
    updateQuestionData(true);
  }

  @Override
  public void falseButtonClicked() {
    updateQuestionData(false);
  }

  @Override
  public void cheatButtonClicked() {
    boolean answer = model.getCurrentAnswer();
    QuestionToCheatState newState = new QuestionToCheatState(answer);
    //passDataToCheatScreen(newState);
    mediator.setQuestionToCheatState(newState);

    view.get().navigateToCheatScreen();
  }

  @Override
  public void nextButtonClicked() {
    //Log.e(TAG, "nextButtonClicked()");

    state.quizIndex++;
    model.incrQuizIndex();

    state.questionText = model.getCurrentQuestion();
    state.resultText = "";

    state.falseButton = true;
    state.trueButton = true;
    state.cheatButton = true;
    state.nextButton = false;

    view.get().displayQuestionData(state);
  }

//  private void passDataToCheatScreen(QuestionToCheatState state) {
//    mediator.setQuestionToCheatState(state);
//  }

//  private CheatToQuestionState getDataFromCheatScreen() {
//    return mediator.getCheatToQuestionState();
//  }

  @Override
  public void injectView(WeakReference<QuestionContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(QuestionContract.Model model) {
    this.model = model;
  }

}
