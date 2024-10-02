package com.api.appactivitats.entities.users.dto;

import com.api.appactivitats.entities.users.domain.User;

public class UserMapper {
    public static UserReference toReference(User user){
        return new UserReference(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getContactInformation().email(),
                user.getContactInformation().phone()
        );
    }
    public static UserDTO toDTO(UserReference userReference){
        return new UserDTO(
                userReference.name(),
                userReference.surname(),
                userReference.email()
        );
    }
    public static UserDTO toDTO(User user){
        return new UserDTO(
                user.getName(),
                user.getSurname(),
                user.getContactInformation().email()
        );
    }
}
