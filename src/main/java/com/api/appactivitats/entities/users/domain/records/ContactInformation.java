package com.api.appactivitats.entities.users.domain.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactInformation(@NotBlank(message = "Email cannot be blank")
                                 @Email(message = "Email should have a valid format")
                                 String email,
                                 Address address,
                                 Phone phone
) {
}
