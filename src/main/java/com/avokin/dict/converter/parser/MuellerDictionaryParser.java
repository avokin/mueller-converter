package com.avokin.dict.converter.parser;

import com.avokin.dict.converter.model.DictionaryItem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Andrey.Vokin
 * Date: 7/9/12
 */
public class MuellerDictionaryParser {
    private BufferedReader reader;

    private Map<Character, String> usedSymbolsMap;

    public MuellerDictionaryParser(final String dictionaryPath) throws FileNotFoundException, UnsupportedEncodingException {
        FileInputStream fis = new FileInputStream(dictionaryPath);
        reader = new BufferedReader(new InputStreamReader(fis, "KOI8-R"));
        usedSymbolsMap = new HashMap<Character, String>();
    }

    public boolean ready() throws IOException {
        return reader.ready();
    }

    public Map<Character, String> getUsedSymbolsMap() {
        return usedSymbolsMap;
    }

    private void appendTranscriptionSymbol(final String word, final StringBuilder result, final char symbol) {
        result.append(symbol);

        if (!usedSymbolsMap.containsKey(symbol)) {
            usedSymbolsMap.put(symbol, word);
        }
    }

    private String processTranscription(final String muellerTranscription, final String word) {
        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < muellerTranscription.length(); i++) {
            final char c = muellerTranscription.charAt(i);

            final char next = i < muellerTranscription.length() - 1 ? muellerTranscription.charAt(i + 1) : 0;

            if (c == 81) {
                appendTranscriptionSymbol(word, result, 'æ');
            } else if (c == 1093) {
                appendTranscriptionSymbol(word, result, 'ˈ');
            } else if (c == 1094) {
                appendTranscriptionSymbol(word, result, 'ʌ');
            } else if (c == 9562) {
                appendTranscriptionSymbol(word, result, 'ə');
            } else if (c == 'I') {
                appendTranscriptionSymbol(word, result, 'i');
            } else if (c == 'A') {
                appendTranscriptionSymbol(word, result, 'ɑ');
            } else if (c == 1067) {
                appendTranscriptionSymbol(word, result, 'ː');
            } else if (c == 'D') {
                appendTranscriptionSymbol(word, result, 'ð');
            } else if (c == '<') {
                if (next == 1067) {
                    // Sound e in 'her'
                    appendTranscriptionSymbol(word, result, 'ɛ');
                } else {
                    // Sound a in 'another'
                    appendTranscriptionSymbol(word, result, 'ə');
                }
            } else if (c == 'S') {
                appendTranscriptionSymbol(word, result, 'ʃ');
            } else if (c == '_') {
                appendTranscriptionSymbol(word, result, 'ɔ');
            } else if (c == 'N') {
                appendTranscriptionSymbol(word, result, 'ŋ');
            } else if (c == 'T') {
                appendTranscriptionSymbol(word, result, 'θ');
            } else if (c == 'u') {
                if (next == 1067) {
                    // Sound oo in 'too'
                    appendTranscriptionSymbol(word, result, 'u');
                } else {
                    // Sound oo in 'book'
                    appendTranscriptionSymbol(word, result, 'ʊ');
                }
            } else if (c == 'd' && next == 'Z') {
                appendTranscriptionSymbol(word, result, 'ʤ');
                i++;
            } else if (c == 'E') {
                appendTranscriptionSymbol(word, result, 'e');
            } else if (c == 9608) {
                appendTranscriptionSymbol(word, result, 'ɔ');
            }
            else {
                appendTranscriptionSymbol(word, result, c);
            }
        }

        return result.toString();
    }

    private String trimWord(final String muellerWord) {
        final StringBuilder result = new StringBuilder();
        final String[] words = muellerWord.split(" ");
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (!word.startsWith("_")) {
                result.append(word).append(' ');
            }
        }
        return result.toString().trim();
    }

    public DictionaryItem getNextItem() throws IOException {
        final String line = reader.readLine();
        if (line == null) {
            return null;
        }

        final int wordEnd = line.indexOf("  ");
        assert wordEnd > 0;

        final int transStart = line.indexOf('[');
        final int transEnd = line.indexOf(']', transStart);
        final String muellerWord = line.substring(0, wordEnd).trim();

        final String muellerTranscription = transStart > 0 && transEnd > 0 ? line.substring(transStart + 1, transEnd) : "";

        final String translations = line.substring(Math.max(wordEnd, transEnd) + 1).trim();

        final String word = trimWord(muellerWord);
        final String transcription = processTranscription(muellerTranscription, word);

        return new DictionaryItem(word, transcription, translations);
    }
}
