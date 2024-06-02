package es.ulpgc.eite.da.quiz.question;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.quiz.R;
import es.ulpgc.eite.da.quiz.cheat.CheatActivity;


public class QuestionActivity
    extends AppCompatActivity implements QuestionContract.View {

    public static String TAG = "Quiz.QuestionActivity";

    QuestionContract.Presenter presenter;

    TextView questionField, resultField;
    Button trueButton, falseButton, cheatButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle(R.string.question_screen_title);


        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        cheatButton = findViewById(R.id.cheatButton);
        nextButton = findViewById(R.id.nextButton);
        questionField = findViewById(R.id.questionText);
        resultField = findViewById(R.id.resultText);

        Log.e(TAG, "onCreate");

        trueButton.setText(getTrueButtonLabel());
        falseButton.setText(getFalseButtonLabel());
        cheatButton.setText(getCheatButtonLabel());
        nextButton.setText(getNextButtonLabel());

        trueButton.setOnClickListener(v -> presenter.trueButtonClicked());
        falseButton.setOnClickListener(v -> presenter.falseButtonClicked());
        cheatButton.setOnClickListener(v -> presenter.cheatButtonClicked());
        nextButton.setOnClickListener(v -> presenter.nextButtonClicked());


    /*
    trueButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        presenter.trueButtonClicked();
      }
    });

    falseButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        presenter.falseButtonClicked();
      }
    });

    cheatButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        presenter.cheatButtonClicked();
      }
    });

    nextButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        presenter.nextButtonClicked();
      }
    });
    */

        // do the setup
        QuestionScreen.configure(this);

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
        //Log.e(TAG, "onResume");

        // do some work
        presenter.onResumeCalled();
    }


    @Override
    protected void onPause() {
        super.onPause();

        //Log.e(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Log.e(TAG, "onDestroy");
    }


    @Override
    public void navigateToCheatScreen() {
        Intent intent = new Intent(this, CheatActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void displayQuestionData(QuestionViewModel viewModel) {
        Log.e(TAG, "displayQuestionData");

        // deal with the data
        questionField.setText(viewModel.questionText);
        resultField.setText(viewModel.resultText);

        trueButton.setEnabled(viewModel.trueButton);
        falseButton.setEnabled(viewModel.falseButton);
        cheatButton.setEnabled(viewModel.cheatButton);
        nextButton.setEnabled(viewModel.nextButton);

        /*
        resultField.setText(getString(R.string.empty_text));

        if (viewModel.nextButton || viewModel.isLastQuestion) {
            resultField.setText(
                viewModel.resultIsCorrect
                    ? getCorrectResultText()
                    : getIncorrectResultText()
            );

        }
        */


        /*
        if (viewModel.isLastQuestion) {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
            cheatButton.setEnabled(false);
            nextButton.setEnabled(false);

        } else {
            trueButton.setEnabled(!viewModel.nextButton);
            falseButton.setEnabled(!viewModel.nextButton);
            cheatButton.setEnabled(!viewModel.nextButton);
            nextButton.setEnabled(viewModel.nextButton);
        }
        */

    }

    /*
    public String getCorrectResultText() {
        return getString(R.string.correct_text);
    }

    public String getIncorrectResultText() {
        return getString(R.string.incorrect_text);
    }
    */

    private String getCheatButtonLabel() {
        return getResources().getString(R.string.cheat_label);
    }

    private String getNextButtonLabel() {
        return getResources().getString(R.string.next_label);
    }

    private String getFalseButtonLabel() {
        return getResources().getString(R.string.false_label);
    }

    private String getTrueButtonLabel() {
        return getResources().getString(R.string.true_label);
    }


    @Override
    public void injectPresenter(QuestionContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
