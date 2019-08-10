package edu.uapa.ui.gamify.requests.learn;

import com.mashape.unirest.http.HttpResponse;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.LearnDto;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearnRequests extends Request {
    private static LearnRequests requestsInstance = null;

    private LearnRequests() {
        super(Urls.APP_LEARN);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");

            }
        };
        setHeaders(headers);
    }

    public static LearnRequests getInstance() {
        return requestsInstance == null ? new LearnRequests() : requestsInstance;
    }

    public List<LearnDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, LearnDto.class);
        }
        return null;
    }

    public List<LearnDto> getAll() {
        String response = getExecute("/all");
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, LearnDto.class);
        }
        return null;
    }

    public LearnDto getByTopicId(String id) {
        String response = getExecute("?topicId=" + id);
        if (!response.isEmpty()) {
            return JsonUtils.toObject(response, LearnDto.class);
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

    public boolean save(LearnDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = postExecute("");
        return response.getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(LearnDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = putExecute("");
        return response.getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
