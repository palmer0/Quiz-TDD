package es.ulpgc.eite.da.quiz.question;

public class QuestionViewModel {

    public String questionText;
    //public String resultText = "";

    //  public boolean falseButton = true;
//  public boolean trueButton = true;
//  public boolean cheatButton = true;
    public boolean nextButton = false;
    public boolean isLastQuestion = false;
    public boolean resultIsCorrect = false;

  /*
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    QuestionViewModel that = (QuestionViewModel) obj;
    return falseButton == that.falseButton &&
        trueButton == that.trueButton &&
        cheatButton == that.cheatButton &&
        nextButton == that.nextButton &&
        Objects.equals(questionText, that.questionText) &&
        Objects.equals(resultText, that.resultText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        questionText, resultText, falseButton, trueButton, cheatButton, nextButton
    );
  }

  @Override
  public String toString() {
    return
        "questionText: " + questionText + ", " +
        "resultText: " + resultText + ", " +
        "trueButton: " + trueButton + ", " +
        "falseButton: " + falseButton + ", " +
        "cheatButton: " + cheatButton + ", " +
        "nextButton: " + nextButton;
  }

  */
}

