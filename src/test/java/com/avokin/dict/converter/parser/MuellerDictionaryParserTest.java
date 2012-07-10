package com.avokin.dict.converter.parser;

import com.avokin.dict.converter.model.DictionaryItem;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * User: Andrey.Vokin
 * Date: 7/10/12
 */
public class MuellerDictionaryParserTest {
    private MuellerDictionaryParser parser;

    @Test
    public void testTranscriptionParsing() throws IOException {
        parser = new MuellerDictionaryParser("src/test/resources/com/avokin/dict/converter/parser/transcription.txt");

        DictionaryItem di = parser.getNextItem();
        Assert.assertEquals("ack-ack", di.getWord());
        Assert.assertEquals("ˈækˈæk", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("her", di.getWord());
        Assert.assertEquals("hɛː", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("tenth", di.getWord());
        Assert.assertEquals("tenθ", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("acting", di.getWord());
        Assert.assertEquals("ˈæktiŋ", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("machine", di.getWord());
        Assert.assertEquals("məˈʃiːn", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("wash", di.getWord());
        Assert.assertEquals("wɔʃ", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("another", di.getWord());
        Assert.assertEquals("əˈnʌðə", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("acquaintance", di.getWord());
        Assert.assertEquals("əˈkweintəns", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("past", di.getWord());
        Assert.assertEquals("pɑːst", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("book", di.getWord());
        Assert.assertEquals("bʊk", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("too", di.getWord());
        Assert.assertEquals("tuː", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("here", di.getWord());
        Assert.assertEquals("hiə", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("day", di.getWord());
        Assert.assertEquals("dei", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("sort", di.getWord());
        Assert.assertEquals("sɔːt", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("hot", di.getWord());
        Assert.assertEquals("hɔt", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("judge", di.getWord());
        Assert.assertEquals("ˈʤʌʤ", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("aerial", di.getWord());
        Assert.assertEquals("ˈeəriəl", di.getTranscription());

        di = parser.getNextItem();
        Assert.assertEquals("acknowledge", di.getWord());
        Assert.assertEquals("əkˈnɔliʤ", di.getTranscription());
    }

    @Test
    public void testContentParsing() throws IOException {
        parser = new MuellerDictionaryParser("src/test/resources/com/avokin/dict/converter/parser/content.txt");

        DictionaryItem di = parser.getNextItem();
        Assert.assertEquals("her", di.getWord());

        di = parser.getNextItem();
        Assert.assertEquals("amn't", di.getWord());
        Assert.assertEquals("_разг. = am not", di.getTranslations());
    }
}
