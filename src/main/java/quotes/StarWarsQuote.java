package quotes;

public class StarWarsQuote implements Quotable {
  private String starWarsQuote;

  StarWarsQuote(String starWarsQuote) {
    this.starWarsQuote = starWarsQuote;
  }

  public String setString() {
    return String.format("Quote: %s\n", this.starWarsQuote.trim());
  }

  @Override
  public String toString() {
    return setString();
  }
}
