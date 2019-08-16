package quotes;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class ParserTest {
  @Test
  public void testUrlReader() {
    Gson gson = new Gson();
    Parser parser = new Parser();
    assertNotNull(
        "testUrlReader should return: ",
        parser.urlReader(gson)
    );
  }

  @Test
  public void testJsonReader() {
    Gson gson = new Gson();
    Parser parser = new Parser();
    assertNotNull(
        "testJsonReader should return: ",
        parser.jsonReader(gson));
  }

  @Test
  public void testRandomNum() {
    Gson gson = new Gson();
    Parser parser = new Parser();
    try {
      Quotable[] quote = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class);

      for (int i = 0; i < 10000; i++) {
        assertTrue(
            "testRandomNum is between 0 and quote.length",
            parser.randomNum(quote) >= 0 && parser.randomNum(quote) < quote.length
            );
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAddToJson() {
    Gson gson = new Gson();
    Parser parser = new Parser();
    try {
      Quotable[] quote = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class);
      int firstLen = quote.length;

      parser.inputReader();

      Quotable[] quote2 = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class);
      int secondLen = quote2.length;

      assertEquals(
          "testAddToJson should return quote2 length 1 larger than quote length",
          firstLen + 1,
          secondLen
      );
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAddQuote() {
    Gson gson = new Gson();
    Parser parser = new Parser();
    try {
      Quotable[] quote1 = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class);

      parser.inputReader();

      Quotable[] quote2 = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class);

      assertEquals(
          "testAddQuote should return true that the last quote of quote1 == second to last quote of quote2",
          quote1[quote1.length - 1].toString(),
          quote2[quote2.length - 2].toString()
      );
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAddQuote_AllQuotes() {
    Gson gson = new Gson();
    Parser parser = new Parser();
    try {
      Quotable[] quote1 = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class);

      parser.inputReader();

      Quotable[] quote2 = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class);

      for (int i = 0; i < quote1.length; i++) {
        assertEquals(
            "testAddQuote should return true that the last quote of quote1 == second to last quote of quote2",
            quote1[i].toString(),
            quote2[i].toString()
        );
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
