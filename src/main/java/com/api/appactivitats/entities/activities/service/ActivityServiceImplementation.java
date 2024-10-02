package com.api.appactivitats.entities.activities.service;

import com.api.appactivitats.entities.activities.domain.Activity;
import com.api.appactivitats.entities.activities.exception.ActivityNotFoundException;
import com.api.appactivitats.entities.activities.exception.ReadingJSONException;
import com.api.appactivitats.entities.activities.repository.ActivityRepository;
import com.api.appactivitats.entities.users.domain.User;
import com.api.appactivitats.entities.users.dto.UserMapper;
import com.api.appactivitats.entities.users.exception.UserNotFoundException;
import com.api.appactivitats.entities.users.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImplementation implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void createActivity(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public Activity readActivity(String id) {
        return activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));
    }

    @Override
    public List<Activity> readAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public void addUserToActivity(String id, String userID) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));
        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));

        activity.enrollUser(UserMapper.toReference(user));
        activityRepository.save(activity);
    }

    @Override
    public void updateActivity(String id, Activity activityWithUpdates) {
        Activity activityToUpdate = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));

        if (!activityWithUpdates.getName().equals(activityToUpdate.getName())){
            activityToUpdate.setName(activityWithUpdates.getName());
        }
        if (!activityWithUpdates.getDescription().equals(activityToUpdate.getDescription())){
            activityToUpdate.setDescription(activityWithUpdates.getDescription());
        }
        if (activityWithUpdates.getCapacity() != (activityToUpdate.getCapacity())){
            activityToUpdate.setCapacity(activityWithUpdates.getCapacity());
        }

        activityRepository.save(activityToUpdate);
    }

    @Override
    public void deleteActivity(String id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));

        activityRepository.delete(activity);
    }

    @Override
    public void addActivitiesFromJson(String json) {
        List<Activity> activityJsonList;

        try {
            activityJsonList = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new ReadingJSONException("Failed to read Activities from JSON: " + e.getMessage(), e);
        }

        activityRepository.saveAll(activityJsonList);
    }
}
