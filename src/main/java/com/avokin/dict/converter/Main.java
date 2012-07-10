package com.avokin.dict.converter;

import com.avokin.dict.converter.db.DbHelper;
import com.avokin.dict.converter.model.DictionaryItem;
import com.avokin.dict.converter.parser.MuellerDictionaryParser;

import java.io.IOException;
import java.sql.SQLException;

/**
 * User: Andrey.Vokin
 * Date: 7/9/12
 */
public class Main {
  public static final String DICTIONARY_PATH = "data/SmallMueller7GPL.koi";

  public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
    final MuellerDictionaryParser parser = new MuellerDictionaryParser(DICTIONARY_PATH);
    try {
      DbHelper.createDb();
      while (parser.ready()) {
        DictionaryItem di = parser.getNextItem();
        if (di == null) {
          break;
        }
        DbHelper.addDictionaryItem(di);
        System.out.println(di.word + "  [" + di.getTranscription() + "] " + di.getTranslations());

      }
    } finally {
      DbHelper.closeDb();
    }
  }
}
