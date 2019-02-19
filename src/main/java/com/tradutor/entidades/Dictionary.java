package com.tradutor.entidades;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Dictionary {

    private String id;
    private String context;
    private List<String> labels;
    private Map<String, Traducao> translation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Map<String, Traducao> getTranslation() {
        return translation;
    }

    public void setTranslation(Map<String, Traducao> translation) {
        this.translation = translation;
    }
}
