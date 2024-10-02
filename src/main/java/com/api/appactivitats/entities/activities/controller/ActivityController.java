package com.api.appactivitats.entities.activities.controller;

import com.api.appactivitats.entities.activities.domain.Activity;
import com.api.appactivitats.entities.activities.dto.ActivityDTO;
import com.api.appactivitats.entities.activities.dto.ActivityMapper;
import com.api.appactivitats.entities.activities.dto.ActivityRegistrationRequest;
import com.api.appactivitats.entities.activities.service.ActivityService;
import com.api.appactivitats.entities.users.dto.UserDTO;
import com.api.appactivitats.entities.users.dto.UserMapper;
import com.api.appactivitats.entities.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appActivitats")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    @PostMapping("/activities")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityRegistrationRequest activityRegistrationRequest){
        Activity activity = new Activity(
                activityRegistrationRequest.name(),
                activityRegistrationRequest.description(),
                activityRegistrationRequest.capacity()
        );

        activityService.createActivity(activity);
        return ResponseEntity.ok(ActivityMapper.toDTO(activity));
    }

    @PostMapping("/importActivities")
    public ResponseEntity<List<ActivityDTO>> createActivitiesFromJson(@RequestBody String jsonString){
        List<ActivityDTO> activityDTOList = activityService.addActivitiesFromJson(jsonString);
        return ResponseEntity.ok(activityDTOList);
    }

    @PostMapping("/activities/{id}/users/{idUser}")
    public ResponseEntity<ActivityDTO> createUserToActivity(@PathVariable String id, @PathVariable String idUser) {
        Activity activity = activityService.addUserToActivity(id, idUser);
        return ResponseEntity.ok(ActivityMapper.toDTO(activity));
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<ActivityDTO> readActivity(@PathVariable String id){
        Activity activity = activityService.readActivity(id);
        return ResponseEntity.ok(ActivityMapper.toDTO(activity));
    }

    @GetMapping("/activities/{id}/users")
    public ResponseEntity<List<UserDTO>> readUsersByActivity(@PathVariable String id) {
        List<UserDTO> users = userService.readUsersByActivity(id).stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PutMapping("activities/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable String id, @RequestBody ActivityDTO activityDTO){
        activityService.updateActivity(id, activityDTO);
        return ResponseEntity.ok(activityDTO);
    }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityDTO>> readAllActivities() {
        List<ActivityDTO> activities = activityService.readAllActivities().stream()
                .map(ActivityMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(activities);
    }

    @DeleteMapping("/activities/{id}")
    public ResponseEntity<ActivityDTO> deleteActivity(@PathVariable String id) {
        activityService.deleteActivity(id);
        return ResponseEntity.ok().build();
    }
}
