package edu.uapa.ui.gamify.requests.security;

import edu.utesa.lib.models.dtos.security.ParamDto;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;

import java.util.List;

public class ParameterRequests extends Request {
    private static ParameterRequests parameterRequests = null;

    private ParameterRequests() {
        super(Urls.APP_PARAMETER);
    }

    public static ParameterRequests getInstance() {
        return parameterRequests == null ? new ParameterRequests() : parameterRequests;
    }

    public List<ParamDto> get(int page, int size) {
        String response = getExecute("");
        if (!response.isEmpty()) {
            return JsonUtils.toObjectList(response, ParamDto.class);
        }
        return null;
    }

}
