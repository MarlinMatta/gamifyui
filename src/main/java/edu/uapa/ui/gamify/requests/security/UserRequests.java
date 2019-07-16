package edu.uapa.ui.gamify.requests.security;

import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRequests extends Request {
    private static UserRequests requestsInstance = null;

    private UserRequests() {
        super(Urls.APP_USER);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");
            }
        };
        setHeaders(headers);
    }

    public static UserRequests getInstance() {
        return requestsInstance == null ? new UserRequests() : requestsInstance;
    }

    public List<UserDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, UserDto.class);
        }
        return null;
    }

    public List<PermissionDto> getPermissions(Long userId, String searchValue) {
        String response = getExecute("/permissions?userId=" + userId + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, PermissionDto.class);
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

    public boolean save(UserDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        return postExecute("").getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(UserDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        return putExecute("").getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
