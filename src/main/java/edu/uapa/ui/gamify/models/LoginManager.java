package edu.uapa.ui.gamify.models;

import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;

import java.util.List;
import java.util.stream.Collectors;

public class LoginManager {

    private UserDto userDto;
    private List<Integer> permission;

    public LoginManager(UserDto userDto) {
        this.userDto = userDto;
        this.permission = userDto.getPermissionDtos().stream().map(PermissionDto::getCode).collect(Collectors.toList());
    }

    //Esta forma de iterar por cada permiso es pesada. Mejorar
    public boolean hasPermission(int code) {
        return !isRoot() && permission.parallelStream().noneMatch(permission -> permission == code);
    }

    private boolean isRoot() {
        return permission.parallelStream().anyMatch(permission -> permission == EnumLoanMasterPermission.ROOT.code);
    }

    public String getName() {
        return userDto.getNickName();
    }
}
