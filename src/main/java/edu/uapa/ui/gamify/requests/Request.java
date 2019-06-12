package edu.uapa.ui.gamify.requests;

import com.mashape.unirest.http.Unirest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Request {
    private String url;
    private Map<String, Object> fields = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();

    protected Request(String url) {
        addHeader("accept", "application/x-www-form-urlencoded");
        this.url = url;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    protected String postExecute(String param) {
        String reponse = "";
        try {
            reponse = Unirest.post(url + param)
                    .fields(fields)
                    .asString().getBody();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return reponse;
    }

    protected String getExecute(String param) {
        System.out.println("URL: " + url + param);
        try {
            return Unirest.get(url + param)
                    .asString()
                    .getBody();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return "";
    }

    protected String putExecute(String param) {
        System.out.println("URL: " + url + param);
        try {
            return Unirest.put(url + param)
                    .fields(fields)
                    .asString()
                    .getBody();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return "";
    }

    protected String deleteExecute(String param) {
        System.out.println("URL: " + url + param);
        try {
            return Unirest.delete(url + param)
                    .fields(fields)
                    .asString()
                    .getBody();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return "";
    }

    protected void addField(String name, String value) {
        fields.put(name, value);
    }

    protected void cleanField() {
        fields.clear();
    }

    protected void addHeader(String name, String value) {
        headers.put(name, value);
    }
}
