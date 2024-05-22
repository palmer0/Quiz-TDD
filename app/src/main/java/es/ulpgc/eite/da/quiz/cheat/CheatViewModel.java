package es.ulpgc.eite.da.quiz.cheat;

import java.util.Objects;

public class CheatViewModel {

  public String answerText = "";

  public boolean yesButton = true;
  public boolean noButton = true;

  /*
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    CheatViewModel that = (CheatViewModel) obj;
    return yesButton == that.yesButton &&
        noButton == that.noButton &&
        Objects.equals(answerText, that.answerText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answerText, yesButton, noButton);
  }


  @Override
  public String toString() {
    return
        "answerText: " + answerText + ", " +
        "yesButton: " + yesButton + ", " +
        "noButton: " + noButton ;
  }

  */
}

