package com.api.appactivitats.entities.users.repository;

import com.api.appactivitats.entities.users.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
