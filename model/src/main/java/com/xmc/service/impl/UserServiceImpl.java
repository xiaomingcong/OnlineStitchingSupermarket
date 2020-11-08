package com.xmc.service.impl;

import com.xmc.dao.RoleRepository;
import com.xmc.dao.UserRepository;
import com.xmc.domain.Role;
import com.xmc.domain.User;
import com.xmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void deleteUserRolesByRoleIds(User user, Long[] ids) {
        List<Role> deleteRoles = new ArrayList<>();
        for(int i = 0; i< ids.length ;i++){
            deleteRoles.add(roleRepository.findById(ids[i]).orElseGet(null));
        }
        Stream<Role> roleStream = user.getRoles().stream();
        List<Role> RemainingRoles =
                roleStream.map(role -> deleteRoles.contains(role) ? null : role).collect(Collectors.toList());
        user.setRoles(RemainingRoles);
        userRepository.save(user);
    }
}
