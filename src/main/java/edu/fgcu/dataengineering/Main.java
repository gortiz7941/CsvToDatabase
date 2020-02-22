package edu.fgcu.dataengineering;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, CsvValidationException {
    // Literally just calls our parser right now (....and is called for tests)
    String infile = "src/Data/bookstore_report2.csv";
    CsvParser csvP = new CsvParser(infile);
    csvP.printCsv();

    // Load the json
        /*
        1. Create instance of GSON
        2. Create a JsonReader object using FileReader
        3. Array of class instances of AuthorParser, assign data from our JsonReader
        4. foreach loop to check data
         */
    Gson gson = new Gson();
    JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
    AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);
    DatabaseManager dbManager = new DatabaseManager();

    //populate author table with json file data
/*    for (var element : authors) {
      dbManager.addAuthor(element);
    }*/

    // add records into Book table
/*    dbManager.addBook(infile);
    dbManager.disconnectFromDB();*/

  }
}
