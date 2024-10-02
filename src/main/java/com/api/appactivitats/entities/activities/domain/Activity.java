package com.api.appactivitats.entities.activities.domain;

import com.api.appactivitats.entities.activities.dto.ActivityDTO;
import com.api.appactivitats.entities.users.dto.UserReference;
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
@Document(collection = "activities")
public class Activity {
    @Id
    @Setter(AccessLevel.NONE)
    private String id = UUID.randomUUID().toString();

    private String name;
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Setter(AccessLevel.NONE)
    private final Instant creationDateTime = Instant.now();

    private int capacity;

    private Set<UserReference> enrolledUsers = new HashSet<>();

    public Activity(String name, String description, int capacity){
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }

    public void enrollUser(UserReference user){
        if(this.capacity > this.enrolledUsers.size()) {
            this.enrolledUsers.add(user);
        }
    }
}
