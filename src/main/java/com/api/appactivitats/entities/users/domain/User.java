package com.api.appactivitats.entities.users.domain;

import com.api.appactivitats.entities.activities.dto.ActivityReference;
import com.api.appactivitats.entities.users.domain.records.ContactInformation;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    private final String id = UUID.randomUUID().toString();

    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\s-]+$", message = "Name must contain only letters")
    private String name;

    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\s-]+$", message = "Surname must contain only letters")
    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Setter(AccessLevel.NONE)
    private final Instant registrationDateTime = Instant.now();

    private ContactInformation contactInformation;
    private long loginCount = 1;
    @Setter(AccessLevel.PRIVATE)
    private Instant lastLoginDate = registrationDateTime;

    private Set<ActivityReference> enrolledActivities = new HashSet<>();

    public User(String name, String surname, ContactInformation contactInformation){
        this.name = name;
        this.surname = surname;
        this.contactInformation = contactInformation;
    }

    public void updateLastLoginDate(){
        this.lastLoginDate = Instant.now();
    }
}
