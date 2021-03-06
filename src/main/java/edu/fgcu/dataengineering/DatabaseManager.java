package edu.fgcu.dataengineering;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

  private Connection conn = null;
  private PreparedStatement ps = null;
  private ResultSet rs = null;
  private boolean isConnectedToDB;
  private String[] bookInfo;

  DatabaseManager() {
    connectToDB();
  }

  private void connectToDB() {

    try {
      // Establish a connection to the database and flag the DBManager as connected.
      conn = DriverManager.getConnection(
          "jdbc:sqlite:D:\\Users\\Jerry\\Documents\\IdeaProjects\\CsvToDatabase\\src\\Data\\BookStore.db");
      isConnectedToDB = true;

      // Cover cases where a connection could not be made.
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  void disconnectFromDB() {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (Exception e) {
      System.out.print("Error closing ResultSet.\n");
      e.printStackTrace();
    }
    try {
      if (ps != null) {
        ps.close();
      }
    } catch (Exception e) {
      System.out.print("Error closing PreparedStatement.\n");
      e.printStackTrace();
    }
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (Exception e) {
      System.out.print("Error closing Connection.\n");
      e.printStackTrace();
    }

    isConnectedToDB = false;
    System.out.print("Connection successfully closed.\n");
  }

  boolean getConnectionStatus() {
    return isConnectedToDB;
  }

  void addAuthor(AuthorParser element) {
    System.out.println("Inserting Author records into table...");
    try {
      PreparedStatement ps = conn.prepareStatement(
          "INSERT INTO author VALUES ('"
              + element.getName()
              + "', '"
              + element.getEmail()
              + "', '"
              + element.getUrl()
              + "')");
      ps.executeUpdate();
      System.out.println("Inserted Author record into table.");
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Could not create booking record.");
    }
  }

  void addBook(String infile) {
    System.out.println("Inserting book records into table...");
    try {
      FileInputStream csvStream = new FileInputStream(infile);
      InputStreamReader inputStream = new InputStreamReader(csvStream,
          StandardCharsets.UTF_8);
      CSVReader reader = new CSVReader(inputStream);
      String[] header = reader.readNext();
      while ((bookInfo = reader.readNext()) != null) {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO book VALUES ('"
                + bookInfo[0]
                + "', '"
                + bookInfo[3]
                + "', '"
                + bookInfo[2]
                + "', '"
                + "NULL"
                + "', '"
                + bookInfo[1]
                + "', '"
                + "NULL"
                + "')");
        ps.executeUpdate();
        System.out.println("Inserted book record into table.");
      }
    } catch (SQLException | FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("Could not create booking record.");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    }

  }


}
