package com.avokin.dict.converter;

import com.avokin.dict.converter.db.DbHelper;
import com.avokin.dict.converter.model.DictionaryItem;
import com.avokin.dict.converter.parser.MuellerDictionaryParser;

/**
 * User: Andrey.Vokin
 * Date: 7/9/12
 */
public class Main {
    public static void main(String[] args) {
        MuellerDictionaryParser parser = new MuellerDictionaryParser();

        DbHelper.createDb();
        DbHelper.openDb();
        while (parser.ready()) {
            DictionaryItem di = parser.getNextItem();
            DbHelper.addDictionaryItem(di);

        }
        DbHelper.closeDb();
    }
}
