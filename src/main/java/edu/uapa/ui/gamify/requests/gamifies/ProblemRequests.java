package edu.uapa.ui.gamify.requests.gamifies;

import com.mashape.unirest.http.HttpResponse;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.school.ProblemDto;
import edu.utesa.lib.models.enums.GameDifficulty;
import edu.utesa.lib.models.enums.GameDifficulty;
import edu.utesa.lib.utils.DateUtils;
import org.apache.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemRequests extends Request {
    private static ProblemRequests requestsInstance = null;


    private ProblemRequests() {
        super(Urls.APP_PROBLEM);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");

            }
        };
        setHeaders(headers);
    }

    public static ProblemRequests getInstance() {
        return requestsInstance == null ? new ProblemRequests() : requestsInstance;
    }

    public List<ProblemDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ProblemDto.class);
        }
        return null;
    }

    public List<ProblemDto> getPractice(GameDifficulty difficulty, int size) {
        String response = getExecute("/?difficulty=" + difficulty.name() + "&size=" + size);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ProblemDto.class);
        }
        return null;
    }

    public List<ProblemDto> getAllByExam(GameDifficulty difficulty, int size, int teacherId, int topicId, String from,
                                         String to) {
        String response = getExecute("/exam?difficulty=" + difficulty.name() + "&size=" + size
                + "&teacherId=" + teacherId + "&topicId=" + topicId + "&from=" + from + "&to=" + to);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ProblemDto.class);
        }
        return null;
    }

    public List<ProblemDto> getAll() {
        String response = getExecute("/all");
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ProblemDto.class);
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

    public boolean save(ProblemDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = postExecute("");
        return response.getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(ProblemDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = putExecute("");
        return response.getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
