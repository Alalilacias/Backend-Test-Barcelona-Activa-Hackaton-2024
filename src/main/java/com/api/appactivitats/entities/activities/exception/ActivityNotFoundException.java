package com.api.appactivitats.entities.activities.exception;

public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException(String id) {
        super("Activity with ID: " + id + "not found.");
    }
}
