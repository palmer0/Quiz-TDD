package es.ulpgc.eite.da.quiz.question;

public class QuestionState extends QuestionViewModel {

    public int quizIndex = 0;


  /*
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    if (!super.equals(obj)) return false;
    QuestionState that = (QuestionState) obj;
    return quizIndex == that.quizIndex;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), quizIndex);
  }

  @Override
  public String toString() {
    return "quizIndex: " + quizIndex + ", " + super.toString();
  }

  */
}
