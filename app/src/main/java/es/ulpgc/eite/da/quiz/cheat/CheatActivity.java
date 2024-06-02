package es.ulpgc.eite.da.quiz.cheat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.quiz.R;


public class CheatActivity
    extends AppCompatActivity implements CheatContract.View {

    public static String TAG = "Quiz.CheatActivity";

    CheatContract.Presenter presenter;

    TextView confirmationField, answerField;
    Button yesButton, noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setTitle(R.string.cheat_screen_title);

        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        confirmationField = findViewById(R.id.confirmationText);
        answerField = findViewById(R.id.answerText);

        yesButton.setText(getYesButtonLabel());
        noButton.setText(getNoButtonLabel());
        confirmationField.setText(getConfirmationButtonLabel());

    /*yesButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        presenter.yesButtonClicked();
      }
    });

    noButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        presenter.noButtonClicked();
      }
    });*/

        yesButton.setOnClickListener(v -> presenter.yesButtonClicked());
        noButton.setOnClickListener(v -> presenter.noButtonClicked());


        // do the setup
        CheatScreen.configure(this);

        // do some work
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.onResumeCalled();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //presenter.onDestroyCalled();
    }


    @Override
    public void displayCheatData(CheatViewModel viewModel) {
        Log.e(TAG, "displayCheatData");

        // deal with the data
        answerField.setText(viewModel.answerText);

        //yesButton.setEnabled(viewModel.cheatButtons);
        //noButton.setEnabled(viewModel.cheatButtons);
        yesButton.setEnabled(viewModel.yesButton);
        noButton.setEnabled(viewModel.noButton);

        /*
        if(!viewModel.isAnswerShown) {
            answerField.setText(getString(R.string.empty_text));

        }  else {

            if (viewModel.isAnswerTrue) {
                answerField.setText(getTrueAnswerText());
            } else {
                answerField.setText(getFalseAnswerText());
            }
        }

        yesButton.setEnabled(!viewModel.isAnswerShown);
        noButton.setEnabled(!viewModel.isAnswerShown);
        */

    }

    @Override
    public void finishView() {
        finish();
    }

    /*
    public String getTrueAnswerText() {
        return getString(R.string.true_text);
    }

    public String getFalseAnswerText() {
        return getString(R.string.false_text);
    }

    */


    private String getYesButtonLabel() {
        return getResources().getString(R.string.yes_label);
    }

    private String getNoButtonLabel() {
        return getResources().getString(R.string.no_label);
    }

    private String getConfirmationButtonLabel() {
        return getResources().getString(R.string.confirmation_text);
    }


    @Override
    public void injectPresenter(CheatContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
