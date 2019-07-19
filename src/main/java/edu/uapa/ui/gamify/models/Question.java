package edu.uapa.ui.gamify.models;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private long id;
    private String question;
    private List<Response> badResponse;
    private Response response;
    private boolean good = false;

    public Question() {
        badResponse = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Response> getBadResponse() {
        return badResponse;
    }

    public void setBadResponse(List<Response> badResponse) {
        this.badResponse = badResponse;
    }

    public void addBadResponse(Response response) {
        this.badResponse.add(response);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }
}
