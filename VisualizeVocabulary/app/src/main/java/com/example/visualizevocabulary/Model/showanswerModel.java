package com.example.visualizevocabulary.Model;

public class showanswerModel {

    String question;
    String audio;

    public showanswerModel(String question, String audio) {
        this.question = question;
        this.audio = audio;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
