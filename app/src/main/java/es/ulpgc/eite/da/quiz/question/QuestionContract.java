package es.ulpgc.eite.da.quiz.question;

import java.lang.ref.WeakReference;


public interface QuestionContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayQuestionData(QuestionViewModel viewModel);
    //String getIncorrectLabel();
    //String getCorrectLabel();
    void navigateToCheatScreen();

  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void onResumeCalled();
    void trueButtonClicked();
    void falseButtonClicked();
    void cheatButtonClicked();
    void nextButtonClicked();

    void onCreateCalled();

    void onRecreateCalled();
  }

  interface Model {

    String getCorrectLabel();
    String getIncorrectLabel();

    String getCurrentQuestion();
    boolean getCurrentAnswer();
    boolean isLastQuestion();
    void incrQuizIndex();
    void setCurrentIndex(int index);
  }

}