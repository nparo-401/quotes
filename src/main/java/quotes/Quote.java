package quotes;

public class Quote implements Quotable {
  private String author;
  private String text;

  Quote(String text) {
    this.text = text;
  }

  Quote(String author, String text) {
    this.author = author;
    this.text = text;
  }

  public String setString() {
    if (this.author == null) {
      return String.format("Quote: %s\n", this.text.trim());
    } else {
      return String.format("Quote: %s - %s\n", this.text.trim(), this.author);
    }
  }

  @Override
  public String toString() {
    return setString();
  }
}
