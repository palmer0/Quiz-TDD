package es.ulpgc.eite.da.quiz.cheat;

import java.lang.ref.WeakReference;


public interface CheatContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayCheatData(CheatViewModel viewModel);

        void finishView();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void yesButtonClicked();

        void noButtonClicked();

        void onResumeCalled();

        void onCreateCalled();

        void onRecreateCalled();

        void onBackPressed();
    }

    interface Model {

        //String getFalseAnswerText();

        //String getTrueAnswerText();
    }
}