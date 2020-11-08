package com.xmc.service;

import com.xmc.domain.User;

public interface UserService {
    void deleteUserRolesByRoleIds(User user,Long[] ids);
}
