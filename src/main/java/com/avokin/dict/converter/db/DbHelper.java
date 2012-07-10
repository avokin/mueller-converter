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
        statement.executeUpdate("drop table if exists words");
        statement.executeUpdate("drop table if exists android_metadata");

        statement.executeUpdate("create table android_metadata(locale string)");
        statement.executeUpdate("insert into android_metadata(locale) values('en_US')");

        statement.executeUpdate("create table words(word string, transcription string, translations string)");
    }

    public static void closeDb() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void addDictionaryItem(DictionaryItem dictionaryItem) throws SQLException {
        Statement insertStatement = connection.createStatement();
        insertStatement.executeUpdate(String.format("insert into words(word, transcription, translations) values('%s', '%s', '%s')", dictionaryItem.getWord(), dictionaryItem.getTranscription(), dictionaryItem.getTranslations()));
    }
}
