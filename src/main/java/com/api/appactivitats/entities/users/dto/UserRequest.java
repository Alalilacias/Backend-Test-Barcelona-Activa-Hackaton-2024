package com.api.appactivitats.entities.users.dto;

import com.api.appactivitats.entities.users.domain.records.ContactInformation;

public record UserRequest(String name, String surname, ContactInformation contactInformation) {
}
