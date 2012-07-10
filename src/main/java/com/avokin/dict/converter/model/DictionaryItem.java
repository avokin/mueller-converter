package com.avokin.dict.converter.model;

/**
 * User: Andrey.Vokin
 * Date: 7/9/12
 */
public class DictionaryItem {
    public String word;

    public String transcription;

    public String translations;

    public DictionaryItem(String word, String transcription, String translations) {
        this.word = word;
        this.transcription = transcription;
        this.translations = translations;
    }

    public String getWord() {
        return word;
    }

    public String getTranscription() {
        return transcription;
    }

    public String getTranslations() {
        return translations;
    }
}
