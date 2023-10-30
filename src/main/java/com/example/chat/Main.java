package com.example.chat;


import com.example.chat.model.entity.Role;
import com.example.chat.model.entity.User;
import com.example.chat.model.service.RoleService;
import com.example.chat.model.service.UserService;
import jakarta.inject.Inject;


public class Main {
    @Inject
    RoleService roleService;

    @Inject
    UserService userService;

    public static void main(String[] args) throws Exception {
//        Role role = Role.builder().role("admin").build();
//        roleService.save(role);
//
//        User user = User.builder()
//                .lastname("khadiv")
//                .firstname("melika")
//                .nickname("meli")
//                .password("meli123")
//                .privateAccount(true)
//                .role(roleService.findById(1L))
//                .username("melika")
//                .build();
//        userService.save(user);
//    }
    }
}
