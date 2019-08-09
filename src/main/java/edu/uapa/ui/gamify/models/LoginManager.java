package edu.uapa.ui.gamify.models;

import edu.utesa.lib.models.dtos.school.StudentDto;
import edu.utesa.lib.models.dtos.school.TeacherDto;
import edu.utesa.lib.models.dtos.security.PermissionDto;
import edu.utesa.lib.models.dtos.security.UserDto;
import edu.utesa.lib.models.enums.EnumLoanMasterPermission;

import java.util.List;
import java.util.stream.Collectors;

public class LoginManager {

    private UserDto userDto;
    private List<Integer> permission;
    private TeacherDto teacherDto;
    private StudentDto studentDto;

    public LoginManager(UserDto userDto) {
        this.userDto = userDto;
        this.permission = userDto.getPermissions().stream().map(PermissionDto::getCode).collect(Collectors.toList());

    }

    public boolean hasPermission(int code) {
        return !isRoot() && permission.parallelStream().noneMatch(permission -> permission == code);
    }

    private boolean isRoot() {
        return permission.parallelStream().anyMatch(permission -> permission == EnumLoanMasterPermission.ROOT.code);
    }

    public String getName() {
        return userDto.getNickName();
    }

    public long getId() {
        return userDto.getId();
    }
}

