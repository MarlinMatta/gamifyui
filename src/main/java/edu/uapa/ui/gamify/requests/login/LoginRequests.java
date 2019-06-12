package edu.uapa.ui.gamify.requests.login;

import edu.utesa.lib.models.dtos.security.UserDto;
import edu.uapa.ui.gamify.requests.Request;
import edu.uapa.ui.gamify.utils.JsonUtils;
import edu.uapa.ui.gamify.utils.Urls;

public class LoginRequests extends Request {
    private static LoginRequests loginRequests = null;

    private LoginRequests() {
        super(Urls.APP_LOGIN);
    }

    public static LoginRequests getInstance() {
        return loginRequests == null ? new LoginRequests() : loginRequests;
    }

    public UserDto login(String userName, String password) {
        addField("userName", userName);
        addField("password", password);
        String response = postExecute("");
        if (!response.isEmpty()) {
            return JsonUtils.toObject(response, UserDto.class);
        }
        return null;
    }

}
