package com.api.appactivitats.entities.activities.dto;

import com.api.appactivitats.entities.activities.domain.Activity;

public class ActivityMapper {
    public static ActivityReference toReference (Activity activity){
        return new ActivityReference(activity.getId(), activity.getName());
    }
    public static ActivityDTO toDTO(Activity activity){
        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getDescription(),
                activity.getCapacity(),
                activity.getCapacity() - activity.getEnrolledUsers().size(),
                activity.getEnrolledUsers()
        );
    }
}
