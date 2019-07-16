package edu.uapa.ui.gamify.requests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Request {
    private String url;
    private Map<String, Object> fields = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private String jsonBody;

    protected Request(String url) {
        this.url = url;
    }

    protected void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    protected String getExecute(String param) {
        System.out.println("URL: " + url + param);
        try {
            return Unirest.get(url + param)
                    .asString().getBody();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return "";
    }

    protected String loginPosExecute() {
        String response = "";
        try {
            response = Unirest.post(url)
                    .fields(fields)
                    .asString().getBody();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return response;
    }

    protected HttpResponse<String> postExecute(String param) {
        System.out.println("URL: " + url + param);
        HttpResponse<String> response = null;
        try {
            response = Unirest.post(url + param)
                    .headers(headers)
                    .body(jsonBody)
                    .asString();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return response;
    }

    protected HttpResponse<String> putExecute(String param) {
        System.out.println("URL: " + url + param);
        HttpResponse<String> response = null;
        try {
            response = Unirest.put(url + param)
                    .headers(headers)
                    .body(jsonBody)
                    .asString();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return response;
    }

    protected void deleteExecute(String param) {
        System.out.println("URL: " + url + param);
        try {
            Unirest.delete(url + param)
                    .asString().getBody();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    protected void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    protected void addField(String name, String value) {
        fields.put(name, value);
    }
}
