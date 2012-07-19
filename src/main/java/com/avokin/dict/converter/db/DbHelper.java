package com.avokin.dict.converter.db;

import com.avokin.dict.converter.model.DictionaryItem;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: Andrey.Vokin
 * Date: 7/9/12
 */
public class DbHelper {
  private static Connection connection;

  public static void createDb() throws SQLException, ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    SQLiteConfig config = new SQLiteConfig();
    connection = DriverManager.getConnection("jdbc:sqlite:sample.db", config.toProperties());

    Statement statement = connection.createStatement();
    statement.executeUpdate("PRAGMA case_sensitive_like = false");

    statement.executeUpdate("drop table if exists words");
    statement.executeUpdate("drop table if exists android_metadata");

    statement.executeUpdate("create table android_metadata(locale text)");
    statement.executeUpdate("insert into android_metadata(locale) values('en_US')");

    statement.executeUpdate("create table words(word text primary key, id integer unique, transcription string, translations string)");
  }

  public static void closeDb() throws SQLException {
    if (connection != null) {
      connection.close();
    }
  }

  public static void addDictionaryItem(DictionaryItem dictionaryItem, int id) throws SQLException {
    Statement insertStatement = connection.createStatement();
    String word = dictionaryItem.getWord().replace("\'", "''");
    String transcription = dictionaryItem.getTranscription().replace("\'", "''");
    String translations = dictionaryItem.getTranslations().replace("\'", "''");
    insertStatement.executeUpdate(String.format("insert into words(id, word, transcription, translations) values(%s, '%s', '%s', '%s')", id, word, transcription, translations));
  }
}
