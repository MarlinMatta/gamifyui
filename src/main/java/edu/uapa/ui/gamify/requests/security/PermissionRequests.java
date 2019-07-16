package edu.uapa.ui.gamify.requests.security;

import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.utesa.lib.models.dtos.security.PermissionGroupDto;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionRequests extends Request {
    private static PermissionRequests requestsInstance = null;

    private PermissionRequests() {
        super(Urls.APP_PERMISSION);
        Map<String, String> headers = new HashMap<>() {
            {
                put("accept", "application/json;charset=UTF-8");
                put("Content-Type", "application/json;charset=UTF-8");

            }
        };
        setHeaders(headers);
    }

    public static PermissionRequests getInstance() {
        return requestsInstance == null ? new PermissionRequests() : requestsInstance;
    }

    public List<PermissionDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, PermissionDto.class);
        }
        return null;
    }

    public List<PermissionGroupDto> getGroups(int page, int size, String searchValue) {
        String response = getExecute("/groups?page=" + page + "&size=" + size + "&filterValue=" + searchValue);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, PermissionGroupDto.class);
        }
        return null;
    }

    public void delete(Long id) {
        deleteExecute("?id=" + id);
    }

    public void deleteGroup(Long id) {
        deleteExecute("/groups?id=" + id);
    }

    public Long count(String searchValue) {
        String response = getExecute("/count?filterValue=" + searchValue);
        return response.isEmpty() ? 0L : Long.parseLong(response);
    }

    public Long countGroup(String searchValue) {
        String response = getExecute("/groups/count?filterValue=" + searchValue);
        return response.isEmpty() ? 0L : Long.parseLong(response);
    }

    public boolean save(PermissionDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        return postExecute("").getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean saveGroup(PermissionGroupDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        return postExecute("/groups").getStatus() == HttpStatus.SC_CREATED;
    }

    public boolean update(PermissionDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        return putExecute("").getStatus() == HttpStatus.SC_ACCEPTED;
    }

    public boolean updateGroup(PermissionGroupDto dto) {
        setJsonBody(JsonUtils.toJSON(dto));
        return putExecute("/groups").getStatus() == HttpStatus.SC_ACCEPTED;
    }
}
