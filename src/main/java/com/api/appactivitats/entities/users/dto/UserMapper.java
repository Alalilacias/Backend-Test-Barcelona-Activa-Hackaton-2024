package com.api.appactivitats.entities.users.dto;

import com.api.appactivitats.entities.users.domain.User;

public class UserMapper {
    static public UserReference toReference(User user){
        return new UserReference(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getContactInformation().email(),
                user.getContactInformation().phone()
        );
    }
}
