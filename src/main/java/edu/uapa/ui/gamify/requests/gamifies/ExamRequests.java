package edu.uapa.ui.gamify.requests.gamifies;

import com.mashape.unirest.http.HttpResponse;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.ExamDto;
import edu.utesa.lib.models.enums.GameDifficulty;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamRequests extends Request {
    private static ExamRequests requestsInstance = null;


    private ExamRequests() {
        super(Urls.APP_EXAM);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");

            }
        };
        setHeaders(headers);
    }

    public static ExamRequests getInstance() {
        return requestsInstance == null ? new ExamRequests() : requestsInstance;
    }

    public List<ExamDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ExamDto.class);
        }
        return null;
    }

    public List<ExamDto> getPractice(GameDifficulty difficulty, int size) {
        String response = getExecute("/?difficulty=" + difficulty.name() + "&size=" + size);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ExamDto.class);
        }
        return null;
    }

    public List<ExamDto> getAll() {
        String response = getExecute("/all");
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ExamDto.class);
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

    public boolean save(ExamDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = postExecute("");
        return response.getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(ExamDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = putExecute("");
        return response.getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
