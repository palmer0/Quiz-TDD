package es.ulpgc.eite.da.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;
import es.ulpgc.eite.da.quiz.cheat.CheatActivity;

@RunWith(RobolectricTestRunner.class)
//@Config(manifest=Config.NONE)
@Config(sdk = 28)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheatActivityTests {


    private String[] quizQuestions = {
        "Christian Bale played Batman in 'The Dark Knight Rises'?", // 1
        "The Gremlins movie was released in 1986?",  // 2
        "Brad Pitt played Danny Ocean in Ocean's Eleven, Ocean's Twelve and Ocean's Thirteen?",  // 3
        "A spoon full of sugar' came from the 1964 movie Mary Poppins?",  // 4
//      "The song “I don't want to miss a thing” featured in Armageddon?", // 5
//      "Will Smith has a son called Jaden?", // 6
//      "Mark Ruffalo played Teddy Daniels in Shutter Island?", // 7
//      "Mike Myers starred in the 'Cat in the Hat' 2003 children's movie?", // 8
//      "Ryan Reynolds is married to Scarlett Johansson?", // 9
//      "The movie 'White House Down' was released in 2014?",  // 10
//      "Michael Douglas starred in Basic Instinct, Falling Down and The Game?", // 11
//      "Colin Firth won an Oscar for his performance in the historical movie 'The King's Speech'?",  // 12
//      "Cameron Diaz and Ashton Kutcher starred in the movie 'What happens in Vegas'?", // 13
//      "Arnold Schwarzenegger played lead roles in Rocky, Rambo and Judge Dredd?", // 14
//      "The Titanic movie featured the song 'My Heart Will Go On'?", // 15
//      "Eddie Murphy narrates the voice of Donkey in the Shrek movies?", // 16
//      "Nicole Kidman played Poison Ivy in 'Batman and Robin'?", // 17
//      "The Lara Croft: Tomb Raider movie was released in 2003?", // 18
//      "Hallie Berry played the character Rogue in X Men?", // 19
        "The Teenage Mutant Ninja Turtles are named after famous artists?" // 20
    };

    private boolean[] quizAnswers = {
        true, // 1
        false, // 2
        false, // 3
        true, // 4
//      true, // 5
//      true, // 6
//      false, // 7
//      true, // 8
//      false, // 9
//      false, // 10
//      true, // 11
//      true, // 12
//      true, // 13
//      false, // 14
//      true, // 15
//      true, // 16
//      false, // 17
//      false, // 18
//      false, // 19
        true // 20
    };


    @Test
    public void test01ActivityInitialization() {

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerField);
            TextView warningTextView = activity.findViewById(R.id.confirmationField);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text), // expected
                warningTextView.getText().toString() // actual
            );

            // Verify the empty answer is shown
            assertEquals(
                activity.getString(R.string.empty_text), // expected
                answerTextView.getText().toString() // actual
            );

            // Verify the buttons are enabled
            assertTrue(yesButton.isEnabled());
            assertTrue(noButton.isEnabled());
        });
    }


    @Test
    public void test02ShowAnswerTrue() {

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerField);
            TextView warningTextView = activity.findViewById(R.id.confirmationField);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Press Yes button
            yesButton.performClick();

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text),
                warningTextView.getText().toString()
            );

            // Verify the True answer is shown
            assertEquals(
                activity.getString(R.string.true_text),
                answerTextView.getText().toString()
            );

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test02ShowAnswerFalse() {

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[1]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerField);
            TextView warningTextView = activity.findViewById(R.id.confirmationField);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Press Yes button
            yesButton.performClick();

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text),
                warningTextView.getText().toString()
            );

            // Verify the False answer is shown
            assertEquals(
                activity.getString(R.string.false_text),
                answerTextView.getText().toString()
            );

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test03RotateScreen() {

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        scenario.onActivity(activity -> {
            TextView answerTextView = activity.findViewById(R.id.answerField);
            TextView warningTextView = activity.findViewById(R.id.confirmationField);
            Button yesButton = activity.findViewById(R.id.yesButton);
            Button noButton = activity.findViewById(R.id.noButton);

            // Press Yes button
            yesButton.performClick();

            // Rotate screen
            activity.recreate();

            // Verify the warning message is shown
            assertEquals(
                activity.getString(R.string.confirmation_text),
                warningTextView.getText().toString()
            );

            // Verify the True answer is shown
            assertEquals(
                activity.getString(R.string.true_text),
                answerTextView.getText().toString()
            );

            // Verify the buttons are disabled
            assertFalse(yesButton.isEnabled());
            assertFalse(noButton.isEnabled());
        });
    }

    @Test
    public void test04ReturnResultNo() {

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        scenario.onActivity(activity -> {
            Button noButton = activity.findViewById(R.id.noButton);

            // Press No button
            noButton.performClick();

            CheatToQuestionState savedState =
                AppMediator.getInstance().getCheatToQuestionState();

            assertFalse(savedState.cheated);
        });
    }


    @Test
    public void test05ReturnResultYes() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {
            Button yesButton = activity.findViewById(R.id.yesButton);

            // Press Yes button
            yesButton.performClick();

            // Go back to QuestionActivity
            activity.onBackPressed();

            CheatToQuestionState savedState =
                    AppMediator.getInstance().getCheatToQuestionState();

            assertTrue(savedState.cheated);
        });
    }


    @Test
    public void test06BackPressed() {

        QuestionToCheatState nextState = new QuestionToCheatState(quizAnswers[0]);
        AppMediator.getInstance().setQuestionToCheatState(nextState);

        ActivityScenario<CheatActivity> scenario =
                ActivityScenario.launch(CheatActivity.class);

        scenario.onActivity(activity -> {

            // Go back to QuestionActivity
            activity.onBackPressed();

            CheatToQuestionState savedState =
                    AppMediator.getInstance().getCheatToQuestionState();

            assertNull(savedState);
        });
    }

}
