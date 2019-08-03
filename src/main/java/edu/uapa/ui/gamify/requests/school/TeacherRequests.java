package edu.uapa.ui.gamify.requests.school;

import com.mashape.unirest.http.HttpResponse;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherRequests extends Request {
    private static TeacherRequests requestsInstance = null;


    private TeacherRequests() {
        super(Urls.APP_TEACHER);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");

            }
        };
        setHeaders(headers);
    }

    public static TeacherRequests getInstance() {
        return requestsInstance == null ? new TeacherRequests() : requestsInstance;
    }

    public List<TeacherDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, TeacherDto.class);
        }
        return null;
    }

    public List<TeacherDto> getAll() {
        String response = getExecute("/all");
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, TeacherDto.class);
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

    public boolean save(TeacherDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = postExecute("");
        return response.getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(TeacherDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = putExecute("");
        return response.getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
