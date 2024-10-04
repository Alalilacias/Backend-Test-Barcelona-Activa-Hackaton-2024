package com.api.appactivitats.entities.users.domain;

import com.api.appactivitats.entities.activities.dto.ActivityReference;
import com.api.appactivitats.entities.users.domain.records.ContactInformation;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id = UUID.randomUUID().toString();

    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\s-]+$", message = "Name must contain only letters")
    private String name;

    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\s-]+$", message = "Surname must contain only letters")
    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant registrationDateTime = Instant.now();

    private ContactInformation contactInformation;
    private long loginCount = 1;
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
