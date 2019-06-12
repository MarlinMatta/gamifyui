package edu.uapa.ui.gamify.requests.security;

import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;

import java.util.List;

public class PermissionRequests extends Request {
    private static PermissionRequests permissionRequests = null;

    private PermissionRequests() {
        super(Urls.APP_PERMISSION);
    }

    public static PermissionRequests getInstance() {
        return permissionRequests == null ? new PermissionRequests() : permissionRequests;
    }

    public List<PermissionDto> get(int page, int size, String searchValue) {
        String response = getExecute("?page=" + page + "&size=" + size);
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, PermissionDto.class);
        }
        return null;
    }

    public Long count() {
        String response = getExecute("/count");
        return response.isEmpty() ? 0L : Long.parseLong(response);
    }
}
