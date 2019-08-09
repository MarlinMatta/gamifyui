package edu.uapa.ui.gamify.requests.gamifies;

import com.mashape.unirest.http.HttpResponse;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.TopicDto;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicRequests extends Request {
    private static TopicRequests requestsInstance = null;


    private TopicRequests() {
        super(Urls.APP_TOPIC);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");

            }
        };
        setHeaders(headers);
    }

    public static TopicRequests getInstance() {
        return requestsInstance == null ? new TopicRequests() : requestsInstance;
    }

    public List<TopicDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, TopicDto.class);
        }
        return null;
    }

    public List<TopicDto> getBySubject() {
        String response = getExecute("/?topic=" + 1);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, TopicDto.class);
        }
        return null;
    }

    public List<TopicDto> getAll() {
        String response = getExecute("/all");
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, TopicDto.class);
        }
        return null;
    }

    public void delete(Long id) {
        deleteExecute("?id=" + id);
    }

    public Long count(String searchValue) {
        String response = getExecute("/count?filterValue=" + searchValue);
        return response.isEmpty() ? 0L : Long.parseLong(response);
    }

    public boolean save(TopicDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = postExecute("");
        return response.getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(TopicDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = putExecute("");
        return response.getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
