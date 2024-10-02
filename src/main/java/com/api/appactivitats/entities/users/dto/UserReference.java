package com.api.appactivitats.entities.users.dto;

import com.api.appactivitats.entities.users.domain.records.Phone;

public record UserReference(String id,
                            String name,
                            String surname,
                            String email,
                            Phone phone) {}
