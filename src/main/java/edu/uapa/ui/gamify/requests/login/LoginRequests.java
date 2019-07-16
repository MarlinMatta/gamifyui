package edu.uapa.ui.gamify.requests.login;

import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;
import edu.utesa.lib.models.dtos.security.UserDto;

import java.util.HashMap;
import java.util.Map;

public class LoginRequests extends Request {

    private static LoginRequests requestsInstance = null;

    private LoginRequests() {
        super(Urls.APP_LOGIN);
        Map<String, String> headers = new HashMap<>() {
            {
                put("Accept", "application/x-www-form-urlencoded");
                put("Content-Type", "application/x-www-form-urlencoded");
            }
        };
        setHeaders(headers);
    }

    public static LoginRequests getInstance() {
        return requestsInstance == null ? new LoginRequests() : requestsInstance;
    }

    public UserDto login(String userName, String password) {
        addField("userName", userName);
        addField("password", password);
        String response = loginPosExecute();
        if (!response.isEmpty()) {
            return JsonUtils.toObject(response, UserDto.class);
        }
        return null;
    }

}
