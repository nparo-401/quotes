<a name=top></a>
## Lab 08 - Quotes && Lab 09 - Potent Quotables
### Table of Contents
* [Team](#team)
* [Resources](#resources)
* [Files](#files)
* [Methods](#methods)

<a name="team"></a>
### Team:
* Nicholas Paro
* Sapana Poudel

<a name="resources"></a>
### Resources:
* Bomi Bear
* Melfi Perez
* Travis Cox
* Marisha Hoza
* Matt Stuhring
* Travis Cox
* [HTTP Java Request](https://www.baeldung.com/java-http-request)
* [Star Wars Quote API](http://swquotesapi.digitaljedi.dk/index.html)
* [Add Data to JsonObject](https://stackoverflow.com/questions/11143363/add-data-to-jsonobject)

<a name="files"></a>
### Files:
* quotes
  * src/main 
    * [App.java](./src/main/java/quotes/App.java)
    * [Parser.java](./src/main/java/quotes/Parser.java)
    * [Quotable.java](./src/main/java/quotes/Quotable.java)
    * [Quote.java](./src/main/java/quotes/Quote.java)
    * [StarWarsQuote.java](./src/main/java/quotes/StarWarsQuote.java)
  * src/test
    * [ParserTest.java](./src/test/java/quotes/ParserTest.java)

<a name="methods"></a>
### Methods:
**Interface**
* **Quotable**
  * Methods:
    * `String setString()`
    * `String toString()`

**Classes**
* **App**
  * To run the Application:
    * Open a command line interface
    * Go into the quotes directory
    * use the command: `./gradlew run` to run the application
  * Methods:
    * `main()`
      * Creates an instance of the Parser class and calls `jsonReader()`

* **Parser**
  * Methods:
    * `public String inputReader()`
      * Initializes a Gson object
      * returns a call to `urlReader(Gson gson)`
    * `public String urlReader(Gson gson)`
      * Use a `try` block to:
        * opens an HTTP connection and pulls in a random quote from the Star Wars quote API
        * Assigns the quote to an instance of `StarWarsQuote` as a `Quotable` object
        * Sets `String response` to a call of `printQuote` and passes in the `starWarsQuote` as a non array argument
       * Use a `catch` block to:
        * call `jsonReader(Gson gson)` and assign the response to `String response`
       * `return response`
    * `public String jsonReader(Gson gson)`
      * reads the json file using Gson
      * Assigns the quotes to an instance of `Quotes` as a `Quotable[]` object
      * Sets `String response` to a call of `printQuote` and passes in `quotes` as an array argument
      * `return response`
    * `private void addToJson(String jsonString)`
      * Assigns `JsonArray recentQuote = addQuote(String jsonString)`
      * Uses a `try catch` block to overwrite the recentquotes.json file
    * `private JsonArray addQuote(String jsonString)`
      * Uses new `JsonArray` and `JsonObject` to pull in the quote from the Star Wars Quote API and assign new property to the quote so it can be read by the `Quote` class
      * returns a JsonArray containing the new quote for `addToJson` method
    * `int randomNum(Quotable[] quotes)`
      * returns a random number between 0 and `quotes.length`
    * `String printQuote(Quotable[] quotes)`
      * returns a quote at the random number as a string
    * `String printQuote(Quotable quote)`
      * returns the quote as a string
  
* **Quote extends Quotable**
  * Variables:
    * `String author`
    * `String text`
  * Constructor:
    * `Quote(String text)`
    * `Quote(String author, String text)`
  * Methods:
    * `public String setString()`
      * returns a formatted string
    * `public String toString()`
      * returns `setString()`

* **StarWarsQuote extends Quotable**
  * Variables:
    * `String starWarsQuote`
  * Constructor:
    * `StarWarsQuote(String starWarsQuote)`
  * Methods:
    * `public String setString()`
      * returns a formatted string
    * `public String toString()`
      * returns `setString()`
  
**[Back to Top](#top)**
