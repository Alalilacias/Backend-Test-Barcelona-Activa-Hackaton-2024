package com.api.appactivitats.entities.activities.service;

import com.api.appactivitats.entities.activities.domain.Activity;
import com.api.appactivitats.entities.activities.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {

    void createActivity(Activity activity);

    Activity readActivity(String id);

    List<Activity> readAllActivities();

    Activity addUserToActivity(String id, String userID);

    void updateActivity(String id, ActivityDTO activityWithUpdates);

    void deleteActivity(String id);

    List<ActivityDTO> addActivitiesFromJson(String json);
}
