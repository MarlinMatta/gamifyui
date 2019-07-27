package edu.uapa.ui.gamify.requests.location;

import com.mashape.unirest.http.HttpResponse;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.location.CountryDto;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryRequests extends Request {
    private static CountryRequests requestsInstance = null;


    private CountryRequests() {
        super(Urls.APP_COUNTRY);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");

            }
        };
        setHeaders(headers);
    }

    public static CountryRequests getInstance() {
        return requestsInstance == null ? new CountryRequests() : requestsInstance;
    }

    public List<CountryDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, CountryDto.class);
        }
        return null;
    }

    public List<CountryDto> getAll() {
        String response = getExecute("/all");
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, CountryDto.class);
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

    public boolean save(CountryDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = postExecute("");
        return response.getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(CountryDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        HttpResponse<String> response = putExecute("");
        return response.getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
