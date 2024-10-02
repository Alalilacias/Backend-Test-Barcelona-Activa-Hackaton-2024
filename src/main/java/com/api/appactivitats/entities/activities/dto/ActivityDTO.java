package com.api.appactivitats.entities.activities.dto;

import com.api.appactivitats.entities.users.dto.UserReference;

import java.util.Set;

public record ActivityDTO(String id,
                          String name,
                          String description,
                          int capacity,
                          int placesLeft,
                          Set<UserReference> enrolledUsers
) {}
