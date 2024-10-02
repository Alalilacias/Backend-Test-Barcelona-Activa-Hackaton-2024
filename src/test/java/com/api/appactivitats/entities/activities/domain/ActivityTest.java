package com.api.appactivitats.entities.activities.domain;

import com.api.appactivitats.entities.users.domain.records.Phone;
import com.api.appactivitats.entities.users.dto.UserReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    private Activity activity;

    @BeforeEach
    void setUp() {
        activity = new Activity("Hackathon", "Incredibly fun and contested tournament", 30);
    }

    @Test
    void testConstructorValid(){
        assertNotNull(activity.getId(), "ID should have been generated automatically");
        assertEquals("Hackathon", activity.getName(), "Name should have been correctly set");
        assertEquals("Incredibly fun and contested tournament", activity.getDescription(), "Description should have been correctly set");
        assertNotNull(activity.getCreationDateTime(), "Creation date should have been set");
        assertEquals(30, activity.getCapacity(), "Capacity should have been initialized to 30");
    }

    @Test
    void testEnrollUser(){
        activity.setCapacity(1);
        activity.enrollUser(
                new UserReference(
                        UUID.randomUUID().toString(),
                        "Manel",
                        "Gracia",
                        "test@appactivitats.cat",
                        new Phone(34, 615498762)
                )
        );
        assertEquals(1, activity.getEnrolledUsers().size());

        activity.enrollUser(
                new UserReference(
                        UUID.randomUUID().toString(),
                        "Jose",
                        "Pineda",
                        "apalacchia@appactivitats.cat",
                        new Phone(34, 648931278)
                )
        );
        assertEquals(1, activity.getEnrolledUsers().size());
    }

    @Test
    void testSettersAndGetters() {
        activity.setName("Dance Class");
        assertEquals("Dance Class", activity.getName(), "Name should be set and retrieved correctly");

        activity.setDescription("A fun dance class");
        assertEquals("A fun dance class", activity.getDescription(), "Description should be set and retrieved correctly");

        activity.setCapacity(50);
        assertEquals(50, activity.getCapacity(), "Capacity should be set and retrieved correctly");
    }
}