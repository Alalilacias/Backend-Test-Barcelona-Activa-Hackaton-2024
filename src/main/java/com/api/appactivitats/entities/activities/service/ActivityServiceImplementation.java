package com.api.appactivitats.entities.activities.service;

import com.api.appactivitats.entities.activities.domain.Activity;
import com.api.appactivitats.entities.activities.dto.ActivityDTO;
import com.api.appactivitats.entities.activities.dto.ActivityMapper;
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
    public Activity addUserToActivity(String id, String userID) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));
        User user = userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));

        activity.enrollUser(UserMapper.toReference(user));
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(String id, ActivityDTO activityWithUpdates) {
        Activity activityToUpdate = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));

        if (!activityWithUpdates.name().equals(activityToUpdate.getName())){
            activityToUpdate.setName(activityWithUpdates.name());
        }
        if (!activityWithUpdates.description().equals(activityToUpdate.getDescription())){
            activityToUpdate.setDescription(activityWithUpdates.description());
        }
        if (activityWithUpdates.capacity() != (activityToUpdate.getCapacity())){
            activityToUpdate.setCapacity(activityWithUpdates.capacity());
        }

        return activityRepository.save(activityToUpdate);
    }

    @Override
    public void deleteActivity(String id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));

        activityRepository.delete(activity);
    }

    @Override
    public List<ActivityDTO> addActivitiesFromJson(String json) {
        List<Activity> activityJsonList;

        try {
            activityJsonList = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new ReadingJSONException("Failed to read Activities from JSON: " + e.getMessage(), e);
        }

        return activityRepository.saveAll(activityJsonList).stream()
                .map(ActivityMapper::toDTO)
                .toList();
    }
}
