package com.api.appactivitats.entities.activities.service;

import com.api.appactivitats.entities.activities.domain.Activity;

import java.util.List;

public interface ActivityService {

    void createActivity(Activity activity);

    Activity readActivity(String id);

    List<Activity> readAllActivities();

    void addUserToActivity(String id, String userID);

    void updateActivity(String id, Activity activityWithUpdates);

    void deleteActivity(String id);

    void addActivitiesFromJson(String json);
}
