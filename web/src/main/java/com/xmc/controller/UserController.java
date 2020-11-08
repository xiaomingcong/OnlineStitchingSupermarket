package com.xmc.controller;

import com.xmc.dao.RoleRepository;
import com.xmc.dao.UserRepository;
import com.xmc.domain.Role;
import com.xmc.domain.User;
import com.xmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/addUser")
    public Long add(@RequestBody User user){
        return userRepository.save(user).getId();
    }

    @PostMapping("delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return;
    }

    @PostMapping("/deleteUsers")
    public void deleteUserRoleByRoleIds(@RequestBody User user,Long[] ids){
        userService.deleteUserRolesByRoleIds(user,ids);

    }
}
