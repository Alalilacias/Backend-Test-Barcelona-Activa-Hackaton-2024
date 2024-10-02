package com.api.appactivitats.entities.activities.controller;

import com.api.appactivitats.entities.activities.domain.Activity;
import com.api.appactivitats.entities.activities.dto.ActivityDTO;
import com.api.appactivitats.entities.activities.dto.ActivityMapper;
import com.api.appactivitats.entities.activities.dto.ActivityRegistrationRequest;
import com.api.appactivitats.entities.activities.service.ActivityService;
import com.api.appactivitats.entities.users.dto.UserMapper;
import com.api.appactivitats.entities.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appActivitats")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    
    @PostMapping("/activity")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityRegistrationRequest activityRegistrationRequest){
        Activity activity = new Activity(
                activityRegistrationRequest.name(),
                activityRegistrationRequest.description(),
                activityRegistrationRequest.capacity()
        );

        activityService.createActivity(activity);
        return ResponseEntity.ok(ActivityMapper.toDTO(activity));
    }

}
