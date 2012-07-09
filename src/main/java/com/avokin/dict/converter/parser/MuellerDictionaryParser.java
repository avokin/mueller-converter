package com.avokin.dict.converter.parser;

import com.avokin.dict.converter.model.DictionaryItem;

import java.io.*;

/**
 * User: Andrey.Vokin
 * Date: 7/9/12
 */
public class MuellerDictionaryParser {
    private BufferedReader reader;

    public MuellerDictionaryParser(final String dictionaryPath) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(dictionaryPath);
        reader = new BufferedReader(new InputStreamReader(fis));
    }

    public boolean ready() throws IOException {
        return reader.ready();
    }

    public DictionaryItem getNextItem() throws IOException {
        final String line = reader.readLine();
        return null;
    }
}
