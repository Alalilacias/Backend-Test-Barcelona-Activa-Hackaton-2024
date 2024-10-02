package com.api.appactivitats.entities.activities.dto;

import com.api.appactivitats.entities.activities.domain.Activity;

public class ActivityMapper {
    public static ActivityReference toReference (Activity activity){
        return new ActivityReference(activity.getId(), activity.getName());
    }
}
