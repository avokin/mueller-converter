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
  public static final String DICTIONARY_PATH = "data/Mueller7GPL.koi";

  public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
    final MuellerDictionaryParser parser = new MuellerDictionaryParser(DICTIONARY_PATH);
    try {
      DbHelper.createDb();
      int id = 1;
      while (parser.ready()) {
        DictionaryItem di = parser.getNextItem();
        if (di == null) {
          break;
        }

        try {
          DbHelper.addDictionaryItem(di, id);
        } catch (SQLException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        id++;
      }
    } finally {
      DbHelper.closeDb();
    }
  }
}
