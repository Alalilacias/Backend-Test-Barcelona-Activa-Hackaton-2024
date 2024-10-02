package com.api.appactivitats.entities.activities.repository;

import com.api.appactivitats.entities.activities.domain.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {}
