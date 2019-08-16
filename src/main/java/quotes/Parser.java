package quotes;

import com.google.gson.*;

import java.io.*;
import java.net.*;

class Parser {
  public String inputReader(){
    Gson gson = new Gson();
    return urlReader(gson);
  }

  public String urlReader(Gson gson) {
    Quotable quote;
    String response;
    try {
      URL url = new URL("http://swquotesapi.digitaljedi.dk/api/SWQuote/RandomStarWarsQuote");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setConnectTimeout(5000);
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuilder content = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      addToJson(content.toString());
      quote = gson.fromJson(content.toString(), StarWarsQuote.class);
      response = printQuote(quote);
    } catch (IOException e) {
      response = jsonReader(gson);
    }
    return response;
  }

  public String jsonReader(Gson gson) {
    String response = "";
    try {
      Quotable[] quote = gson.fromJson(
          new FileReader("./src/main/resources/recentquotes.json"),
          Quote[].class
      );
      response = printQuote(quote);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return response;
  }

  private void addToJson(String jsonString) {
    JsonArray recentQuote = addQuote(jsonString);
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/recentquotes.json"));
      writer.write(recentQuote.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private JsonArray addQuote(String jsonString) {
    JsonArray recentQuote = new JsonArray();
    try {
      Gson gson = new Gson();
      JsonObject inputObj = gson.fromJson(jsonString, JsonObject.class);
      JsonObject updatedObj = new JsonObject();
      updatedObj.add("text", inputObj.get("starWarsQuote"));

      recentQuote = gson.fromJson(new FileReader("./src/main/resources/recentquotes.json"), JsonArray.class);
      recentQuote.add(updatedObj);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return recentQuote;
  }

  public int randomNum(Quotable[] quotes) {
    return (int) (Math.random() * quotes.length);
  }

  private String printQuote(Quotable[] quotes) {
    Quotable quote = quotes[randomNum(quotes)];
    return quote.toString();
  }

  private String printQuote(Quotable quote) {
    return quote.toString();
  }
}
