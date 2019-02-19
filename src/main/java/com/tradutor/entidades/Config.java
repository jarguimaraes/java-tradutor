package com.tradutor.entidades;

import java.util.List;

public class Config {

    private String sourceLanguage;
    private String targetLanguage;
    private List<Dictionary> dictionary;

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public List<Dictionary> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<Dictionary> dictionary) {
        this.dictionary = dictionary;
    }
}
