package com.avokin.dict.converter.parser;

import com.avokin.dict.converter.model.DictionaryItem;

import java.io.*;

/**
 * User: Andrey.Vokin
 * Date: 7/9/12
 */
public class MuellerDictionaryParser {
    private BufferedReader reader;

    public MuellerDictionaryParser(final String dictionaryPath) throws FileNotFoundException, UnsupportedEncodingException {
        FileInputStream fis = new FileInputStream(dictionaryPath);
        reader = new BufferedReader(new InputStreamReader(fis, "KOI8-R"));
    }

    public boolean ready() throws IOException {
        return reader.ready();
    }

    private String processTranscription(final String muellerTranscription) {
        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < muellerTranscription.length(); i++) {
            final char c = muellerTranscription.charAt(i);

            final char next = i < muellerTranscription.length() - 1 ? muellerTranscription.charAt(i + 1) : 0;

            if (c == 81) {
                result.append('æ');
            } else if (c == 1093) {
                result.append('ˈ');
            } else if (c == 1094) {
                result.append('ʌ');
            } else if (c == 9562) {
                result.append('ə');
            } else if (c == 'I') {
                result.append('i');
            } else if (c == 'A') {
                result.append('ɑ');
            } else if (c == 1067) {
                result.append('ː');
            } else if (c == 'D') {
                result.append('ð');
            } else if (c == '<') {
                if (next == 1067) {
                    // Sound e in 'her'
                    result.append('ɛ');
                } else {
                    // Sound a in 'another'
                    result.append('ə');
                }
            } else if (c == 'S') {
                result.append('ʃ');
            } else if (c == '_') {
                result.append('ɔ');
            } else if (c == 'N') {
                result.append('ŋ');
            } else if (c == 'T') {
                result.append('θ');
            } else if (c == 'u') {
                if (next == 1067) {
                    // Sound oo in 'too'
                    result.append('u');
                } else {
                    // Sound oo in 'book'
                    result.append('ʊ');
                }
            } else if (c == 'd' && next == 'Z') {
                result.append('ʤ');
                i++;
            }
            else {
                result.append(c);
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
        final int transStart = line.indexOf('[');
        final int transEnd = line.indexOf(']', transStart);
        final String muellerWord = line.substring(0, transStart).trim();
        final String muellerTranscription = line.substring(transStart + 1, transEnd);
        final String translations = line.substring(transEnd + 1).trim();

        final String word = trimWord(muellerWord);
        final String transcription = processTranscription(muellerTranscription);

        return new DictionaryItem(word, transcription, translations);
    }
}
