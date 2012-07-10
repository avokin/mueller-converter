package com.avokin.dict.converter.parser;

import com.avokin.dict.converter.model.DictionaryItem;
import org.junit.Test;

import java.io.IOException;

/**
 * User: Andrey.Vokin
 * Date: 7/10/12
 */
public class MuellerDictionaryParserStatisticTest {
    @Test
    public void testContentParsing() throws IOException {
        final MuellerDictionaryParser parser = new MuellerDictionaryParser("data/Mueller7GPL.koi");

        while (true) {
            DictionaryItem di = parser.getNextItem();
            if (di == null) {
                break;
            }
        }

        parser.getUsedSymbolsMap();
    }
}
